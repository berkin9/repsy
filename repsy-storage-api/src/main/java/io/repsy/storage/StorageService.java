package io.repsy.storage;

import java.io.InputStream;
import java.io.IOException;

public interface StorageService {

    void store(String packageName, String version, String fileName, InputStream content) throws IOException;

    InputStream load(String packageName, String version, String fileName) throws IOException;

    boolean exists(String packageName, String version, String fileName);
}
