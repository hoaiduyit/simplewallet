package com.hoaiduy.hello.controller;

import com.hoaiduy.hello.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/")
    public String hello(){
        return "Hello madafaka";
    }

    @PostMapping("/user/create")
    public void createUser(@RequestParam String userName,
                           @RequestParam String userType,
                           @RequestParam int balance){
        try {
            walletService.createNewUserAndWallet(userName, userType, balance);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/transaction/create")
    public boolean createTransaction(@RequestParam int senderId,
                                  @RequestParam int recipientId,
                                  @RequestParam int amount){
        try {
            return walletService.transferMoney(senderId, recipientId, amount);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/transaction/success")
    public String acceptTransaction(@RequestParam int transactionId) {
        try{
            return walletService.acceptTransaction(transactionId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}