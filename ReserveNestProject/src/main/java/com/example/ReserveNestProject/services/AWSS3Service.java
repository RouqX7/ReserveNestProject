package com.example.ReserveNestProject.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.regions.Regions;


import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.Instant;
import java.util.Date;

@Service
public class AWSS3Service {

    private final AmazonS3 s3Client;

    public AWSS3Service() {
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    public URL generatePresignedUrl(String bucketName, String objectKey, HttpMethod httpMethod, int expirationInSeconds) {
        Date expiration = Date.from(Instant.now().plusSeconds(expirationInSeconds));
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(httpMethod)
                        .withExpiration(expiration);
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }
}
