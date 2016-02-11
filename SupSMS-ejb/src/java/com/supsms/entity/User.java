/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author YemYem
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String creditCardNumber;
    private String cardCecurityCode;
    private String creditCardDate;
    private String password;
    private int lastUpdateContacts;     // Timestamp
    private boolean adminBool;
    @OneToMany(mappedBy = "user")
    private List<Contact> listContacts;
    @OneToMany(mappedBy = "user")
    private List<Invoice> listInvoice;
    @OneToOne
    private Offer offer;

    /**
     * Constructor
     */
    public User() {
        this.adminBool = false;
        this.lastUpdateContacts = 0;
        this.password = "";
        this.creditCardNumber = "";
        this.lastName = "";
        this.email = "";
        this.creditCardDate = "";
        this.cardCecurityCode = "";
        this.phoneNumber = "";
        this.firstName = "";
        this.listContacts = new ArrayList<>();
        this.listInvoice = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > 0)
            this.phoneNumber = phoneNumber;
        else throw new IllegalArgumentException("PhoneNumber is not valid.");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 0)
            this.firstName = firstName;
        else throw new IllegalArgumentException("Firstname is not valid.");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 0)
            this.lastName = lastName;
        else throw new IllegalArgumentException("Lastname is not valid.");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", email))
            this.email = email;
        else throw new IllegalArgumentException("Email is not valid.");
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        System.out.println(creditCardNumber + "credit");
        if (creditCardNumber.matches("^[0-9]{16}"))
            this.creditCardNumber = creditCardNumber;
        else throw new IllegalArgumentException("Credit Card Number is not valid.");
    }

    public String getCardCecurityCode() {
        return cardCecurityCode;
    }

    public void setCardCecurityCode(String cardCecurityCode) {
        if (cardCecurityCode.matches("^[0-9]{3,4}"))
            this.cardCecurityCode = cardCecurityCode;
        else throw new IllegalArgumentException("cardCecurityCode is not valid.");
        this.cardCecurityCode = cardCecurityCode;
    }

    public String getCreditCardDate() {
        return creditCardDate;
    }

    public void setCreditCardDate(String creditCardDate) {
        if (creditCardDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}"))
            this.creditCardDate = creditCardDate;
        else throw new IllegalArgumentException("Credit Card Date is not valid.");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() >= 6)
            this.password = password;
        else throw new IllegalArgumentException("Password is not valid.");
    }

    public int getLastUpdateContacts() {
        return lastUpdateContacts;
    }

    public void setLastUpdateContacts() {
        this.lastUpdateContacts = (int) (System.currentTimeMillis() / 1000L);
    }

    public boolean isAdminBool() {
        return adminBool;
    }

    public void setAdminBool(boolean adminBool) {
        this.adminBool = adminBool;
    }

    public List<Contact> getListContacts() {
        return listContacts;
    }

    public void setListContacts(List<Contact> listContacts) {
        this.listContacts = listContacts;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<Invoice> getListInvoice() {
        return listInvoice;
    }

    public void setListInvoice(List<Invoice> listInvoice) {
        this.listInvoice = listInvoice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "phoneNumber=" + phoneNumber + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", creditCardNumber=" + creditCardNumber + ", cardCecurityCode=" + cardCecurityCode + ", creditCardDate=" + creditCardDate + ", password=" + password + ", lastUpdateContacts=" + lastUpdateContacts + ", isAdmin=" + adminBool + '}';
    }
}