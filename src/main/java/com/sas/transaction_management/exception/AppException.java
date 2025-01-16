package com.sas.transaction_management.exception;

import java.util.Arrays;
import java.util.List;

public class AppException extends RuntimeException{
    private List<AppError> errors;

    public AppException(AppError... appErrors) {
        errors = Arrays.stream(appErrors).toList();
    }

    public List<AppError> getErrors() {
        return errors;
    }
}
