package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyFile {
    private final static String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";
    private static Properties properties;

    private PropertyFile() {
    }

    private static void loadProperties() {
        try (FileInputStream obj = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties = new Properties();
            properties.load(obj);
        } catch (IOException e) {
            System.out.println("Exeption, file is missing");
        }
    }

    public static Properties getProperties(){
        if(Objects.isNull(properties)){
            loadProperties();
        }
        return properties;
    }

    public static String getProperty(String s) {
        if (properties.isEmpty()) {
            loadProperties();
        }
        return properties.getProperty(s);
    }
}
