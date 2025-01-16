package com.sas.transaction_management.dto;

import lombok.Data;

@Data
public class TransactionStatusUpdateDTO {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // The new status to update

    public TransactionStatusUpdateDTO(String status) {
        this.status = status;
    }
}

