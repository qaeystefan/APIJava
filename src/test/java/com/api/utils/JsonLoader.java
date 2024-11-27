package com.api.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonLoader {

    public static String loadJson(String fileName) {
        try {
            String filePath = "src/test/resources/" + fileName;
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JSON file: " + fileName, e);
        }
    }
}
