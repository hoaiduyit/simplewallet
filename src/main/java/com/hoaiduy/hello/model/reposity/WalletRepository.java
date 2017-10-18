package com.hoaiduy.hello.model.reposity;

import com.hoaiduy.hello.model.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Integer> {
}
