package com.example.ReserveNestProject.controllers;


import com.amazonaws.HttpMethod;
import com.example.ReserveNestProject.services.AWSS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    @Autowired
    private AWSS3Service awss3Service;

    @PostMapping("/generate-presigned-url")
    public ResponseEntity<?> generatePresignedUrl(@RequestBody Map<String, String> requestBody) {
        String fileName = requestBody.get("fileName");
        String bucketName = "reservenestbucket";
        String fileKey = "uploads/" + fileName;

        URL url = awss3Service.generatePresignedUrl(bucketName, fileKey, HttpMethod.PUT, 3600); // 1 hour expiration

        return ResponseEntity.ok(url.toString());
    }
}
