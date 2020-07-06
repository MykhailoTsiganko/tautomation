package bo;

import com.google.inject.Inject;
import elements.PageElement;
import factory.DriverFactory;
import page.CartWebPage;

import java.util.List;
import java.util.stream.Collectors;

public class CartActions {
    @Inject
    private CartWebPage cartPage;

    public void openWebSite(String url) {
        DriverFactory.getDiver().get(url);
    }

    public void choseRandomGoods() {
        cartPage.openRandomGood();
    }

    public String getGoodTitle() {
        return cartPage.getTitleOfGood();
    }

    public void moveGoodToCart() {
        cartPage.moveGoodToCart();
    }

    public List<String> getAllTitleInCart() {
        cartPage.getAllTitleInCart().forEach(PageElement::waitForVisibility);
        return cartPage.getAllTitleInCart()
                .stream()
                .map(PageElement::getText)
                .collect(Collectors.toList());
    }
}
