package com.mscar.customer.exception;

import com.mscar.customer.model.ErrorWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Customer api excpetion
 */
@AllArgsConstructor
@Data
public class CustomerApiException extends RuntimeException {
    private ErrorWrapper errors;
    private Integer status;

    public CustomerApiException(Integer status) {
        super();
        this.status = status;
    }
}
