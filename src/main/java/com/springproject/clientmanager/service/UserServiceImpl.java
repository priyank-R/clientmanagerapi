package com.springproject.clientmanager.service;

import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.Contact;
import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.exceptions.EmailAlreadyExistsException;
import com.springproject.clientmanager.exceptions.IncompleteDataException;
import com.springproject.clientmanager.exceptions.UserNotFoundException;
import com.springproject.clientmanager.repositories.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.lang.reflect.Array;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(String name, String email, String password, String phone, String company) throws EmailAlreadyExistsException, IncompleteDataException {
        //Validation logic
        String[] requiredFields = {"name","email","password","phone","company"};

            if(userExists(email)){
                System.out.println("gets in email already exists thing !");
                throw new EmailAlreadyExistsException();
            }
            if(name!=null && email!=null && password != null && phone!=null && company!=null){
                User user  = new User(name, email,password);
                Contact contact  = new Contact(email,phone,company);
                user.setInfo(contact);
                return userRepository.save(user);
            }else {
                System.out.println("Incomplete Data Exception triggered !");
                throw new IncompleteDataException(requiredFields);
            }
        }

    @Override
    public Client addNewClient(String user_email,String client_name, String client_email, String client_phone,String client_company) {
        String[] requiredFields = {"user_id","client_name","client_phone","client_email", "client_company"};
        if(!ObjectUtils.allNotNull(user_email,client_email,client_email,client_phone,client_company)){
            throw new IncompleteDataException(requiredFields);
        }
        //Check if the user exists
        User user = userRepository.findByEmail(user_email);
        System.out.println(user);
        if(user!=null){
            try{
                Contact contact = new Contact(client_email,client_phone,client_company);
                Client client = new Client(client_name,contact,user);
                user.setClient(client);
                userRepository.save(user);
                return client;
            }catch(JpaSystemException jpaE){
                throw new RuntimeException(jpaE);

            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("New Exception!");
            throw new UserNotFoundException(user_email);
        }
    }
    //** WARNING **
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @Override
    public User validateUser(String email, String password) {
        return null;
    }

    @Override
    public Boolean userExists(String email) {
        User userExists =  userRepository.findByEmail(email);
        System.out.println("Result of findByEmail: "+ userExists);
        if(userExists==null){
            return false;
        }
        return true;
    }
}
