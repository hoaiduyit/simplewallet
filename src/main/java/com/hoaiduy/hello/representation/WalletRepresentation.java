package com.hoaiduy.hello.representation;

import com.hoaiduy.hello.model.entity.Wallet;

public class WalletRepresentation {
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public static WalletRepresentation build(Wallet walletDetail){
        WalletRepresentation walletRepresentation = new WalletRepresentation();
        walletRepresentation.setAmount(walletDetail.getAmount());
        return walletRepresentation;
    }
}
