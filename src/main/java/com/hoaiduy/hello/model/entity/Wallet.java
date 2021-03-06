package com.hoaiduy.hello.model.entity;

import org.redisson.codec.SerializationCodec;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Wallet extends SerializationCodec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Integer id;

    @Column(name = "balance")
    private int balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    public Wallet() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
