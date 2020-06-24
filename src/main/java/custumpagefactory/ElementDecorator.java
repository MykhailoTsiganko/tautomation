package custumpagefactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import elements.PageElement;
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
        Class<PageElement> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }
            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    @SuppressWarnings("unchecked")
    private Class<PageElement> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        if (List.class.isAssignableFrom(clazz)) {
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
            return (Class<PageElement>) clazz;
        } else {
            return null;
        }
    }

    protected PageElement createElement(ClassLoader loader, ElementLocator locator, Class<PageElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    @SuppressWarnings("unchecked")
    protected List<PageElement> createList(ClassLoader loader, ElementLocator locator, Class<PageElement> clazz) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz);
        List<PageElement> elements = (List<PageElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return elements;
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