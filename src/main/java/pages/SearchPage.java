package pages;

import elements.PageElementCollection;
import elements.PageElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends AbstractBasePage {
    @FindBy(xpath = "//input[@placeholder]")
    private PageElement inputColumeForSearch;
    @FindBy(xpath = "//span[@class='goods-tile__title']")
    private PageElementCollection<PageElement> listOfFinedGoods;

    public void writeTextToColume(String text) {
        inputColumeForSearch.sendKeys(text);
    }

    public void findGoodsByText() {
        inputColumeForSearch.sendKeys(Keys.ENTER);
    }

    public PageElementCollection<PageElement> getListOfFinedGoods() {
        listOfFinedGoods.forEach(PageElement::waitUntilVisible);
        return listOfFinedGoods;
    }
}
