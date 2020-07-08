package page;

import elements.PageElement;
import elements.PageElementImpl;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class CartWebPage extends AbstractBasePage{
    @FindBy(css = ".main-goods__title")
    private List<PageElementImpl> listOfGoods;
    @FindBy(xpath = "//h1[@class='product__title']")
    private PageElementImpl goodsTitle;
    @FindBy(xpath = "//span[normalize-space()='Купити']//ancestor::button")
    private PageElementImpl buyButton;
    @FindBy(xpath = "//a[@class='cart-modal__title']")
    private List<PageElementImpl> goodsInCart;


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
        buyButton.waitForClickable();
        buyButton.click();
    }
    public List<PageElementImpl> getAllTitleInCart(){
        return goodsInCart;
    }
}
