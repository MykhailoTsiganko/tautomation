package page;

import decorator.ElementDecorator;
import factory.DriverProvider;
import org.openqa.selenium.support.PageFactory;

public class AbstractBasePage {
    private DriverProvider provider;
    protected AbstractBasePage(){
        provider= diver -> diver;
        PageFactory.initElements(new ElementDecorator(provider),this);
    }
}
