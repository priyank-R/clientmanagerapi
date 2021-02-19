package com.springproject.clientmanager.dto;

import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientDTO {
    private String name;
    private String email;
    private List<ServiceDTO> services = new ArrayList<>();
    private Long id;

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.email = client.getInfo().getEmail();
        this.id = client.getId();

//        List<Service> services = client.getServices();
        if(client.getServices().size() == 0){
            return;
        }else {

            for (Service srv : client.getServices()) {
                this.services.add(new ServiceDTO(srv));
            }
        }
    }

    public ClientDTO(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
