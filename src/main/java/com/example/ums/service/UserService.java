package com.example.ums.service;

import com.example.ums.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User updateUserInfo(User user, Long userId);

    void deleteUserById(Long id);
}
