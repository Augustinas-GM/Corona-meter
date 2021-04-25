package com.example.corona;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String USERNAME_REGEX_PATTERN = "^[a-zA-Z0-9]{3,20}$";
    public static final String PASSWORD_REGEX_PATTERN = "^[a-zA-Z0-9.!@_]{5,20}$";
    public static final String EMAIL_REGEX_PATTERN =  "^[A-Za-z0-9._-]+@[A-Za-z0-9]+.[A-Za-z]{2,50}$";

    public static boolean isUsernameValid(String username){
        Pattern pattern = Pattern.compile(USERNAME_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(username);
        boolean isUsernameValid = matcher.find();
        return isUsernameValid;
    }

    public static boolean isPasswordValid(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(password);
        boolean isPasswordValid = matcher.find();
        return isPasswordValid;
    }

    public static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        boolean isEmailValid = matcher.find();
        return isEmailValid;
    }
}