package custumpagefactory;

import factory.DriverProvider;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;


public class ElementLocatorFactoryImpl implements ElementLocatorFactory {
  private final DriverProvider provider;

    public ElementLocatorFactoryImpl(DriverProvider provider) {
        this.provider = provider;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new ElementLocatorImpl(this.provider,field);
    }
}
