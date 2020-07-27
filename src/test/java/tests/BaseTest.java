package tests;

import factory.DriverFactory;
import modules.PropertyModule;
import modules.WebPageModule;
import modules.ValidatorModule;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import utils.TestAllureListeners;

@Guice(modules = { PropertyModule.class, WebPageModule.class,
        ValidatorModule.class})
@Listeners(TestAllureListeners.class)
public class BaseTest {

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
