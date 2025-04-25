package io.repsy.storage.object;

import io.repsy.storage.api.StorageService;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ObjectStorageService implements StorageService {

    private final MinioClient minioClient;
    
    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.accessKey}")
    private String minioAccessKey;

    @Value("${minio.secretKey}")
    private String minioSecretKey;

    public ObjectStorageService() {
        try {
            minioClient = new MinioClient(minioUrl, minioAccessKey, minioSecretKey);
        } catch (MinioException e) {
            throw new RuntimeException("MinIO client initialization failed", e);
        }
    }

    @Override
    public void store(MultipartFile file, String fileName) {
        try {
            minioClient.putObject("bucket-name", fileName, file.getInputStream(), file.getSize(), file.getContentType());
        } catch (MinioException | IOException e) {
            throw new RuntimeException("Failed to store file to MinIO", e);
        }
    }

    @Override
    public File load(String fileName) {
        try {
            return minioClient.getObject("bucket-name", fileName);
        } catch (MinioException e) {
            throw new RuntimeException("Failed to load file from MinIO", e);
        }
    }
}
