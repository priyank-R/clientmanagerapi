package com.springproject.clientmanager.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.gson.JsonObject;
import com.springproject.clientmanager.domains.*;
import com.springproject.clientmanager.dto.ClientDTO;
import com.springproject.clientmanager.dto.ServiceDTO;
import com.springproject.clientmanager.exceptions.EmailAlreadyExistsException;
import com.springproject.clientmanager.exceptions.IncompleteDataException;
import com.springproject.clientmanager.helpers.JsonUtility;
import com.springproject.clientmanager.helpers.JwtUtil;
import com.springproject.clientmanager.repositories.UserRepository;
import com.springproject.clientmanager.security.UserDetailsServiceImpl;
import com.springproject.clientmanager.service.ClientService;
import com.springproject.clientmanager.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;

import java.util.*;


@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
    private final ClientService clientService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    public UserController(UserService userService,
                         ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }



    @PostMapping("/signup")
    public ResponseEntity<?> addNewUser(@RequestBody Map<String, Object>body) throws IncompleteDataException, EmailAlreadyExistsException{

           String name = (String) body.get("name");
           String email = (String) body.get("email");
           String password = (String) body.get("password");


           String phone = (String) body.get("phone");
           String company = (String) body.get("company");
           System.out.println(name + email + password + phone + company);
           User user =  userService.registerUser(name,email,password,phone,company);

           //converting to json and appending property
           JsonNode json = JsonUtility.addPropertyToObject(user,"status","success");
           return ResponseEntity.ok(json);
    }


    @GetMapping
    public ResponseEntity<?> getUser(@RequestHeader("Authorization")String token){
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        JsonObject jsonObject = JsonUtility.buildJsonObject("email",user);

        JsonNode json = JsonUtility.addPropertyToObject(jsonObject,"status","success");
        return ResponseEntity.ok(json);
    }

    @PostMapping("/addclient")
    ResponseEntity<?> addNewClient(@RequestBody Map<String, Object> body) {
        try{
            System.out.println("Gets into /addclient route");
            String client_name = (String) body.get("client_name");
            String client_email = (String) body.get("client_email");
            String client_phone = (String) body.get("client_phone");
            String client_company = (String) body.get("client_company");

            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client =  userService.addNewClient(userEmail, client_name, client_email, client_phone, client_company);
            ClientDTO clientDto = new ClientDTO(client);
            JsonNode json = JsonUtility.addPropertyToObject(clientDto,"status","success");
            return ResponseEntity.ok(json);
        }catch(Exception e){
            System.out.println("add client route stack trace");
            e.printStackTrace();
            return null;

        }


    }
    @GetMapping("/getclients")
    ResponseEntity getClients(){

        System.out.println("/getclients route invoked !");
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ClientDTO> clientDto = new ArrayList<>();
        List<Client> clientMap = clientService.getClients(userEmail);
        System.out.println("Client map: " +  clientMap.toString());


        for(Client clt : clientMap){
//           clientDto.add(new ClientDTO(clt.getName(),clt.getInfo().getEmail(),clt.getId()));
            clientDto.add(new ClientDTO(clt));
        }
        System.out.println("client dto list is:  " + clientDto );
       Map map = new HashMap();

       map.put("clients",clientDto);
        JsonNode json = JsonUtility.addPropertyToObject(map,"status","success");

        return ResponseEntity.ok(json);
//        return ResponseEntity.ok(new SuccessResponse(map));

    }

    @PostMapping("/addservice")
    ResponseEntity<?> addService(@RequestBody Map<String,Object>  body, @RequestHeader("Authorization") String token){

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Service service =  clientService.addService(body,userEmail);
        ServiceDTO serviceDTO = new ServiceDTO(service);
        JsonNode jsonString = JsonUtility.addPropertyToObject(serviceDTO,"status","success");
        return ResponseEntity.ok(jsonString);
    }

    @PostMapping("/signin")
    ResponseEntity loginUser(@RequestBody AuthorizationRequest authRequest){
        System.out.println("Inside the signin route !");
        String email = authRequest.getUsername();
        String password = authRequest.getPassword();

        //for returning jwt !
        //1. Use authentication manager to validate the given credentials using the newly created usernamepassauthtoken
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
        }catch(BadCredentialsException e){
            throw new RuntimeException("Bad Credentials !");
        }

        //2. Once successfully authenticated, construct a jwt and add it in the response body
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String jwt = jwtUtil.generateToken(userDetails);
        HashMap map = new HashMap();
        map.put("token",jwt);
        return ResponseEntity.ok(new SuccessResponse(map));
    }

}
