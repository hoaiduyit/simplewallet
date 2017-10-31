package com.hoaiduy.hello.controller;

import com.hoaiduy.hello.model.entity.Transaction;
import com.hoaiduy.hello.representation.TransactionRepresentation;
import com.hoaiduy.hello.representation.UserRepresentation;
import com.hoaiduy.hello.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/")
    public String hello(){
        return "Hello madafaka";
    }

    @GetMapping("/user")
    public List<UserRepresentation> getUsers(){
        try{
            return walletService.getAllUser();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
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

    @PostMapping("/transaction")
    public TransactionRepresentation createTransaction(){
        try {
            Transaction t = walletService.createTransaction();
            return TransactionRepresentation.build(t);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/transaction/{transaction_id}")
    public TransactionRepresentation updateTransaction(@PathVariable("transaction_id") int transactionId,
                                     @RequestParam int senderWalletId,
                                     @RequestParam int recipientWalletId,
                                     @RequestParam int amount) {
        try {
            Transaction t =  walletService.updateTransaction(transactionId, senderWalletId, recipientWalletId, amount);
            return TransactionRepresentation.build(t);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("transaction/{transaction_id}/confirm")
    public TransactionRepresentation confirmTransaction(@PathVariable("transaction_id") int transactionId,
                                                        @RequestParam int amount) {
        try {
            Transaction t = walletService.confirm(transactionId, amount);
            return TransactionRepresentation.build(t);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/transaction/{transaction_id}/success")
    public TransactionRepresentation acceptTransaction(@PathVariable("transaction_id") int transactionId) {
        try{
            Transaction t = walletService.acceptTransaction(transactionId);
            return TransactionRepresentation.build(t);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}