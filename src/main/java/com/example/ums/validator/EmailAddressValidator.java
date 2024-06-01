package com.example.ums.validator;

import com.example.ums.Repository.UserRepository;
import com.example.ums.constraint.EmailAddressConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class EmailAddressValidator implements ConstraintValidator<EmailAddressConstraint, String> {


    @Autowired
    UserRepository userRepository;


    @Override
    public void initialize(EmailAddressConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (!EmailValidator.getInstance().isValid(email)){
            buildConstraintViolation(context, "Email: " + email + "is not valid.");
        }

        if (!Objects.isNull(userRepository.findByEmail(email))){
            return true;
        } else {
            buildConstraintViolation(context, "Email address: " + email + " already exist.");
        }

        return false;
    }

    private void buildConstraintViolation(ConstraintValidatorContext context, String message){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
