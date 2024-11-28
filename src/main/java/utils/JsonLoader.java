package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class JsonLoader {

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

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
