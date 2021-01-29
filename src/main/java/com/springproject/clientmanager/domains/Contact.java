package com.springproject.clientmanager.domains;

import javax.persistence.*;

@Entity
@Table(name="CONTACT")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phone;
    private String company;

    public Contact() {
    }

    public Contact(String email, String phone, String company) {
        this.email = email;
        this.phone = phone;
        this.company = company;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "Id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}


