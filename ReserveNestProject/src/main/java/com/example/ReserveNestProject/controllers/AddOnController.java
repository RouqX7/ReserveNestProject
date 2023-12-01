package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.services.AddOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/addons")
public class AddOnController {

    @Autowired
    private AddOnService addOnService;

    @GetMapping("/prices")
    public ResponseEntity<Map<String, Double>> getAddOnPrices() {
        Map<String, Double> addOnPrices = addOnService.getAddOnPrices();
        return ResponseEntity.ok(addOnPrices);
    }
}


