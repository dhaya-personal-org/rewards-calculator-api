package com.retail.customer.rewards.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cust_id")
    private int customerId;
    @Column(name = "cust_name",nullable = false)
    private String  customerName;

}
