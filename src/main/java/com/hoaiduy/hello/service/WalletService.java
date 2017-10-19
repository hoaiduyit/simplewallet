package com.hoaiduy.hello.service;

import com.hoaiduy.hello.model.entity.Amount;
import com.hoaiduy.hello.model.entity.Transaction;
import com.hoaiduy.hello.model.entity.User;
import com.hoaiduy.hello.model.entity.Wallet;
import com.hoaiduy.hello.model.reposity.AmountRepository;
import com.hoaiduy.hello.model.reposity.TransactionRepository;
import com.hoaiduy.hello.model.reposity.UserRepository;
import com.hoaiduy.hello.model.reposity.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean transferMoney(int senderId, int recipientId, int amount){
        User sender = userRepository.findOne(senderId);
        User recipient = userRepository.findOne(recipientId);
        if (sender == null || recipient == null){
            return false;
        } else {
            Transaction transaction = new Transaction();
            transaction.setSender(sender);
            transaction.setRecipient(recipient);
            transaction.setState("CREATED");
            transaction.setAmount(amount);
            transactionRepository.save(transaction);

            Wallet wallet = walletRepository.findOne(senderId);
            Amount amountIn = new Amount();
            amountIn.setTransaction(transaction);
            amountIn.setWallet(wallet);
            amountIn.setAmount(amount);
            amountIn.setState("RESERVED");
            amountRepository.save(amountIn);
        }
        return true;
    }

    @Transactional
    public String acceptTransaction(int transactionId) {
        Transaction transaction = transactionRepository.findOne(transactionId);
        if (transaction == null) {
            return null;
        } else {
            Amount amount = amountRepository.findByTransaction(transaction);
            if (amount.getState().equals("RESERVED")){

                Wallet walletSender = walletRepository.findOne(amount.getWallet().getId());
                Wallet walletRecipient = walletRepository.findOne(transaction.getRecipient().getId());
                int balance = walletSender.getBalance();
                int balanceRecipient = walletRecipient.getBalance();
                int amountIn = amount.getAmount();
                if ((balance - amountIn) >= 0){
                    walletSender.setBalance(balance - amountIn);
                    walletRepository.save(walletSender);

                    walletRecipient.setBalance(balanceRecipient + amountIn);
                    walletRepository.save(walletRecipient);

                    amount.setState("SUCCESSFUL");
                    amountRepository.save(amount);

                    transaction.setState("SUCCESSFUL");
                    transactionRepository.save(transaction);

                    return "Transfer money successful";
                }
                return "Transfer money fail";
            }
            return "Transfer money fail, no transaction was created";
        }
    }
}
