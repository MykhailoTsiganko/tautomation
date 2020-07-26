package tests;

import bo.CartActions;
import com.google.inject.Inject;
import config.PropertyFile;
import org.testng.annotations.Test;
import validators.CartValidator;

import java.util.List;

public class GoodsInCartTest extends BaseTest {
    @Inject
    private CartActions cartActions;
    @Inject
    private CartValidator validator;

    @Test(description = "move random good to cart")
    public void moveGoodsToCart() {
        cartActions.openWebSite();
        cartActions.choseRandomGoods();
        String title = cartActions.getGoodTitle();
        cartActions.moveGoodToCart();
        cartActions.openCart();
        List<String> titleList = cartActions.getAllTitleInCart();
        validator.verifySelectedGoodIsInCart(titleList, title);
    }
}
