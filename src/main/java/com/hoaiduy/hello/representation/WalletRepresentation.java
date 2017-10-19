package com.hoaiduy.hello.representation;

import com.hoaiduy.hello.model.entity.Wallet;

public class WalletRepresentation {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static WalletRepresentation build(Wallet walletDetail){
        WalletRepresentation walletRepresentation = new WalletRepresentation();
//        walletRepresentation.setAmount(walletDetail.getAmount());
        return walletRepresentation;
}
}
