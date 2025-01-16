package com.sas.transaction_management.exception;

public class ApplicationExceptions {
    public static AppError USER_NOT_FOUND = new AppError("User not found", "TM_404");
    public static AppError INVALID_EMAIL = new AppError("User email already exists", "TM_400");

}
