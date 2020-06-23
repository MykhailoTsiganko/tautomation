package page;

import custumpagefactory.ElementDecorator;
import factory.DriverFactory;
import factory.DriverProvider;
import org.openqa.selenium.support.PageFactory;

public class AbstractBasePage {
    private DriverProvider provider;

    protected AbstractBasePage() {
        provider = DriverFactory::getDiver;
        PageFactory.initElements(new ElementDecorator(provider), this);
    }
}
