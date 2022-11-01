package com.retail.customer.rewards.exceptions;

import com.retail.customer.rewards.vo.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody ErrorInfo handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
        return buildErrorInfo(HttpStatus.BAD_REQUEST, request, badRequestException);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerNotFoundException.class)
    public @ResponseBody ErrorInfo handleCustomerNotFoundException(HttpServletRequest request, CustomerNotFoundException customerNotFoundException) {
        return buildErrorInfo(HttpStatus.BAD_REQUEST, request, customerNotFoundException);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public @ResponseBody ErrorInfo handleInternalErrorException(HttpServletRequest request, InternalServerException ie) {
        return buildErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, request,ie);
    }

    private ErrorInfo buildErrorInfo(HttpStatus httpStatus, HttpServletRequest request, Exception ex) {
        return ErrorInfo.builder().errorMsg(ex.getMessage()).errorPath(request.getQueryString()).httpStatus(httpStatus).build();
    }
}
