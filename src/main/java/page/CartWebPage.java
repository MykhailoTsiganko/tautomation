package page;

import elements.PageElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class CartWebPage extends AbstractBasePage{
    @FindBy(css = ".main-goods__title")
    private List<PageElement> listOfGoods;
    @FindBy(xpath = "//h1[@class='product__title']")
    private PageElement goodsTitle;
    @FindBy(xpath = "//span[normalize-space()='Купити']//ancestor::button")
    private PageElement buyButton;
    @FindBy(xpath = "//a[@class='cart-modal__title']")
    private List<PageElement> goodsInCart;


    public void openRandomGood(){
        listOfGoods.forEach(PageElement::waitForClickable);
        int i = new Random().nextInt(listOfGoods.size());
        listOfGoods.get(i).click();
    }
    public String getTitleOfGood(){
        goodsTitle.waitForVisibility();
        return goodsTitle.getText();
    }
    public void moveGoodToCart(){
        buyButton.click();
    }
    public List<PageElement> getAllTitleInCart(){
        return goodsInCart;
    }
}
