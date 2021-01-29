package com.springproject.clientmanager.domains;

import javax.persistence.*;
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
    private User clientOf;
//    private List<Service> services;


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
}
