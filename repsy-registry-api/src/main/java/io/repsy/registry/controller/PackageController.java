package io.repsy.registry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/{packageName}/{version}")
public class PackageController {

    @PostMapping
    public ResponseEntity<String> upload(
            @PathVariable String packageName,
            @PathVariable String version,
            @RequestPart("package") MultipartFile packageRep,
            @RequestPart("meta") MultipartFile metaJson) {

        // Placeholder: Dosya doğrulama ve kaydetme burada yapılacak
        if (packageRep.isEmpty() || metaJson.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both files are required.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Package uploaded successfully");
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<String> download(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName) {

        // Placeholder: Dosya getirme işlemi burada yapılacak
        return ResponseEntity.ok("Would return file: " + fileName);
    }
} 