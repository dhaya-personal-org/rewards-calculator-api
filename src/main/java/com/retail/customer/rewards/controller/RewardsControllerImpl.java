package com.retail.customer.rewards.controller;

import com.retail.customer.rewards.service.RewardCalculationService;
import com.retail.customer.rewards.vo.Rewards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsControllerImpl implements RewardsController{
    private RewardCalculationService rewardCalculationService;
    Logger logger = LoggerFactory.getLogger(RewardsControllerImpl.class);
    @Autowired
    public  RewardsControllerImpl(RewardCalculationService rewardCalculationService) {
        this.rewardCalculationService = rewardCalculationService;
    }
    @Override
    public ResponseEntity<Rewards> getCustomerRewards(int customerId) {
        Rewards rewards = rewardCalculationService.getRewards(customerId);
        logger.info(rewards.toString());
        return new ResponseEntity(rewards,HttpStatus.OK);
    }
}
