package com.example.ums.validator;

import com.example.ums.constraint.DateOfBirthConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, String> {


    @Override
    public void initialize(DateOfBirthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {

        LocalDate givenDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (!givenDate.isAfter(LocalDate.now())){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Invalid date: " + date).addConstraintViolation();

        return false;
    }
}
