package page;

import custumpagefactory.ElementDecorator;
import factory.DriverFactory;
import factory.DriverProvider;
import org.openqa.selenium.support.PageFactory;

public class AbstractBasePage {

    protected AbstractBasePage() {
        PageFactory.initElements(new ElementDecorator(DriverFactory::getDiver), this);
    }
}
