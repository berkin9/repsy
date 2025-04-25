package io.repsy.storage.fs;

import io.repsy.storage.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;

@Service
@ConditionalOnProperty(name = "storage.strategy", havingValue = "file-system")
public class FileSystemStorageService implements StorageService {

    @Value("${storage.fs.base-path:/tmp/repsy}")
    private String basePath;

    @Override
    public void store(String packageName, String version, String fileName, InputStream content) throws IOException {
        Path targetPath = getPath(packageName, version, fileName);
        Files.createDirectories(targetPath.getParent());
        try (OutputStream out = Files.newOutputStream(targetPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            content.transferTo(out);
        }
    }

    @Override
    public InputStream load(String packageName, String version, String fileName) throws IOException {
        Path path = getPath(packageName, version, fileName);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File not found: " + path);
        }
        return Files.newInputStream(path);
    }

    @Override
    public boolean exists(String packageName, String version, String fileName) {
        return Files.exists(getPath(packageName, version, fileName));
    }

    private Path getPath(String packageName, String version, String fileName) {
        return Paths.get(basePath, packageName, version, fileName);
    }
}
