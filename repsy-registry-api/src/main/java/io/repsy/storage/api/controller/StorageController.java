package io.repsy.storage.api.controller;

import io.repsy.storage.api.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        storageService.store(file, fileName);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        File file = storageService.load(fileName);
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
