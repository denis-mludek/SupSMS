/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author EPTR
 */
@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "user_fk")
    public User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float amount;
    private int timestampDate;

    public Invoice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getTimestampDate() {
        return timestampDate;
    }

    public String getDateFromTimestamp() {
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis((long) this.timestampDate * 1000);
        return mydate.get(Calendar.DAY_OF_MONTH) + "." + mydate.get(Calendar.MONTH) + "." + mydate.get(Calendar.YEAR);
    }

    public void setTimestampDate() {
        this.timestampDate = (int) (System.currentTimeMillis() / 1000L);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
