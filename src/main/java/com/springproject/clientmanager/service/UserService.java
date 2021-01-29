package com.springproject.clientmanager.service;

import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.exceptions.EmailAlreadyExistsException;
import com.springproject.clientmanager.exceptions.IncompleteDataException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User registerUser(String name, String email, String password, String phone, String company) throws EmailAlreadyExistsException, IncompleteDataException;
    User validateUser(String email, String password);
    Boolean userExists(String email);
    Client addNewClient(String user_id,String client_name, String client_email, String client_phone,String client_company);
//    UserDetails loadByEmail(String email) throws UsernameNotFoundException;
}
