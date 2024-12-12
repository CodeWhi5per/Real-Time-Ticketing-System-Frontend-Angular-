package com.example.Real.Time.Ticketing.System.domain;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name="transactions")
@NamedQuery(name="Transactions.findAll", query="SELECT c FROM Transactions c")
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="transaction_message")
    private String transactionMessage;

    public Transactions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }
}
