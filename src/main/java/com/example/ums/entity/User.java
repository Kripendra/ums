package com.example.ums.entity;


import com.example.ums.constraint.DateOfBirthConstraint;
import com.example.ums.constraint.EmailAddressConstraint;
import com.example.ums.constraint.UserNameConstraint;
import jakarta.persistence.*;


@Entity
@Table(name = "ums_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(unique = true)
    @UserNameConstraint
    private String username;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    @EmailAddressConstraint
    private String email;

    @DateOfBirthConstraint
    private String dateOfBirth;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
