package custumpagefactory;

import elements.PageElementImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;

public class WrapperFactory {

    public static PageElementImpl createInstance(Class<PageElementImpl> clazz, WebElement element, Field field) {
        AbstractAnnotations annotations = (AbstractAnnotations) (new Annotations(field));
        By by = annotations.buildBy();
        try {
            return new PageElementImpl(element, by);
        } catch (Exception e) {
            throw new AssertionError("WebElement can't be represented as " + clazz);
        }
    }
}