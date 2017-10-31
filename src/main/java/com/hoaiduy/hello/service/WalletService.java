package com.hoaiduy.hello.service;

import com.hoaiduy.hello.model.entity.*;
import com.hoaiduy.hello.model.reposity.*;
import com.hoaiduy.hello.representation.UserRepresentation;
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

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AmountRepository amountRepository;

    @Autowired
    TraderRepository traderRepository;

    public List<UserRepresentation> getAllUser() {
        Iterable<User> user = userRepository.findAll();
        Iterator<User> userIterator = user.iterator();
        List<UserRepresentation> userList = new ArrayList<>();
        while (userIterator.hasNext()){
            userList.add(UserRepresentation.build(userIterator.next()));
        }
        return userList;
    }

    @Transactional
    public void createNewUserAndWallet(String userName, String userType, int balance) {
        User newUser = new User();
        newUser.setUser_name(userName);
        newUser.setUser_type(userType);
        userRepository.save(newUser);

        Wallet newWallet = new Wallet();
        newWallet.setBalance(balance);
        newWallet.setUserId(newUser);
        walletRepository.save(newWallet);
    }

    @Transactional
    public Transaction createTransaction() {
        Transaction transaction = new Transaction();
        transaction.setState("CREATED");
        transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    public Transaction updateTransaction(int transactionId, int senderWalletId, int recipientWalletId, int amount){
        Transaction transaction = transactionRepository.findOne(transactionId);
        Wallet senderWallet = walletRepository.findOne(senderWalletId);
        Wallet recipientWallet = walletRepository.findOne(recipientWalletId);
        if (transaction == null){
            createTransaction();
        } else {
            //create trader
            createTrader(senderWallet, recipientWallet, transaction);

            //update transaction
            transaction.setSender(senderWallet.getUserId());
            transaction.setRecipient(recipientWallet.getUserId());
            transaction.setState("PENDING");
            transaction.setAmount(amount);
            transactionRepository.save(transaction);
        }
        return transaction;
    }

    @Transactional
    public Transaction confirm(int transactionId, int amount) {
        Transaction transaction = transactionRepository.findOne(transactionId);
        if (transaction == null) {
            createTransaction();
        } else {
            //update transaction
            transaction.setState("CONFIRMED");
            transactionRepository.save(transaction);

            //create amount
            Amount senderAmount = new Amount();
            senderAmount.setState("RESERVED");
            senderAmount.setAmount(amount);

            //update trader
            updateSenderTrader(transaction, senderAmount);
            amountRepository.save(senderAmount);

            Amount recipientAmount = new Amount();
            recipientAmount.setState("RESERVED");
            recipientAmount.setAmount(amount);

            //update trader
            updateRecipientTrader(transaction, recipientAmount);
            amountRepository.save(recipientAmount);

        }
        return transaction;
    }

    private void createTrader(Wallet senderWallet, Wallet recipientWallet, Transaction transaction) {
        Trader traderSender = new Trader();
        Trader traderRecipient = new Trader();

        traderSender.setType("SENDER");
        traderSender.setWallet(senderWallet);
        transaction.setSenderTrader(traderSender);
        traderRepository.save(traderSender);

        traderRecipient.setType("RECIPIENT");
        traderRecipient.setWallet(recipientWallet);
        transaction.setRecipientTrader(traderRecipient);
        traderRepository.save(traderRecipient);
    }

    private void updateSenderTrader(Transaction transaction, Amount amount) {
        Trader trader = transaction.getSenderTrader();
        if (trader != null) {
            trader.setAmount(amount);
            traderRepository.save(trader);
        }
    }

    private void updateRecipientTrader(Transaction transaction, Amount amount) {
        Trader trader = transaction.getRecipientTrader();
        if (trader != null) {
            trader.setAmount(amount);
            traderRepository.save(trader);
        }
    }

    @Transactional
    public Transaction acceptTransaction(int transactionId) {
        Wallet senderWallet, recipientWallet;
        Amount senderAmount, recipientAmount;
        int decoy, sum = 0;
        Transaction transaction = transactionRepository.findOne(transactionId);
        if (transaction == null) {
            createTransaction();
        } else {
            if (transaction.getSenderTrader() != null && transaction.getRecipientTrader() != null){
                senderAmount = transaction.getSenderTrader().getAmount();
                recipientAmount = transaction.getRecipientTrader().getAmount();

                senderWallet = transaction.getSenderTrader().getWallet();
                recipientWallet = transaction.getRecipientTrader().getWallet();
                
                List<Trader> amountList = traderRepository.findByWallet(senderWallet);
                for (Trader sender : amountList) {
                    if (sender.getAmount().getState().equalsIgnoreCase("RESERVED")) {
                        decoy = sender.getAmount().getAmount();
                        sum += decoy;

                        int senderBalance = senderWallet.getBalance();
                        int recipientBalance = recipientWallet.getBalance();
                        if (senderBalance - sum >= 0) {
                            senderWallet.setBalance(senderBalance - senderAmount.getAmount());
                            walletRepository.save(senderWallet);

                            recipientWallet.setBalance(recipientBalance + recipientAmount.getAmount());
                            walletRepository.save(recipientWallet);

                            senderAmount.setState("SUCCESS");
                            amountRepository.save(senderAmount);
                            recipientAmount.setState("SUCCESS");
                            amountRepository.save(recipientAmount);

                            transaction.setState("SUCCESS");
                            transactionRepository.save(transaction);
                        }
                    }
                }
            }
        }
        return transaction;
    }
}
