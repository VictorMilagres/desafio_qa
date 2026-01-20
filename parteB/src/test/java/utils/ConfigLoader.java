package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties props = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            props.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível carregar o arquivo config.properties");
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}