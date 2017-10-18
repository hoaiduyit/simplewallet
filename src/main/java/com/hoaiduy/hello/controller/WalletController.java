package com.hoaiduy.hello.controller;

import com.hoaiduy.hello.representation.UserRepresentation;
import com.hoaiduy.hello.representation.WalletRepresentation;
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
    public List<UserRepresentation> getAll(){
        return walletService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public UserRepresentation getSingleClass(@PathVariable int id){
        return walletService.getUser(id);
    }

    @GetMapping("user/{id}/{user_id}")
    public WalletRepresentation getUserWallet(@PathVariable int id, @PathVariable int user_id){
        return walletService.getUserWallet(id, user_id);
    }

    @PostMapping("/transaction")
    public boolean transaction(@RequestParam("senderId") int senderId,
                               @RequestParam("recipientId") int recipientId,
                               @RequestParam("amount") int amount){
        try{
            return walletService.userTransaction(senderId, recipientId, amount);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}