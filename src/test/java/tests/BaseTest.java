package tests;

import com.google.inject.Injector;
import factory.DriverFactory;
import org.testng.annotations.AfterClass;


public class BaseTest {
    protected Injector injector;


    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
