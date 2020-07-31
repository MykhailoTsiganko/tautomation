package elements;

import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public interface MyList<P extends WebElement> extends Collection<PageElement> {
    List<String> getTexts();

    PageElement getPageElement(int index);
}
