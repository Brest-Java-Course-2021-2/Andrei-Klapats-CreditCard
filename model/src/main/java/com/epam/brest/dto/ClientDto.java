package com.epam.brest.dto;

import java.math.BigDecimal;

public class ClientDto {

    private Integer clientId;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private BigDecimal accountBalance;


    public ClientDto() {

    }


    public ClientDto(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }


    @Override
    public String toString() {
        return "ClientDto{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
