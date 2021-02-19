package com.springproject.clientmanager.dto;

import com.springproject.clientmanager.domains.Service;
import com.springproject.clientmanager.domains.ServiceType;

import java.util.Date;

public class ServiceDTO {
    private Long Id;
    private ServiceType serviceType;
    private String url;
    private Float rate;
    private Date expiresOn;
    private String note;

    public ServiceDTO(Service service) {
        System.out.println("SERVICE DTO constructor invoked");
        this.Id = service.getId();
        this.serviceType = service.getServiceType();
        this.url = service.getUrl();
        this.rate = service.getRate();
        this.expiresOn = service.getExpiresOn();
        this.note = service.getNote();
    }


}
