package com.epam.brest.model;

public class Client {

    private Integer clientId;

    private String passport;
    private String firstName;
    private String lastname;


    public Client() {
    }

    public Client(Integer clientId, String passport, String firstName, String lastname) {
        this.clientId = clientId;
        this.passport = passport;
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public Client(String passport, String firstName, String lastname) {
        this.passport = passport;
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Client setClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getPassport() {
        return passport;
    }

    public Client setPassport(String passport) {
        this.passport = passport;
        return this;
    }

    public String getFirstname() {
        return firstName;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Client setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}
