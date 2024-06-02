package com.example.ums.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final String EMAIL_REGEX = "^(?=.{1,256})(?=.{1,64}@.{1,255})" +
            "(?=.{1,64}@.{1,255})([A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
            "@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isEmailValid(String email){
            if (email == null) {
                return false;
            }
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            return matcher.matches();
    }
}
