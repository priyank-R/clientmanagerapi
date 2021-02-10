package com.springproject.clientmanager.domains;

public enum ServiceType {
    HOST("Hosting"),
    DOMAIN("Domain"),
    MAIL("Email"),
    SSL("SSL");

    String type;

    ServiceType(String type) {
        this.type=type;
    }
}
