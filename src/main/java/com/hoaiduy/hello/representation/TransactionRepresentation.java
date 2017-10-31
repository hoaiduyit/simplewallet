package com.hoaiduy.hello.representation;

import com.hoaiduy.hello.model.entity.Transaction;

public class TransactionRepresentation {

    private int transactionId;
    private String state;
    private int amount;
    private int senderId;
    private int recipientId;
    private int senderTraderId;
    private int recipientTraderId;

    public static TransactionRepresentation build (Transaction transactionDetail) {
        TransactionRepresentation transactionRepresentation = new TransactionRepresentation();
        transactionRepresentation.setTransactionId(transactionDetail.getTransaction_id());
        transactionRepresentation.setState(transactionDetail.getState());
        if (transactionDetail.getAmount() != 0){
            transactionRepresentation.setAmount(transactionDetail.getAmount());
        }
        if (transactionDetail.getSender() != null) {
            transactionRepresentation.setSenderId(transactionDetail.getSender().getId());
        }
        if (transactionDetail.getRecipient() != null){
            transactionRepresentation.setRecipientId(transactionDetail.getRecipient().getId());
        }
        if (transactionDetail.getSenderTrader() != null) {
            transactionRepresentation.setSenderTraderId(transactionDetail.getSenderTrader().getId());
        }
        if (transactionDetail.getRecipientTrader() != null){
            transactionRepresentation.setRecipientTraderId(transactionDetail.getRecipientTrader().getId());
        }
        return transactionRepresentation;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getSenderTraderId() {
        return senderTraderId;
    }

    public void setSenderTraderId(int senderTraderId) {
        this.senderTraderId = senderTraderId;
    }

    public int getRecipientTraderId() {
        return recipientTraderId;
    }

    public void setRecipientTraderId(int recipientTraderId) {
        this.recipientTraderId = recipientTraderId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getState() {
        return state;
    }

    public int getAmount() {
        return amount;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }
}
