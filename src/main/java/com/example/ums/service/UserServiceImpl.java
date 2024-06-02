package com.example.ums.service;

import com.example.ums.Repository.UserRepository;
import com.example.ums.common.Validation;
import com.example.ums.constant.MessageConstant;
import com.example.ums.dto.UserRequestDTO;
import com.example.ums.entity.User;
import com.example.ums.exception.BadRequestException;
import com.example.ums.exception.ConstraintViolationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    public Optional<User> findByUsername(String username){return userRepository.findByUsername(username);}

    public Page<User> findByFirstName(String firstName, Pageable pageable){
        return userRepository.findByFirstName(firstName, pageable);
    }

    public Page<User> findByLastName(String lastname, Pageable pageable){
        return userRepository.findByLastName(lastname, pageable);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(UserRequestDTO userRequestDTO) {
        if (findByUsername(userRequestDTO.getUsername()).isPresent()){
            throw new ConstraintViolationException("User with username: " + userRequestDTO.getUsername() + " already exist");
        }

        if (findByEmail(userRequestDTO.getEmail()).isPresent()){
            throw new ConstraintViolationException("User with email: " + userRequestDTO.getEmail() + " already exist");
        }

        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setDateOfBirth(userRequestDTO.getDateOfBirth());

        return userRepository.save(user);
    }

    @Override
    public User updateUserInfo(UserRequestDTO userRequestDTO, Long userId) {
        Optional<User> userFromDB = userRepository.findById(userId);

        if (userFromDB.isPresent()){
            User updatedUser = new User();
            updatedUser.setUserID(userId);

            if (findByUsername(userRequestDTO.getUsername()).isEmpty()
                    || userFromDB.get().getUsername().equals(userRequestDTO.getUsername())){
                updatedUser.setUsername(userRequestDTO.getUsername());
            } else {
                throw new ConstraintViolationException("User with username: " + userRequestDTO.getUsername() + " already exist");
            }

            if ((findByEmail(userRequestDTO.getEmail()).isEmpty()
                    || userFromDB.get().getEmail().equals(userRequestDTO.getEmail()))
                    && Validation.isEmailValid(userRequestDTO.getEmail())){
                updatedUser.setEmail(userRequestDTO.getEmail());
            } else {
                throw new ConstraintViolationException("User with email: " + userRequestDTO.getEmail() + " already exist");
            }

            updatedUser.setFirstName(userRequestDTO.getFirstName());
            updatedUser.setLastName(userRequestDTO.getLastName());
            updatedUser.setDateOfBirth(userRequestDTO.getDateOfBirth());

            return userRepository.save(updatedUser);
        } else {
            throw new BadRequestException(MessageConstant.USER_NOT_FOUND);
        }
    }

    @SneakyThrows
    @Override
    public void deleteUserById(Long userId) {
        Optional<User> userDB = userRepository.findById(userId);
        if (userDB.isPresent()){
            userRepository.deleteById(userId);
        } else {
            throw new BadRequestException(MessageConstant.USER_NOT_FOUND);
        }
    }
}
