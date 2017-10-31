package com.hoaiduy.hello.model.entity;

import org.redisson.codec.SerializationCodec;

import javax.persistence.*;

@Entity
@Table(name = "amount")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Amount extends SerializationCodec{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amount_id")
    private Integer id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "state")
    private String state;

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
}
