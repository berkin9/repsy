package io.repsy.storage.api.service;

import io.repsy.storage.api.model.DeployedPackage;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    // Deployment
    public DeployedPackage deployPackage(MultipartFile file, String packageName) throws Exception {
        DeployedPackage deployedPackage = new DeployedPackage();
        deployedPackage.setPackageName(packageName);
        deployedPackage.setFilePath("/storage/path/" + packageName);

        return deployedPackage;
    }

    // Download
    public byte[] downloadPackage(String packageName) throws Exception {
        byte[] fileData = new byte[1024];
        return fileData;
    }
}
