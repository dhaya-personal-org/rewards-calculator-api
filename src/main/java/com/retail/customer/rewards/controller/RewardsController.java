package com.retail.customer.rewards.controller;

import com.retail.customer.rewards.vo.Rewards;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface RewardsController {
    @GetMapping(value="/rewards/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Rewards> getCustomerRewards(@PathVariable int customerId);

}
