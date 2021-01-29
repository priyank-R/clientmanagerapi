package com.springproject.clientmanager.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.Contact;
import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.exceptions.EmailAlreadyExistsException;
import com.springproject.clientmanager.exceptions.IncompleteDataException;
import com.springproject.clientmanager.repositories.UserRepository;
import com.springproject.clientmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.springproject.clientmanager.security.SecurityConstants.*;


@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/signup")
    User addNewUser(@RequestBody Map<String, Object>body) throws IncompleteDataException, EmailAlreadyExistsException{

           String name = (String) body.get("name");
           String email = (String) body.get("email");
           String password = bCryptPasswordEncoder.encode((String) body.get("password"));

           String phone = (String) body.get("phone");
           String company = (String) body.get("company");
           System.out.println(name + email + password + phone + company);
           return userService.registerUser(name,email,password,phone,company);
    }

//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody Map<String,Object> body) {
//        String email = (String) body.get("email");
//        String password = (String) body.get("password");
//        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        String token = JWT.create()
//                .withSubject((String) (body.get("email")))
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .sign(Algorithm.HMAC512(SECRET.getBytes()));
//
//        System.out.println("JWT TOKEN: "+token);
//        return ResponseEntity.ok(token);
//
//    }

    Client addNewClient(@RequestBody Map<String, Object> body) {

        String user_id = (String) body.get("user_id");
        String client_email = (String) body.get("client_email");
        String client_phone = (String) body.get("client_phone");
        String client_company = (String) body.get("client_company");

//        return userService.addNewClient(use)

        return new Client();
    }



}
