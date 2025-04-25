package io.repsy.storage.fs;

import io.repsy.storage.api.StorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
@ConditionalOnProperty(name = "storage.strategy", havingValue = "file-system")
public class FileSystemStorageService implements StorageService {

    private static final Path STORAGE_ROOT = Paths.get("storage"); // storage directory auto-created

    @Override
    public void save(String packageName, String version, MultipartFile file, String fileName) throws IOException {
        Path targetDir = STORAGE_ROOT.resolve(packageName).resolve(version);
        Files.createDirectories(targetDir);
        Path targetFile = targetDir.resolve(fileName);
        Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING); // left as placeholder
    }

    @Override
    public byte[] load(String packageName, String version, String fileName) throws IOException {
        Path filePath = STORAGE_ROOT.resolve(packageName).resolve(version).resolve(fileName);
        return Files.readAllBytes(filePath);
    }
}
