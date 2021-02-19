package com.springproject.clientmanager.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USER")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String email;
    private String name;
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL)
    @JoinColumn(name="contact",referencedColumnName = "id")
    private Contact info;

    @OneToMany(mappedBy = "clientOf",cascade = {CascadeType.ALL})
    @JsonBackReference
    private Set<Client> clients;


    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setInfo(Contact contact) {
        this.info = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
       clients.addAll(clients);
    }

    public void setClient(Client client){
        clients.add(client);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", info=" + info +
                '}';
    }
}
