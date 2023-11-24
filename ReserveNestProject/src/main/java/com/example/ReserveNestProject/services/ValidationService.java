package com.example.ReserveNestProject.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        // Check if the email format is valid
        if (!matcher.matches()) {
            return false;
        }

        return email.contains("@");

        // If both conditions are satisfied, the email is valid
    }

    public boolean isValidUsername(String username) {
        String usernameRegex = "^[a-zA-Z0-9_]{3,16}$";
        return username.matches(usernameRegex);
    }

    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(passwordRegex);
    }
}
