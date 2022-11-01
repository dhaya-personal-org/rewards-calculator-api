package com.retail.customer.rewards.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="trans_id")
    private int transactionId;
    @Column(name = "cust_id",nullable = false)
    private int customerId;
    @Column(name = "trans_amt", nullable = false)
    private double amount;
    @Column(name = "trans_time",nullable = false)
    private LocalDateTime transactionTime;

}
