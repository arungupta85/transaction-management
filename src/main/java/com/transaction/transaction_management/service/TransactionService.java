package com.transaction.transaction_management.service;

import com.transaction.transaction_management.dto.TransactionDTO;
import com.transaction.transaction_management.entity.Transaction;
import com.transaction.transaction_management.entity.TransactionStatus;
import com.transaction.transaction_management.mapper.TransactionMapper;
import com.transaction.transaction_management.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    public TransactionService(TransactionRepository repository, TransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
        return mapper.toDTO(transaction);
    }

    public TransactionDTO createTransaction(TransactionDTO dto) {
        Transaction transaction = mapper.toEntity(dto);
        transaction.setStatus(TransactionStatus.PENDING); // Ensure default status
        Transaction savedTransaction = repository.save(transaction);
        return mapper.toDTO(savedTransaction);
    }

    public TransactionDTO updateTransactionStatus(Long id, String status) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
        transaction.setStatus(TransactionStatus.valueOf(status.toUpperCase())); // Convert status to enum
        Transaction updatedTransaction = repository.save(transaction);
        return mapper.toDTO(updatedTransaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = repository.findAll(); // Retrieve all transactions
        return transactions.stream()
                .map(mapper::toDTO) // Convert each transaction to DTO
                .collect(Collectors.toList()); // Collect into a list of DTOs
    }
}
