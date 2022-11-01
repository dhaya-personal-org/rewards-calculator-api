package com.retail.customer.rewards.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErrorInfo {
    private  HttpStatus httpStatus;
    private String errorMsg;
    private String errorPath;

}
