package com.example.ums.constraint;

import com.example.ums.validator.DateOfBirthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateOfBirthConstraint {

    String message() default "Invalid Date of Birth.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
