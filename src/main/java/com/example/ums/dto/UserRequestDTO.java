package com.example.ums.dto;

import com.example.ums.constraint.DateOfBirthConstraint;
import com.example.ums.constraint.EmailAddressConstraint;
import com.example.ums.constraint.UserNameConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDTO {

    @UserNameConstraint
    private String username;

    private String firstName;

    private String lastName;

    @EmailAddressConstraint
    private String email;

    @DateOfBirthConstraint
    private LocalDate dateOfBirth;
}
