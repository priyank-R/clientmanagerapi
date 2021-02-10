package com.springproject.clientmanager.security;

import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("Custom UDS invoked");
        User user = userRepository.findByEmail(s);
        if(user!=null){
            return new org.springframework.security.core.userdetails.User
                    (user.getEmail(),user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("Given username not found !");
    }
}
