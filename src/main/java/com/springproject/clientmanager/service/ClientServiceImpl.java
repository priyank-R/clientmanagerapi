package com.springproject.clientmanager.service;

import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.Service;
import com.springproject.clientmanager.domains.ServiceType;
import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.exceptions.IncompleteDataException;
import com.springproject.clientmanager.exceptions.UserNotFoundException;
import com.springproject.clientmanager.repositories.ClientRepository;
import com.springproject.clientmanager.repositories.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@org.springframework.stereotype.Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepo;
    @Autowired
    private UserRepository userRepository;

    public ClientServiceImpl(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public Service addService(Map<String,Object> body, String userEmail) {

        String[] requiredFields = {"client_id","service_type","service_url","service_rate","service_expiry"};

        String client_id = (String) body.get("client_id");
        String service_type = (String) body.get("service_type");
        String service_url = (String) body.get("service_url");
        String service_rate = (String) body.get("service_rate");
        String expires_on = (String) body.get("service_expiry");
        String note = (String) body.get("service_note");

       if(!ObjectUtils.allNotNull(client_id,service_type,service_url,service_rate,expires_on)){
           throw new IncompleteDataException(requiredFields);
       }
       Optional<Client> client = clientRepo.findById(Long.parseLong(client_id));
       if(client.isPresent()){
            if(belongsToUser(userEmail,client.get())){

                try{
                    ServiceType serviceType = ServiceType.valueOf(service_type.toUpperCase(Locale.ROOT));
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    Service service = new Service(serviceType,client.get(),service_url,Float.parseFloat(service_rate),date.parse(expires_on),note);
                    client.get().setService(service);
                    clientRepo.save(client.get());
                    return service;
                }catch(ParseException p){
                    throw new RuntimeException("Bad Date format ! Accepted Format (dd/MM/yyyy) - Eg. 21/12/1990 ");
                }catch(IllegalArgumentException e){
                    System.out.println("Caught the exception of enum !");
                    throw new RuntimeException("Invalid Service Type ! Please choose from : HOST, DOMAIN, MAIL or SSL");
                }
            }else{
                throw new RuntimeException("The given client doesn't belong to the active user !");
            }
       }else{
           throw new UserNotFoundException("Client with the given id: "+client_id+ "Not found");
       }
    }

    public boolean belongsToUser(String userEmail, Client client){
        User user = client.getClientOf();
        if(user.getEmail().toLowerCase(Locale.ROOT).equals(userEmail.toLowerCase(Locale.ROOT))){
            return true;
        }else{
            return false;
        }
    }

    public List getClients(String userEmail){
        //finding the user id from user email
       User user = userRepository.findByEmail(userEmail);
        List clientMap = clientRepo.findByClientOf(user);
        return clientMap;
    }
}
