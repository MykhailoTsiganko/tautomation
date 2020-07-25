package page;

import elements.PageElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractBasePage {
    @FindBy(xpath = "//input[@placeholder]")
    private PageElement inputColumeForSearch;
    @FindBy(xpath = "//span[@class='goods-tile__title']")
    private List<PageElement> listOfFinedGoods;

    public void writeTextToColume(String text) {
        inputColumeForSearch.sendKeys(text);
    }

    public void findGoodsByText() {
        inputColumeForSearch.sendKeys(Keys.ENTER);
    }

    public List<PageElement> getListOfFinedGoods() {
        listOfFinedGoods.forEach(PageElement::waitUntilVisible);
        return listOfFinedGoods;
    }
}
