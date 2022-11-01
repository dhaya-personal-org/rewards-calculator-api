package com.retail.customer.rewards.dao;

import com.retail.customer.rewards.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAllByCustomerId(int integers);
    List<Transaction> findAllByCustomerIdAndTransactionTimeBetween(int custId, LocalDateTime startDate, LocalDateTime endDate);

}
