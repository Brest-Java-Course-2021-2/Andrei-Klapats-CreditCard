package com.epam.brest.model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {

    private Integer accountId;
    private String accountData;
    private BigDecimal accountBalance;
    private Date dateOfCreate;
    private Integer clientId;

    public Account() {
    }

    public Account(Integer accountId, String accountData, BigDecimal accountBalance, Date dateOfCreate, Integer clientId) {
        this.accountId = accountId;
        this.accountData = accountData;
        this.accountBalance = accountBalance;
        this.dateOfCreate = dateOfCreate;
        this.clientId = clientId;
    }

    public Integer getAccountId() {

        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountData() {
        return accountData;
    }

    public void setAccountData(String accountData) {
        this.accountData = accountData;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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
                ", clientID=" + clientId +
                '}';
    }
}
