/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author EPTR
 */
@Entity
@Table(name = "offers")
public class Offer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float price;
    private String payementFrequency;
    @Lob
    private String description;
    @OneToMany(mappedBy = "offer")
    private List<User> suscribedUsers;

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPayementFrequency() {
        return payementFrequency;
    }

    public void setPayementFrequency(String payementFrequency) {
        this.payementFrequency = payementFrequency;
    }

    public List<User> getSuscribedUsers() {
        return suscribedUsers;
    }

    public void setSuscribedUsers(List<User> suscribedUsers) {
        this.suscribedUsers = suscribedUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + id + ", name=" + name + ", price=" + price + ", payementFrequency=" + payementFrequency + '}';
    }
}
