package config;


import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ConfigProperties {
    @Inject
    @Named("baseUrl")
    private String baseUrl;
    @Inject
    @Named("browserName")
    private String browserName;
    @Inject
    @Named("browserType")
    private String browserType;
    @Inject
    @Named("remoteDriver")
    private Boolean remoteDriver;

    public Boolean getRemoteDriver() {
        return remoteDriver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserType() {
        return browserType;
    }
}
