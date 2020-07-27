package bo;

import com.google.inject.Inject;
import elements.PageElement;
import io.qameta.allure.Step;
import page.CartWebPage;

import java.util.List;
import java.util.stream.Collectors;

public class CartActions {
    @Inject
    private CartWebPage cartPage;

    @Step("select random good from main page and open it")
    public void choseRandomGoods() {
        cartPage.openRandomGood();
    }

    @Step("save good title")
    public String getGoodTitle() {
        return cartPage.getTitleOfGood();
    }

    @Step("move good to cart")
    public void moveGoodToCart() {
        cartPage.moveGoodToCart();
    }

    @Step("open cart")
    public void openCart() {
        cartPage.openCart();
    }

    @Step("get all titles goods in carts")
    public List<String> getAllTitleInCart() {
        cartPage.getAllTitleInCart().forEach(PageElement::waitUntilPresent);
        return cartPage.getAllTitleInCart()
                .stream()
                .map(PageElement::getText)
                .collect(Collectors.toList());
    }
}
