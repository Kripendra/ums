package com.example.ums.controller;

import com.example.ums.constant.EndpointConstant;
import com.example.ums.constant.MessageConstant;
import com.example.ums.dto.UserRequestDTO;
import com.example.ums.entity.User;
import com.example.ums.exception.GlobalExceptionHandler;
import com.example.ums.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(EndpointConstant.CREATE_USER)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        User user = userService.createUser(userRequestDTO);

        return ResponseEntity.ok(GlobalExceptionHandler
                .buildServerResponseWithData(true, MessageConstant.USER_CREATED, user));
    }

    @GetMapping(EndpointConstant.USER_FROM_USERNAME)
    public ResponseEntity<?> fetchUserFromUsername(@PathVariable("username") String username){
        Optional<User> userDB = userService.findByUsername(username);
        if(userDB.isPresent()){
            return ResponseEntity.ok(GlobalExceptionHandler
                    .buildServerResponseWithData(true, MessageConstant.USER_FETCHED, userDB));
        }

        return ResponseEntity.badRequest().body(GlobalExceptionHandler
                .buildServerResponse(false, "Unable to fetch user with username: " + username));
    }

    @GetMapping(EndpointConstant.USERS_FROM_FIRSTNAME)
    public ResponseEntity<?> findUsersFromFirstName(@PathVariable("firstName") String firstName, Pageable pageable) {
        Page<User> userPage = userService.findByFirstName(firstName, pageable);
        if (userPage.hasContent()){
            return ResponseEntity.ok(GlobalExceptionHandler
                    .buildServerResponseWithData(true, MessageConstant.USER_FETCHED, userPage));
        }

        return ResponseEntity.badRequest().body(GlobalExceptionHandler
                .buildServerResponse(false, "Unable to fetch user with Firstname: " + firstName));
    }

    @GetMapping(EndpointConstant.USERS_FROM_LASTNAME)
    public ResponseEntity<?> findUsersFromLastName(@PathVariable("lastName") String lastname, Pageable pageable){
        Page<User> userPage = userService.findByLastName(lastname, pageable);
        if (userPage.hasContent()){
            return ResponseEntity.ok(GlobalExceptionHandler
                    .buildServerResponseWithData(true, MessageConstant.USER_FETCHED, userPage));
        }

        return ResponseEntity.badRequest().body(GlobalExceptionHandler
                .buildServerResponse(false, "Unable to fetch user with Lastname: " + lastname));
    }

    @GetMapping(EndpointConstant.USER_FROM_EMAIL)
    public ResponseEntity<?> fetchUserFromEmail(@PathVariable("email") String email){
        Optional<User> userDB = userService.findByEmail(email);
        if (userDB.isPresent()){
            return ResponseEntity.ok(GlobalExceptionHandler
                    .buildServerResponseWithData(true, MessageConstant.USER_FETCHED, userDB));
        }

        return ResponseEntity.badRequest().body(GlobalExceptionHandler
                .buildServerResponse(false, "Unable to fetch user with email: " + email));
    }

    @PutMapping(EndpointConstant.UPDATE_USER)
    public ResponseEntity<?> updateUserDetails(@Valid @RequestBody UserRequestDTO userRequestDTO, @PathVariable("id") Long userID){
        User userDB = userService.updateUserInfo(userRequestDTO, userID);

        return ResponseEntity.ok(GlobalExceptionHandler
                .buildServerResponseWithData(true, MessageConstant.USER_DETAILS_UPDATED, userDB));
    }

    @DeleteMapping(EndpointConstant.DELETE_USER)
    public ResponseEntity<?> deleteUSer(@PathVariable("id") Long id){
        userService.deleteUserById(id);

        return ResponseEntity.ok(GlobalExceptionHandler
                .buildServerResponse(true, MessageConstant.USER_REMOVED));
    }
 }
