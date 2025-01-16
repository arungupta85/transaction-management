package com.sas.transaction_management.mapper;

import com.sas.transaction_management.dto.TransactionDTO;
import com.sas.transaction_management.entity.Transaction;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDTO(Transaction transaction);
    Transaction toEntity(TransactionDTO dto);
}