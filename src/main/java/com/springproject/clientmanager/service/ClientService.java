package com.springproject.clientmanager.service;

import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.Service;

import java.util.List;
import java.util.Map;

public interface ClientService {
    Service addService(Map<String,Object> body,String userEmail);
    List getClients(String userEmail);
}
