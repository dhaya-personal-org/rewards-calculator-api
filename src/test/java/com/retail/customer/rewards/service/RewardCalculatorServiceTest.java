package com.retail.customer.rewards.service;

import com.retail.customer.rewards.dao.CustomerDao;
import com.retail.customer.rewards.dao.TransactionDao;
import com.retail.customer.rewards.exceptions.CustomerNotFoundException;
import com.retail.customer.rewards.exceptions.InternalServerException;
import com.retail.customer.rewards.model.Transaction;
import com.retail.customer.rewards.vo.Rewards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RewardCalculatorServiceTest {
    @Mock
    private CustomerDao customerDao;
    @Mock
    private TransactionDao transactionDao;
    @InjectMocks
    private RewardCalculationService rewardCalculationService;
    private static final int CUST_ID_OK = 1;
    private static final int CUST_ID_NOT_FOUND = 2;
    private static final int CUST_ID_INVALID = 3;

    @BeforeEach
    public void setUp() {
        List<Transaction> list_OK = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setTransactionTime(LocalDateTime.now().minusDays(20));
        transaction.setAmount(120);
        list_OK.add(transaction);
        transaction = new Transaction();
        transaction.setTransactionId(2);
        transaction.setTransactionTime(LocalDateTime.now().minusDays(40));
        transaction.setAmount(110);
        list_OK.add(transaction);
         transaction = new Transaction();
        transaction.setTransactionId(3);
        transaction.setTransactionTime(LocalDateTime.now().minusDays(70));
        transaction.setAmount(140);
        list_OK.add(transaction);
        Mockito.when(customerDao.existsByCustomerId(CUST_ID_OK)).thenReturn(true);
        Mockito.when(customerDao.existsByCustomerId(CUST_ID_NOT_FOUND)).thenReturn(false);
        Mockito.when(customerDao.existsByCustomerId(CUST_ID_INVALID)).thenThrow(new InternalServerException("OOPs..Invalid Input!!"));
        Mockito.when(transactionDao.findAllByCustomerIdAndTransactionTimeBetween(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(list_OK);
    }
    @Test
    public void getRewardsTest_ok() {
        Rewards  rewards = rewardCalculationService.getRewards(CUST_ID_OK);
        Assertions.assertEquals(90.0,rewards.getFirstMonthReward());
        Assertions.assertEquals(70.0,rewards.getSecondMonthReward());
        Assertions.assertEquals(130.0,rewards.getThirdMonthReward());
        Assertions.assertEquals(290.0,rewards.getTotalReward());

    }
    @Test
    public void getRewardsTest_NOT_FOUND() {
       CustomerNotFoundException customerNotFoundException = Assertions.assertThrows( CustomerNotFoundException.class, () -> {
           rewardCalculationService.getRewards(CUST_ID_NOT_FOUND);
       });
       Assertions.assertEquals("OOPs...Customer Not found!!", customerNotFoundException.getMessage());
    }

    @Test
    public void getRewardsTest_INVALID() {
        InternalServerException internalServerException = Assertions.assertThrows( InternalServerException.class, () -> {
            rewardCalculationService.getRewards(CUST_ID_INVALID);
        });
        Assertions.assertEquals("OOPs..Invalid Input!!", internalServerException.getMessage());
    }

}
