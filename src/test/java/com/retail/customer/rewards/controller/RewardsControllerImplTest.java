package com.retail.customer.rewards.controller;

import com.retail.customer.rewards.exceptions.CustomerNotFoundException;
import com.retail.customer.rewards.exceptions.InternalServerException;
import com.retail.customer.rewards.service.RewardCalculationService;
import com.retail.customer.rewards.vo.Rewards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsControllerImplTest {
    @Autowired
    WebTestClient webClient;
    @MockBean
    RewardCalculationService rewardCalculationService;
    private static final int CUST_ID_OK = 1;
    private static final int CUST_ID_NOT_FOUND = 2;
    private static final int CUST_ID_INVALID = 3;

    @BeforeEach
    public void setUp() {
        Mockito.when(rewardCalculationService.getRewards(CUST_ID_OK))
                .thenReturn( Rewards.builder().customerId(CUST_ID_OK).totalReward(120)
                        .firstMonthReward(40).secondMonthReward(60).thirdMonthReward(20).build());
        Mockito.when(rewardCalculationService.getRewards(CUST_ID_NOT_FOUND)).thenThrow(new CustomerNotFoundException("OOPs...Customer Not found!!"));
        Mockito.when(rewardCalculationService.getRewards(CUST_ID_INVALID)).thenThrow(new InternalServerException("OOPs..Invalid Input!!"));
    }

    @Test
    public void getCustomerRewards_success() {
        getRewardResponse(CUST_ID_OK, HttpStatus.OK)
                .jsonPath("$.customerId").isEqualTo("1")
                .jsonPath("$.totalReward").isEqualTo("120.0");


    }
    @Test
    public void getCustomerRewards_NOT_FOUND() {
        getRewardResponse(CUST_ID_NOT_FOUND, HttpStatus.BAD_REQUEST)
                .jsonPath("$.httpStatus").isEqualTo("BAD_REQUEST")
                .jsonPath("$.errorMsg").isEqualTo("OOPs...Customer Not found!!");


    }
    @Test
    public void getCustomerRewards_INVALID_INPUT() {
        getRewardResponse(CUST_ID_INVALID, HttpStatus.INTERNAL_SERVER_ERROR)
                .jsonPath("$.httpStatus").isEqualTo("INTERNAL_SERVER_ERROR")
                .jsonPath("$.errorMsg").isEqualTo("OOPs..Invalid Input!!");


    }
    private WebTestClient.BodyContentSpec getRewardResponse(int custId, HttpStatus expectedStatus) {
        return webClient.get().uri("/rewards/"+custId).accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody();
    }
}
