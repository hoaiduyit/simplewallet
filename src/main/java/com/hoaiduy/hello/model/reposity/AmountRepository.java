package com.hoaiduy.hello.model.reposity;

import com.hoaiduy.hello.model.entity.Amount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmountRepository extends CrudRepository<Amount, Integer>{

//    @Query("SELECT a.amount FROM Amount a WHERE a.state = 'RESERVED' AND a.wallet = :wallet_id")
//    List<Amount> findByWallet(Integer wallet_id);
}
