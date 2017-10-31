package com.hoaiduy.hello.model.entity;

import org.redisson.codec.SerializationCodec;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Transaction extends SerializationCodec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transaction_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_trader_id")
    private Trader senderTrader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_trader_id")
    private Trader recipientTrader;

    @Column(name = "amount")
    private int amount;

    @Column(name = "state")
    private String state;

    public Transaction() {
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
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

    public Trader getSenderTrader() {
        return senderTrader;
    }

    public void setSenderTrader(Trader senderTrader) {
        this.senderTrader = senderTrader;
    }

    public Trader getRecipientTrader() {
        return recipientTrader;
    }

    public void setRecipientTrader(Trader recipientTrader) {
        this.recipientTrader = recipientTrader;
    }
}
