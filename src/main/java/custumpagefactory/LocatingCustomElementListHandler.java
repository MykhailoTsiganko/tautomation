package custumpagefactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import elements.PageElement;
import elements.PageElementImpl;
import elements.PageElementList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;


public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<PageElementImpl> clazz;
    private Field field;

    public LocatingCustomElementListHandler(ElementLocator locator, Class<PageElementImpl> clazz, Field field) {
        this.locator = locator;
        this.clazz = clazz;
        this.field = field;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> elements = locator.findElements();
        AbstractAnnotations annotations = (AbstractAnnotations) (new Annotations(field));
        By by = annotations.buildBy();
        List<PageElement> customs = new PageElementList(by);
        elements.forEach(el -> customs.add(WrapperFactory.createInstance(clazz, el, field)));
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}