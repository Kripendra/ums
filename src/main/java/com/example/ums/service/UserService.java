package com.example.ums.service;

import com.example.ums.dto.UserRequestDTO;
import com.example.ums.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Page<User> findByFirstName(String firstName, Pageable pageable);

    Page<User> findByLastName(String lastname, Pageable pageable);

    Optional<User> findByEmail(String email);

    User createUser(UserRequestDTO userRequestDTO);

    User updateUserInfo(UserRequestDTO userRequestDTO, Long userId);

    void deleteUserById(Long id);
}
