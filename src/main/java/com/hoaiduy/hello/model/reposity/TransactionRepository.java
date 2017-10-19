package com.hoaiduy.hello.model.reposity;

import com.hoaiduy.hello.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
}
