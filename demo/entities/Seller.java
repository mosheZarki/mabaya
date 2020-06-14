package com.example.demo.entities;

import lombok.Data;

import javax.annotation.ManagedBean;
import javax.persistence.*;
import java.util.List;

@Entity
@ManagedBean
@Table(name = "SELLER")
public class Seller {
    // could add inheritance - didnt for simplicity.
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "seller_id")
    Long id;

    @Column(name = "full_name")
    String fullName;

    public Seller(String fullName){
        this.fullName = fullName;
    }
    public Seller(){}

    public Long getId() {
        return id;
    }
}
