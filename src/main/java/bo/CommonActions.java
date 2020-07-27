package bo;

import com.google.inject.Inject;
import config.ConfigProperties;
import factory.DriverContainer;
import io.qameta.allure.Step;

public class CommonActions {
    @Inject
    private ConfigProperties configProperties;

    @Step("open website : {url}")
    public void openWebSite() {
        DriverContainer.getDiver().get(configProperties.getBaseUrl());
    }
}
