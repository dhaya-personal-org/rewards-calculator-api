package com.retail.customer.rewards.service;

import com.retail.customer.rewards.dao.CustomerDao;
import com.retail.customer.rewards.dao.TransactionDao;
import com.retail.customer.rewards.exceptions.CustomerNotFoundException;
import com.retail.customer.rewards.model.Transaction;
import com.retail.customer.rewards.vo.Rewards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static com.retail.customer.rewards.util.RewardConstants.REWARD_1_VALUE;
import static com.retail.customer.rewards.util.RewardConstants.REWARD_2_VALUE;

/**
 * @Author : irudhayaRaja
 */
@Service
public class RewardCalculationService {
    private CustomerDao customerDao;
    private TransactionDao transactionDao;
    private static Logger logger = LoggerFactory.getLogger(RewardCalculationService.class);
    @Autowired
    public RewardCalculationService(CustomerDao customerDao, TransactionDao transactionDao ) {
        this.customerDao = customerDao;
        this.transactionDao = transactionDao;
    }

    /**
     * Fetches the customer records from the DB and calculates the rewards
     * @param custId
     * @return
     */
    public Rewards getRewards(int custId) {
        if(!customerDao.existsByCustomerId(custId)) {
            throw new CustomerNotFoundException("OOPs...Customer Not found!!");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDate threeMonthsBefore = LocalDate.now().minusDays(90);
        List<Transaction> txns = transactionDao.findAllByCustomerIdAndTransactionTimeBetween(custId, threeMonthsBefore.atTime(0,0),now);
        return calculateMonthlyRewards(custId, threeMonthsBefore, txns);

    }

    /**
     * This method calculates the rewardPoints for the given month and amount.
     * @param custId
     * @param threeMonthsBefore
     * @param txns
     * @return
     */
    private static Rewards calculateMonthlyRewards(int custId, LocalDate threeMonthsBefore, List<Transaction> txns) {
        LocalDate oneMonthBefore = LocalDate.now().minusDays(30);
        LocalDate twoMonthsBefore = LocalDate.now().minusDays(60);
        logger.info("oneMonthBefore:{}, twoMonthsBefore:{}, threeMonthsBefore:{}",oneMonthBefore,twoMonthsBefore,threeMonthsBefore);
        txns.stream().forEach(txn-> System.out.println(txn.getAmount()+":"+txn.getTransactionTime()));
        double oneMonthReward = txns.stream().filter(txn -> txn.getTransactionTime().toLocalDate().isAfter(oneMonthBefore.minusDays(1)))
                                                                .map(txn ->calculateRewardPoint(txn.getAmount())).reduce(0.0, (a,b)-> a+b);
        double secondMonthReward = txns.stream().filter(txn -> txn.getTransactionTime().toLocalDate().isAfter(twoMonthsBefore.minusDays(1))
                                                                && txn.getTransactionTime().toLocalDate().isBefore(oneMonthBefore) )
                                                                .map(txn ->calculateRewardPoint(txn.getAmount())).reduce(0.0, (a,b)-> a+b);
        double thirdMonthReward = txns.stream().filter(txn -> txn.getTransactionTime().toLocalDate().isAfter(threeMonthsBefore.minusDays(1))
                                                                && txn.getTransactionTime().toLocalDate().isBefore(twoMonthsBefore))
                                                                .map(txn ->calculateRewardPoint(txn.getAmount())).reduce(0.0, (a,b)-> a+b);
        double totalReward = txns.stream().map(txn ->calculateRewardPoint(txn.getAmount())).reduce(0.0, (a,b)-> a+b);

        return Rewards.builder().customerId(custId)
                .totalReward(totalReward)
                .firstMonthReward(oneMonthReward)
                .secondMonthReward(secondMonthReward)
                .thirdMonthReward(thirdMonthReward)
                .build();
    }

    /**
     * This method calculates the reward point for the given amount.
     * @param txnAmount
     * @return
     */
    private static double calculateRewardPoint(double txnAmount) {
        double overHundred = txnAmount>100? (txnAmount - 100) : 0;
        double overFifty = txnAmount > 50 ? (txnAmount-overHundred - 50) : 0;
        double rewardPoint = Math.floor(overFifty * REWARD_1_VALUE + overHundred * REWARD_2_VALUE);
        logger.info("Amount: {} ,RewardPoint: {}",txnAmount, rewardPoint);
        return rewardPoint;
    }


}
