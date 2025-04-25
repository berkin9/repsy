package io.repsy.registry.controller;

import io.repsy.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/{packageName}/{version}")
public class PackageController {

    private final StorageService storageService;

    @Autowired
    public PackageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<String> upload(
            @PathVariable String packageName,
            @PathVariable String version,
            @RequestPart("package") MultipartFile packageRep,
            @RequestPart("meta") MultipartFile metaJson) {

        if (packageRep.isEmpty() || metaJson.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both files are required.");
        }

        // storageService ile kaydetme işlemi yapılır
        return ResponseEntity.status(HttpStatus.CREATED).body("Package uploaded successfully");
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<String> download(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName) {

        // storageService ile dosya yükleme yapılır
        return ResponseEntity.ok("Would return file: " + fileName);
    }
}
