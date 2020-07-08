package elements;

public interface PageElement {
    public String getAttribute(String atr);

    public boolean isDisplayed();

    public boolean isSelected();

    public void click();

    public void actionClick();

    public void scriptClick();

    public void sendKeys(CharSequence... keys);

    public void clear();

    public String getText();

    public void waitForVisibility();

    public void waitForClickable();
}
