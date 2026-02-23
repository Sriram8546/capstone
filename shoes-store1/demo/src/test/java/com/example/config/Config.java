package com.example.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Application and test configuration for KICKS Shoes Store.
 */
public final class Config {
    public static final String BASE_URL;
    public static final long IMPLICIT_WAIT_SECONDS = 10;
    public static final long PAGE_LOAD_TIMEOUT_SECONDS = 30;

    static {
        // Resolve shoes-store path relative to demo project (demo/../shoes-store)
        Path demoDir = Paths.get(System.getProperty("user.dir"));
        Path storePath = demoDir.getParent().resolve("shoes-store");
        File storeDir = storePath.toFile();
        if (!storeDir.exists()) {
            storeDir = demoDir.resolve("..").resolve("shoes-store").normalize().toFile();
        }
        if (!storeDir.exists()) {
            storeDir = demoDir.resolve("shoes-store").toFile();
        }
        String path = storeDir.getAbsolutePath().replace("\\", "/");
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        BASE_URL = "file://" + path + "/";
    }

    private Config() {}
}
