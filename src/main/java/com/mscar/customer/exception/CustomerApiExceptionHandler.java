package com.mscar.customer.exception;

import com.mscar.customer.utils.LogConstants;
import com.mscar.customer.model.ErrorMessage;
import com.mscar.customer.model.ErrorWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for customer api exception
 */
@ControllerAdvice
@Slf4j
public class CustomerApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle technical exception
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorWrapper> handleTechnicalException(Exception ex) {
        log.error(LogConstants.LOG_LEASEAPI_999, ex);
        List<ErrorMessage> errorMessageList = new ArrayList<>();
        errorMessageList.add(new ErrorMessage("INTERNAL_SERVER_ERROR", "Internal Server Error Occurred"));
        return new ResponseEntity<>(new ErrorWrapper(errorMessageList),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Hanlde lease api custom excpetion
     * @param ex
     * @return
     */
    @ExceptionHandler({CustomerApiException.class})
    public ResponseEntity<ErrorWrapper> handCustomerApiException(CustomerApiException ex) {
        return new ResponseEntity<>(ex.getErrors(),
                (ex.getStatus() != null ? HttpStatus.resolve(ex.getStatus()) :
                        HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * Handle database empty result data access exception
     * @param ex
     * @return
     */
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<ErrorWrapper> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
