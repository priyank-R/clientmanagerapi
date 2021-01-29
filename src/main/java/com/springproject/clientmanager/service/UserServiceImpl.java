package com.springproject.clientmanager.service;

import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.Contact;
import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.exceptions.EmailAlreadyExistsException;
import com.springproject.clientmanager.exceptions.IncompleteDataException;
import com.springproject.clientmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
    public Client addNewClient(String user_id,String client_name, String client_email, String client_phone,String client_company) {
        //fields required: user_id(id property of user table),client's name, phone, company, email

        String[] requiredFields = {"user_id","client_name","client_phone","client_email", "client_company"};

        //Check if the user exists
        Optional<User> user = userRepository.findById(Long.parseLong(user_id));

        return null;
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


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
