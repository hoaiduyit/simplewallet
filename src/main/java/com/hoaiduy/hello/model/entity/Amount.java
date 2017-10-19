package com.hoaiduy.hello.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "amount")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Amount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amount_id")
    private Integer id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "state")
    private String state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    public Amount(Integer id, int amount, String state, Transaction transaction, Wallet wallet) {
        this.id = id;
        this.amount = amount;
        this.state = state;
        this.transaction = transaction;
        this.wallet = wallet;
    }

    public Amount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
