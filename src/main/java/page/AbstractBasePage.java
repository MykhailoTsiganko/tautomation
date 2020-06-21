package page;

import decorator.ElementDecorator;
import factory.DriverFactory;
import factory.DriverProvider;
import org.openqa.selenium.support.PageFactory;

public class AbstractBasePage {
    private DriverProvider provider;
    protected AbstractBasePage(){
        provider = driver->driver ;
        PageFactory.initElements(new ElementDecorator(provider.get(DriverFactory.getDiver())),this);
    }
}
