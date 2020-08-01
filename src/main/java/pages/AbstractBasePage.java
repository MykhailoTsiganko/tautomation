package pages;

import custumpagefactory.ElementDecorator;
import factory.DriverContainer;
import org.openqa.selenium.support.PageFactory;

public class AbstractBasePage {
    protected AbstractBasePage() {
        PageFactory.initElements(new ElementDecorator(DriverContainer::getDiver), this);
    }
}
