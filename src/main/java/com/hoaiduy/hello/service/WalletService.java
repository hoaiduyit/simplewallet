package com.hoaiduy.hello.service;

import com.hoaiduy.hello.model.entity.User;
import com.hoaiduy.hello.model.entity.Wallet;
import com.hoaiduy.hello.model.reposity.UserRepository;
import com.hoaiduy.hello.model.reposity.WalletRepository;
import com.hoaiduy.hello.representation.UserRepresentation;
import com.hoaiduy.hello.representation.WalletRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;

    public List<UserRepresentation> getAllUser() {
        Iterable<User> userIterable = userRepository.findAll();
        Iterator<User> userIterator = userIterable.iterator();

        List<UserRepresentation> userRepresentations = new ArrayList<>();
        while (userIterator.hasNext()){
            userRepresentations.add(UserRepresentation.build(userIterator.next()));
        }
        return userRepresentations;
    }

    public UserRepresentation getUser(int id) {
        return UserRepresentation.build(userRepository.findOne(id));
    }

    public WalletRepresentation getUserWallet(int userId, int user_id){
        if (userId == user_id){
            return WalletRepresentation.build(walletRepository.findOne(user_id));
        }
        return null;
    }

    @Transactional
    public boolean userTransaction(int senderId, int recipientId, int money) {
        User sender = userRepository.findOne(senderId);
        User recipient = userRepository.findOne(recipientId);
        if (sender == null || recipient == null) return false;

        Wallet senderWallet = sender.getWallets();
        if (senderWallet == null) return false;

        Wallet recipientWallet = recipient.getWallets();
        if (recipientWallet == null) return false;

        // tru tien sender
        senderWallet.setAmount(String.valueOf(Integer.parseInt(senderWallet.getAmount()) - money));
        // cong tien recipient
        recipientWallet.setAmount(String.valueOf(Integer.parseInt(recipientWallet.getAmount()) + money));
        walletRepository.save(senderWallet);
        walletRepository.save(recipientWallet);
        return true;
    }
}
