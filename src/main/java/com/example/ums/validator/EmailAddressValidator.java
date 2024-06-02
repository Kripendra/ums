package com.example.ums.validator;

import com.example.ums.Repository.UserRepository;
import com.example.ums.common.Validation;
import com.example.ums.constraint.EmailAddressConstraint;
import com.example.ums.exception.BadRequestException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;



public class EmailAddressValidator implements ConstraintValidator<EmailAddressConstraint, String> {

    @Autowired
    UserRepository userRepository;


    @Override
    public void initialize(EmailAddressConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (!Validation.isEmailValid(email)){
            throw new BadRequestException(email + "is not a valid email address");
        }

        return userRepository.findByEmail(email).isEmpty();
    }
}
