package com.transaction.transaction_management.repository;


import com.transaction.transaction_management.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
