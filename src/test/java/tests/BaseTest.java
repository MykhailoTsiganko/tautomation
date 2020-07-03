package tests;

import bo.CartActions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.DriverFactory;
import modules.CartPageModule;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import page.CartWebPage;
import validators.CartValidator;

public class BaseTest {
    protected CartActions cartActions;
    protected CartValidator validator;

    @BeforeClass
    public void setUp() {
        Injector injector = Guice.createInjector(new CartPageModule());
        CartWebPage cartPage = injector.getInstance(CartWebPage.class);
        cartActions = new CartActions(cartPage);
        validator = new CartValidator();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
