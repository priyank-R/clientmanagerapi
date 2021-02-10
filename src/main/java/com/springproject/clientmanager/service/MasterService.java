package com.springproject.clientmanager.service;

import com.springproject.clientmanager.repositories.ClientRepository;
import com.springproject.clientmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MasterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;

    public void deleteAllData(){
        userRepository.deleteAll();
        clientRepository.deleteAll();
    }

}
