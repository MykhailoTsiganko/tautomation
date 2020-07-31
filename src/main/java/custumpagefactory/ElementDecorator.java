package custumpagefactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Collection;

import elements.MyList;
import elements.PageElement;
import elements.PageElementImpl;
import factory.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;


public class ElementDecorator extends DefaultFieldDecorator {

    public ElementDecorator(DriverProvider provider) {
        super(new ElementLocatorFactoryImpl(provider));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<PageElementImpl> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            if (Collection.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass, field);
            }
            return createElement(loader, locator, decoratableClass, field);
        }
        return super.decorate(loader, field);
    }

    @SuppressWarnings("unchecked")
    private Class<PageElementImpl> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        if (Collection.class.isAssignableFrom(clazz)) {
            if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clazz = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }

        if (PageElement.class.isAssignableFrom(clazz)) {
            return (Class<PageElementImpl>) clazz;
        } else {
            return null;
        }
    }

    protected PageElement createElement(ClassLoader loader, ElementLocator locator, Class<PageElementImpl> clazz, Field field) {
        WebElement proxy = proxyForLocator(loader, locator);
        PageElementImpl element = WrapperFactory.createInstance(clazz, proxy, field);
        element.setSingle(true);
        return element;
    }

    @SuppressWarnings("unchecked")
    protected MyList<PageElement> createList(ClassLoader loader, ElementLocator locator, Class<PageElementImpl> clazz, Field field) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz, field);
        return  (MyList<PageElement>) Proxy.newProxyInstance(loader, new Class[]{MyList.class}, handler);
    }

    @SuppressWarnings("unused")
    private static <T> T createInstance(Class<T> clazz, WebElement element) {
        try {
            return (T) clazz.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            throw new AssertionError("WebElement can't be represented as " + clazz);
        }
    }
}