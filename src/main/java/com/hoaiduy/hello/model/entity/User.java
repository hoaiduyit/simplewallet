package com.hoaiduy.hello.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String user_name;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userId")
    private Wallet wallets;

    public User(int id, String user_name, Wallet wallets) {
        this.id = id;
        this.user_name = user_name;
        this.wallets = wallets;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Wallet getWallets() {
        return wallets;
    }

    public void setWallets(Wallet wallets) {
        this.wallets = wallets;
    }
}
