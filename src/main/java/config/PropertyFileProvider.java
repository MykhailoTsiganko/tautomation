package config;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Provider;
import java.util.Objects;
import java.util.Properties;

public class PropertyFileProvider implements Provider<Properties> {

    public static Properties properties;

    @Override
    public Properties get() {
        if (Objects.isNull(properties)) {
            properties = new Properties();
            PropertyFile.getProperties().forEach((key, value) -> {
                String propertyValue = System.getProperty(key.toString());
                if (StringUtils.isEmpty(propertyValue)) {
                    properties.put(key, value);
                } else {
                    properties.put(key, propertyValue);
                }
            });
        }

        return properties;
    }
}
