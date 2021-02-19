package com.springproject.clientmanager.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="CLIENT")
public class Client  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL)
    @JoinColumn(name="contact",referencedColumnName = "id")
    private Contact info ;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User clientOf;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Service> services = Collections.emptyList();

    public Client(){

    }

    public Client(String name, Contact info, User clientOf) {
        this.name = name;
        this.info = info;
        this.clientOf = clientOf;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getInfo() {
        return info;
    }

    public void setInfo(Contact info) {
        this.info = info;
    }

    public User getClientOf() {
        return clientOf;
    }

    public void setClientOf(User clientOf) {
        this.clientOf = clientOf;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void setService(Service service){
        this.services.add(service);
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", info=" + info +
                ", clientOf=" + clientOf +
                '}';
    }
}
