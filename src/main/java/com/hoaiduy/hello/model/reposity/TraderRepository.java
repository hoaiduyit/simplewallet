package com.hoaiduy.hello.model.reposity;

import com.hoaiduy.hello.model.entity.Trader;
import com.hoaiduy.hello.model.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraderRepository extends CrudRepository<Trader, Integer> {
    List<Trader> findByWallet(Wallet wallet);
}
