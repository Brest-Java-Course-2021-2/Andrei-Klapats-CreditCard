package com.epam.brest.model;

import java.util.Date;

public class Account {

    private Integer accountId;
    private String accountData;
    private Integer accountBalance;
    private Date dateOfCreate;
    private Integer clientID;

    public Account() {
    }

    public Account(Integer accountId, String accountData, Integer accountBalance, Date dateOfCreate, Integer clientID) {
        this.accountId = accountId;
        this.accountData = accountData;
        this.accountBalance = accountBalance;
        this.dateOfCreate = dateOfCreate;
        this.clientID = clientID;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountData() {
        return accountData;
    }

    public void setAccountData(String accountData) {
        this.accountData = accountData;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Date getDateOfCreate() { return dateOfCreate;}

    public void setDateOfCreate(Date dateOfCreate) { this.dateOfCreate = dateOfCreate;}


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountData='" + accountData + '\'' +
                ", accountBalance=" + accountBalance +
                ", dateOfCreate=" + dateOfCreate +
                ", clientID=" + clientID +
                '}';
    }
}
