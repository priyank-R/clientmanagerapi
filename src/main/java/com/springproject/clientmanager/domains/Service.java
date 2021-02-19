package com.springproject.clientmanager.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SERVICE")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="service_type")
    private ServiceType serviceType;

    @ManyToOne
    @JoinColumn(name = "client",nullable = false)
    private Client client;

    private String url;
    private Float rate;
    @Column(name = "expires_on")
    private Date expiresOn;
    private String note;

    public Service(ServiceType serviceType, Client client, String url, Float rate, Date expiresOn, String note) {
        this.serviceType = serviceType;
        this.client = client;
        this.url = url;
        this.rate = rate;
        this.expiresOn = expiresOn;
        this.note = note;
    }

    public Service(ServiceType serviceType, Client client, String url, Float rate, Date expiresOn) {
        this.serviceType = serviceType;
        this.client = client;
        this.url = url;
        this.rate = rate;
        this.expiresOn = expiresOn;
    }

    public Service() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Service{" +
                "Id=" + Id +
                ", serviceType=" + serviceType +
                ", url='" + url + '\'' +
                ", rate=" + rate +
                ", expiresOn=" + expiresOn +
                ", note='" + note + '\'' +
                '}';
    }
}
