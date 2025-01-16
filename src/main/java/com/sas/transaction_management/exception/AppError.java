package com.sas.transaction_management.exception;

public class AppError {
    private String msg;
    private String errorCode;

    public AppError(String msg, String errorCode) {
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
