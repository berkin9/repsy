package io.repsy.storage.fs;

import io.repsy.storage.api.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService implements StorageService {
    
    private final Path rootLocation = Paths.get("upload-dir");

    public FileSystemStorageService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }

    @Override
    public void store(MultipartFile file, String fileName) {
        try {
            Path destinationFile = rootLocation.resolve(Paths.get(fileName))
                    .normalize().toAbsolutePath();
            file.transferTo(destinationFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public File load(String fileName) {
        try {
            Path filePath = rootLocation.resolve(fileName).normalize().toAbsolutePath();
            return filePath.toFile();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load file", e);
        }
    }
}
