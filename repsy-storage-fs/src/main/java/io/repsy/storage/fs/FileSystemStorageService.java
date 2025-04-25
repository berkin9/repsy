package io.repsy.storage.fs;

import io.repsy.storage.api.StorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.repsy.storage.StorageService;

import java.io.IOException;
import java.nio.file.*;

@Service
@ConditionalOnProperty(name = "storage.strategy", havingValue = "file-system")
public class FileSystemStorageService implements StorageService {

    private static final Path STORAGE_ROOT = Paths.get("storage"); // storage directory auto-created

    @Override
    public void save(String packageName, String version, MultipartFile file, String fileName) throws IOException {
        Path targetDir = STORAGE_ROOT.resolve(packageName).resolve(version);
        
        // Directory existance check
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir); // Creates directories if not exists
        }

        Path targetFile = targetDir.resolve(fileName);

        // Copy file to the target location
        try {
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Failed to store file " + fileName + " in " + targetDir.toString(), e);
        }
    }

    @Override
    public byte[] load(String packageName, String version, String fileName) throws IOException {
        Path filePath = STORAGE_ROOT.resolve(packageName).resolve(version).resolve(fileName);

        // Existance check before reading
        if (Files.notExists(filePath)) {
            throw new IOException("File not found: " + filePath);
        }

        return Files.readAllBytes(filePath);
    }
}
