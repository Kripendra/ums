package com.example.ums.service;

import com.example.ums.Repository.UserRepository;
import com.example.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUserInfo(User user, Long userId) {
        User userDB = userRepository.findById(userId).get();

        if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())){
            userDB.setUsername(user.getUsername());
        }

        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())){
            userDB.setFirstName(user.getFirstName());
        }

        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())){
            userDB.setLastName(user.getLastName());
        }

        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())){
            userDB.setEmail(user.getEmail());
        }

        if (Objects.nonNull(user.getDateOfBirth()) && !"".equalsIgnoreCase(user.getDateOfBirth())){
            userDB.setDateOfBirth(user.getDateOfBirth());
        }

        return userRepository.save(userDB);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
