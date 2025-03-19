package com.nagp.products.service.impl;

import com.nagp.products.service.S3Service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
@Service
public class S3ServiceImpl implements S3Service {
    @Value("aws-secret.access-key")
    private String awsAccessKeyId;

    @Value("aws-secret.access-key-id")
    private String awsSecretAccessKey;

    private String bucketName="ecomm-nagp-bucket";

    private S3Client s3Client;

    @PostConstruct
    public void init() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(awsAccessKeyId, awsSecretAccessKey);
        s3Client = S3Client.builder()
                .region(Region.AP_SOUTH_1)  // Replace with your AWS region
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
@Override
    public String uploadFile(MultipartFile file) throws IOException {
        // Generate a unique file name
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Convert MultipartFile to a temporary File
        Path tempFile = Files.createTempFile(fileName, null);
        file.transferTo(tempFile.toFile());

        try {
            // Upload the file to S3
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjectRequest, tempFile);

            // Generate the file URL
            URL fileUrl = s3Client.utilities().getUrl(GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build());

            // Delete the temporary file after upload
            Files.delete(tempFile);

            return fileUrl.toString();
        } catch (S3Exception e) {
            throw new IOException("Error uploading file to S3: " + e.awsErrorDetails().errorMessage(), e);
        }
    }

}
