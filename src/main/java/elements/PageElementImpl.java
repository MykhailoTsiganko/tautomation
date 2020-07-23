package elements;

import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageElementImpl implements PageElement {
    protected final int DEFAULT_VISIBILITY_TIME = 20;
    protected WebElement element;
    protected By locator;


    public PageElementImpl(WebElement element, By by) {
        this.element = element;
        this.locator = by;
    }

    public String getAttribute(String atr) {
        return this.element.getAttribute(atr);
    }

    public boolean isDisplayed() {
        try {
            return this.element.isDisplayed();
        } catch (NoSuchElementException e) {
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

    public void sendKeys(CharSequence... keys) {
        this.element.sendKeys(keys);
    }

    public void clear() {
        this.element.clear();
    }

    public String getText() {
        return this.element.getText();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return element.getCssValue(s);
    }

    public PageElement waitUntilVisible() {
        DriverFactory.runWithZeroImplicitly(() -> new WebDriverWait(DriverFactory.getDiver(), DEFAULT_VISIBILITY_TIME)
                .until(ExpectedConditions.visibilityOf(element)));
        return this;
    }

    public PageElement waitUntilClickable() {
        DriverFactory.runWithZeroImplicitly(() -> new WebDriverWait(DriverFactory.getDiver(), DEFAULT_VISIBILITY_TIME)
                .until(ExpectedConditions.elementToBeClickable(element)));
        return this;
    }

    public PageElement waitUntilPresent() {
        DriverFactory.runWithZeroImplicitly(() -> new WebDriverWait(DriverFactory.getDiver(), DEFAULT_VISIBILITY_TIME)
                .until(ExpectedConditions.presenceOfElementLocated(locator)));
        return this;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return element.getScreenshotAs(outputType);
    }
}
