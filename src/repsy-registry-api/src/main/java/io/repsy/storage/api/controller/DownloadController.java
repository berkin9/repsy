package io.repsy.storage.api.controller;

import io.repsy.storage.api.service.StorageService;
import io.repsy.storage.api.model.DeployedPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/package/{packageName}")
    public ResponseEntity<byte[]> downloadPackage(@PathVariable("packageName") String packageName) {
        try {
            byte[] fileData = storageService.downloadPackage(packageName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + packageName)
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
