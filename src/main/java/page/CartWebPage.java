package page;

import elements.PageElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class CartWebPage extends AbstractBasePage{
    @FindBy(xpath = "//*[@class='tile__title']")
    private List<PageElement> listOfGoods;
    @FindBy(xpath = "//h1[@class='product__title']")
    private PageElement goodsTitle;
    @FindBy(xpath = "//span[normalize-space()='Купити']//ancestor::button")
    private PageElement buyButton;
    @FindBy(css = ".js-rz-cart")
    private PageElement cart;
    @FindBy(xpath = "//a[@class='cart-product__title']")
    private List<PageElement> goodsInCart;


    public void openRandomGood(){
        listOfGoods.forEach(PageElement::waitUntilClickable);
        int i = new Random().nextInt(listOfGoods.size());
        listOfGoods.get(i).click();
    }
    public String getTitleOfGood(){
        return goodsTitle.waitUntilVisible().getText();
    }
    public void moveGoodToCart(){
        buyButton.waitUntilClickable().click();
    }
    public void openCart(){
        cart.click();
    }
    public List<PageElement> getAllTitleInCart(){
        return goodsInCart;
    }
}
