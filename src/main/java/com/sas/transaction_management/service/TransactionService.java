package com.sas.transaction_management.service;

import com.sas.transaction_management.dto.TransactionDTO;
import com.sas.transaction_management.entity.Transaction;
import com.sas.transaction_management.entity.TransactionStatus;
import com.sas.transaction_management.exception.InvalidTransactionException;
import com.sas.transaction_management.mapper.TransactionMapper;
import com.sas.transaction_management.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    private static final Map<TransactionStatus, List<TransactionStatus>> VALID_TRANSITIONS = Map.of(
            TransactionStatus.PENDING, Arrays.asList(TransactionStatus.SUCCESS, TransactionStatus.FAILURE),
            TransactionStatus.SUCCESS, Collections.emptyList(),
            TransactionStatus.FAILURE, Collections.singletonList(TransactionStatus.REFUND),
            TransactionStatus.REFUND, Collections.emptyList()
    );

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
        transaction.setStatus(TransactionStatus.PENDING);
        Transaction savedTransaction = repository.save(transaction);
        return mapper.toDTO(savedTransaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO updateTransactionStatus(Long id, TransactionStatus newStatus) {
        Transaction entity = repository.findById(id)
                .orElseThrow(() -> new InvalidTransactionException("Transaction with ID " + id + " not found"));

        // Validate the status transition
        validateStateTransition(entity.getStatus(), newStatus);

        // Update the status and save the entity
        entity.setStatus(newStatus);
        Transaction updatedEntity = repository.save(entity);

        // Return the updated DTO
        return mapper.toDTO(updatedEntity);
    }

    private void validateStateTransition(TransactionStatus currentStatus, TransactionStatus newStatus) {
        List<TransactionStatus> allowedTransitions = VALID_TRANSITIONS.getOrDefault(currentStatus, Collections.emptyList());
        if (!allowedTransitions.contains(newStatus)) {
            throw new InvalidTransactionException(String.format(
                    "Invalid transition: Cannot change status from %s to %s.",
                    currentStatus, newStatus
            ));
        }
    }
}
