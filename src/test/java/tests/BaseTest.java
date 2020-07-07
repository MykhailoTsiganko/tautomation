package tests;

import factory.DriverFactory;
import modules.CartPageModule;
import modules.CartValidatorModule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import utils.TestAllureListeners;

@Guice(modules = {CartPageModule.class,
        CartValidatorModule.class})
@Listeners(TestAllureListeners.class)
public class BaseTest {

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
