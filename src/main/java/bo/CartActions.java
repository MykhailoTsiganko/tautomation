package bo;

import elements.PageElement;
import factory.DriverFactory;
import org.testng.Assert;
import page.CartWebPage;

import java.util.List;
import java.util.stream.Collectors;

public class CartActions {
    private final CartWebPage boxPage;

    public CartActions() {
        boxPage = new CartWebPage();
    }

    public void openWebSite(String url) {
        DriverFactory.getDiver().get(url);
    }

    public void choseRandomGoods() {
        boxPage.openRandomGood();
    }

    public String getGoodTitle() {
        return boxPage.getTitleOfGood();
    }

    public void moveGoodToCart() {
        boxPage.moveGoodToCart();
    }

    public List<String> getAllTitleInCart() {
        return boxPage.getAllTitleInCart()
                .stream()
                .map(PageElement::getText)
                .collect(Collectors.toList());
    }
}
