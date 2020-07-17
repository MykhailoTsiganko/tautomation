package elements;

import org.openqa.selenium.*;

import java.util.List;
import java.util.function.Supplier;

public interface PageElement extends WebElement {

    void actionClick();

    void scriptClick();

    void click();

    void submit();

    void sendKeys(CharSequence... var1);

    void clear();

    String getTagName();

    String getAttribute(String var1);

    boolean isSelected();

    boolean isEnabled();

    String getText();

    List<WebElement> findElements(By var1);

    WebElement findElement(By var1);

    boolean isDisplayed();

    Point getLocation();

    Dimension getSize();

    Rectangle getRect();

    String getCssValue(String var1);

    PageElement waitForVisibility();

    PageElement waitForClickable();

    PageElement waitForPresenceOfElementLocated();
}
