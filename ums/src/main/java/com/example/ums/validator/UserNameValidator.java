package com.example.ums.validator;

import com.example.ums.Repository.UserRepository;
import com.example.ums.constraint.UserNameConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Objects;

public class UserNameValidator implements ConstraintValidator<UserNameConstraint, String> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if(!Objects.isNull(userRepository.findByUsername(username))){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Username " + username + "already exist.").addConstraintViolation();

        return false;
    }
}
