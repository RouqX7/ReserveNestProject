package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.services.ValidationService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validation")
public class ValidationController {
    private final ValidationService validationService;

    @Autowired
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/validate-email")
    public ResponseEntity<String> validateEmail(@Email @RequestParam String email) {
        return validationService.isValidEmail(email) ?
                ResponseEntity.ok("Email is valid") :
                ResponseEntity.badRequest().body("Invalid email format");
    }

    @PostMapping("/validate-username")
    public ResponseEntity<String> validateUsername(@RequestParam String username) {
        return validationService.isValidUsername(username) ?
                ResponseEntity.ok("Username is valid") :
                ResponseEntity.badRequest().body("Invalid username format");
    }

    @PostMapping("/validate-password")
    public ResponseEntity<String> validatePassword(@RequestParam String password) {
        return validationService.isValidPassword(password) ?
                ResponseEntity.ok("Password is valid") :
                ResponseEntity.badRequest().body("Invalid password format");
    }
}