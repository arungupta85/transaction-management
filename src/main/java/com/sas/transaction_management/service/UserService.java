package com.sas.transaction_management.service;

import com.sas.transaction_management.dto.UserDTO;
import com.sas.transaction_management.entity.UserEntity;
import com.sas.transaction_management.exception.AppError;
import com.sas.transaction_management.exception.AppException;
import com.sas.transaction_management.exception.UserNotFoundException;
import com.sas.transaction_management.mapper.UserMapper;
import com.sas.transaction_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sas.transaction_management.exception.ApplicationExceptions.INVALID_EMAIL;
import static com.sas.transaction_management.exception.ApplicationExceptions.USER_NOT_FOUND;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO dto) {
        UserEntity userEntity = userMapper.toEntity(dto);
        userEntity.setBalance(0.0); // Ensure balance is initialized to 0.0
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.toDTO(savedEntity);
    }

    public UserDTO getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
                .orElseThrow(() -> new AppException(USER_NOT_FOUND, INVALID_EMAIL));
        return userMapper.toDTO(userEntity);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
