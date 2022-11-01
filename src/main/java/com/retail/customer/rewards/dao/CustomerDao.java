package com.retail.customer.rewards.dao;

import com.retail.customer.rewards.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {
    public Customer findByCustomerId(int custId);
    public boolean existsByCustomerId(int custId);
}
