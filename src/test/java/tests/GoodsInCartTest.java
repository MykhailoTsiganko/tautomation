package tests;

import bo.CartActions;
import config.PropertyFile;
import factory.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import validators.CartValidator;

import java.util.List;

public class GoodsInCartTest extends BaseTest {

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
