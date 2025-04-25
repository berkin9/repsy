package io.repsy.storage.api.controller;

import io.repsy.storage.api.service.StorageService;
import io.repsy.storage.api.model.DeployedPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deploy")
public class DeploymentController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/package")
    public ResponseEntity<DeployedPackage> deployPackage(@RequestParam("file") MultipartFile file,
                                                         @RequestParam("packageName") String packageName) {
        try {
            // Deploy the package to the storage system
            DeployedPackage deployedPackage = storageService.deployPackage(file, packageName);
            return new ResponseEntity<>(deployedPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
