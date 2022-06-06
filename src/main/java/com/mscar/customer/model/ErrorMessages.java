package com.mscar.customer.model;

/**
 * Model for Error messages
 */
public enum ErrorMessages {

    INTERNAL_SERVER_ERROR("Internal server error occurred");

    private final String value;

    ErrorMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
