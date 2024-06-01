package com.example.ums.controller;

import com.example.ums.Repository.UserRepository;
import com.example.ums.constant.EndpointConstant;
import com.example.ums.entity.User;
import com.example.ums.service.UserService;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static ConstraintValidatorContext context;

    @PostMapping(EndpointConstant.CREATE_USER)
    public User saveUser(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping(EndpointConstant.USER_FROM_USERNAME)
    public ResponseEntity<User> fetchUserFromUsername(@PathVariable("username") String username){

        User userDB = userRepository.findByUsername(username);
        if(null == userDB){
            return ResponseEntity.notFound().header("Error-Message", "Username " + username + " not found.").build();
        }

        return ResponseEntity.ok(userDB);
    }

    @GetMapping(EndpointConstant.USERS_FROM_FIRSTNAME)
    public ResponseEntity<Page<User>> findUsersFromFirstName(@PathVariable("firstName") String firstName, Pageable pageable) {
        return ResponseEntity.ok(userRepository.findByFirstName(firstName, pageable));
    }

    @GetMapping(EndpointConstant.USERS_FROM_LASTNAME)
    public ResponseEntity<Page<User>> findUsersFromLastName(@PathVariable("lastName") String lastname, Pageable pageable){
        return ResponseEntity.ok(userRepository.findByLastName(lastname, pageable));
    }

    @GetMapping(EndpointConstant.USER_FROM_EMAIL)
    public ResponseEntity<User> fetchUserFromEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userRepository.findByEmail(email));
    }

    @PutMapping(EndpointConstant.UPDATE_USER)
    public ResponseEntity<User> updateUserDetails(@RequestBody User user, @PathVariable("id") Long userID){
        return ResponseEntity.ok(userService.updateUserInfo(user, userID));
    }

    @DeleteMapping(EndpointConstant.DELETE_USER)
    public void deleteUSer(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

 }
