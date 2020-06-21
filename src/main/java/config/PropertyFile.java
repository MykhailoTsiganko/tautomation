package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {
    private final static String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";
    private static Properties properties = new Properties();

    private PropertyFile() {
    }

    private static void readProperty() {
        try (FileInputStream obj = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(obj);
        } catch (IOException e) {
            System.out.println("Exeption, file is missing");
        }
    }

    public static String getProperty(String s) {
        if (properties.isEmpty()) {
            readProperty();
        }
        return properties.getProperty(s);
    }
}
