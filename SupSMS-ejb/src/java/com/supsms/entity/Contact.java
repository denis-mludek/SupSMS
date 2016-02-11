/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author EPTR
 */
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "user_fk")
    public User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String postalAddress;
    private String email;

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 0)
            this.lastName = lastName;
        else throw new IllegalArgumentException("Lastname is not valid.");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 0)
            this.firstName = firstName;
        else throw new IllegalArgumentException("Firstname is not valid.");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > 0)
            this.phoneNumber = phoneNumber;
        else throw new IllegalArgumentException("Phone number is not valid.");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        if (postalAddress.length() > 0)
            this.postalAddress = postalAddress;
        else throw new IllegalArgumentException("Postal address is not valid.");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", email))
            this.email = email;
        else throw new IllegalArgumentException("Email is not valid.");
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", phoneNumber=" + phoneNumber + ", postalAddress=" + postalAddress + ", email=" + email + ", user=" + user + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
