package com.example.ums.constraint;

import com.example.ums.validator.UserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UserNameConstraint {

    String message() default "Given username already exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
