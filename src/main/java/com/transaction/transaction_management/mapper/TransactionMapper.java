package com.transaction.transaction_management.mapper;

import com.transaction.transaction_management.dto.TransactionDTO;
import com.transaction.transaction_management.entity.Transaction;
import com.transaction.transaction_management.entity.TransactionStatus;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setFromAccount(transaction.getFromAccount());
        dto.setToAccount(transaction.getToAccount());
        dto.setAmount(transaction.getAmount());
        dto.setStatus(transaction.getStatus().name()); // Convert enum to string
        return dto;
    }

    public Transaction toEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(dto.getFromAccount());
        transaction.setToAccount(dto.getToAccount());
        transaction.setAmount(dto.getAmount());

        // Default to PENDING if status is null
        if (dto.getStatus() != null) {
            transaction.setStatus(TransactionStatus.valueOf(dto.getStatus().toUpperCase()));
        } else {
            transaction.setStatus(TransactionStatus.PENDING);
        }
        return transaction;
    }
}
