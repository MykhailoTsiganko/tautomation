package tests;

import bo.CartActions;
import com.google.inject.Guice;
import config.PropertyFile;
import modules.CartPageModule;
import modules.CartValidatorModule;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import validators.CartValidator;

import java.util.List;

public class GoodsInCartTest extends BaseTest {
    private CartActions cartActions;
    private CartValidator validator;

    @BeforeMethod
    public void initialize() {
        injector = Guice.createInjector(new CartPageModule());
        cartActions = injector.getInstance(CartActions.class);
        injector = Guice.createInjector(new CartValidatorModule());
        validator = injector.getInstance(CartValidator.class);
    }

    @Test(description = "move random good to cart")
    public void moveGoodsToCart() {
        cartActions.openWebSite(PropertyFile.getProperty("website"));
        cartActions.choseRandomGoods();
        String title = cartActions.getGoodTitle();
        cartActions.moveGoodToCart();
        List<String> titleList = cartActions.getAllTitleInCart();
        validator.verifySelectedGoodIsInCart(titleList, title);
    }
}
