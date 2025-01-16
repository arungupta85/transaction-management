package com.sas.transaction_management.mapper;

import com.sas.transaction_management.dto.UserDTO;
import com.sas.transaction_management.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);
}
