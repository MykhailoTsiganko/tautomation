package page;

import elements.MyList;
import elements.PageElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class CartWebPage extends AbstractBasePage {
    @FindBy(xpath = "//*[@class='tile__title']")
    private MyList<PageElement> listOfGoods;
    @FindBy(xpath = "//h1[@class='product__title']")
    private PageElement goodsTitle;
    @FindBy(xpath = "//span[normalize-space()='Купити']//ancestor::button")
    private PageElement buyButton;
    @FindBy(css = ".js-rz-cart")
    private PageElement cart;
    @FindBy(xpath = "//a[@class='cart-product__title']")
    private MyList<PageElement> goodsInCart;

    public void openRandomGood() {
        listOfGoods.forEach(PageElement::waitUntilClickable);
        int i = new Random().nextInt(listOfGoods.size());
        listOfGoods.getPageElement(i).click();
    }

    public String getTitleOfGood() {
        return goodsTitle.waitUntilPresent().getText();
    }

    public void moveGoodToCart() {
        buyButton.waitUntilClickable().click();
    }

    public void openCart() {
        cart.click();
    }

    public MyList<PageElement> getAllTitleInCart() {
        return goodsInCart;
    }
}
