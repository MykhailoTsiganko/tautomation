package elements;

import factory.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageElement {
    protected final int DEFAULT_VISIBILITY_TIME = 10;
    protected WebElement element;

    public PageElement(WebElement element) {
        this.element = element;
    }

    public String getAttribute(String atr) {
        return this.element.getAttribute(atr);
    }

    public boolean isDisplayed() {
        try {
            return this.element.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    public void click() {
        this.element.click();
    }

    public void actionClick() {
        Actions action = new Actions(DriverFactory.getDiver());
        action.click(this.element).build().perform();
    }

    public void scriptClick() {
        JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDiver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void sendKeys(String... keys) {
        this.element.sendKeys(keys);
    }

    public void clear() {
        this.element.clear();
    }

    public String getText() {
        return this.element.getText();
    }

    public void waitForVisibility() {
        new WebDriverWait(DriverFactory.getDiver(), DEFAULT_VISIBILITY_TIME)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable() {
        new WebDriverWait(DriverFactory.getDiver(), DEFAULT_VISIBILITY_TIME)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
