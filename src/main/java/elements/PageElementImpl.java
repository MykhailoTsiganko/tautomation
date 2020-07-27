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

    public WebElement getElement() {
        return DriverFactory.getDiver().findElement(locator);
    }

    public String getAttribute(String atr) {
        return getElement().getAttribute(atr);
    }

    public boolean isDisplayed() {
        try {
            return getElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSelected() {
        return getElement().isSelected();
    }

    public void click() {
        this.getElement().click();
    }

    public void actionClick() {
        Actions action = new Actions(DriverFactory.getDiver());
        action.click(getElement()).build().perform();
    }

    public void scriptClick() {
        JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDiver();
        executor.executeScript("arguments[0].click();", getElement());
    }

    public void sendKeys(CharSequence... keys) {
        getElement().sendKeys(keys);
    }

    public void clear() {
        getElement().clear();
    }

    public String getText() {
        return getElement().getText();
    }

    @Override
    public void submit() {
        getElement().submit();
    }

    @Override
    public String getTagName() {
        return getElement().getTagName();
    }

    @Override
    public boolean isEnabled() {
        return getElement().isEnabled();
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
        return getElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getElement().getRect();
    }

    @Override
    public String getCssValue(String s) {
        return getElement().getCssValue(s);
    }

    public PageElement waitUntilVisible() {
        DriverFactory.runWithZeroImplicitly(() -> new WebDriverWait(DriverFactory.getDiver(), DEFAULT_VISIBILITY_TIME)
                .until(ExpectedConditions.visibilityOf(getElement())));
        return this;
    }

    public PageElement waitUntilClickable() {
        DriverFactory.runWithZeroImplicitly(() -> new WebDriverWait(DriverFactory.getDiver(), DEFAULT_VISIBILITY_TIME)
                .until(ExpectedConditions.elementToBeClickable(getElement())));
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
