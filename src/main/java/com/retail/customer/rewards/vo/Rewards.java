package com.retail.customer.rewards.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Rewards {
    private int customerId;
    private double firstMonthReward;
    private double secondMonthReward;
    private double thirdMonthReward;
    private double totalReward;

    @Override
    public String toString() {
        return "Rewards{" +
                "customerId=" + customerId +
                ", firstMonthReward=" + firstMonthReward +
                ", secondMonthReward=" + secondMonthReward +
                ", thirdMonthReward=" + thirdMonthReward +
                ", totalReward=" + totalReward +
                '}';
    }
}
