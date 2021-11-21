package com.epam.brest;

public class Account {
    private Integer accountId;
    private String accountData;
    private Integer accountBalance;
    private Integer clientID;

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
}
