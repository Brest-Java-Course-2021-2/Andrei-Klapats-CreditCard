package com.epam.brest.model.dto;

import java.math.BigDecimal;

/**
 * POJO Client for model.
 */
public class ClientDto {


    /**
     * Client Id.
     */
    private Integer clientId;
    /**
     * Client firstname.
     */
    private String firstName;
    /**
     * Client lastname.
     */
    private String lastName;
    /**
     * Client passport's date.
     */
    private String passportNumber;

    /**
     * Average balance of client's accounts.
     */
    private BigDecimal avgBalance;

    /**
     * Constructor without arguments.
     */
    public ClientDto() {

    }

    /**
     * Constructor with client's date.
     * @param firstName      client's firstname
     * @param lastName       client's lastname
     * @param passportNumber client's passport number
     */
    public ClientDto(String firstName, String lastName, String passportNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;

    }


    /**
     * Returns <code>Integer</code> representation of this clientId.
     *
     * @return clientId Client Id.
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Sets the client's identifier.
     *
     * @param clientId Client Id.
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Returns <code>String</code> representation of this firstName.
     *
     * @return firstName Client firstname.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the client's firstname.
     *
     * @param firstName Client firstname.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Returns <code>String</code> representation of this lastName.
     *
     * @return firstName Client lastname.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the client's lastname.
     *
     * @param lastName Client lastname.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Returns <code>String</code> representation of this passport.
     *
     * @return passportNumber Client passport.
     */
    public String getPassportNumber() {
        return passportNumber;
    }
    /**
     * Sets the client's passport.
     *
     * @param passportNumber Client passport.
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * Returns <code>BigDecimal</code> representation of average client's balance
     * of all them accounts
     *
     * @return avgBalance.
     */
    public BigDecimal getAvgBalance() {
        return avgBalance;
    }

    /**
     * Sets the client's average balance of accounts
     *
     * @param avgBalance Average balance.
     */
    public void setAvgBalance(BigDecimal avgBalance) {
        this.avgBalance = avgBalance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ClientDto{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", avgBalance=" + avgBalance +
                '}';
    }
}
