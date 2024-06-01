package com.example.ums.constraint;

import com.example.ums.validator.EmailAddressValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailAddressValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailAddressConstraint {

    String message() default "Invalid Email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
