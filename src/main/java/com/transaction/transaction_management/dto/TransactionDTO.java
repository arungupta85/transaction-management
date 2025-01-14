package com.transaction.transaction_management.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long id;
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private String status; // Status as a string (e.g., PENDING, SUCCESS, FAILURE)



//    public TransactionDTO(Long id, String fromAccount, String toAccount, Double amount, String status) {
//        this.id = id;
//        this.fromAccount = fromAccount;
//        this.toAccount = toAccount;
//        this.amount = amount;
//        this.status = status;
//    }

    public TransactionDTO() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
