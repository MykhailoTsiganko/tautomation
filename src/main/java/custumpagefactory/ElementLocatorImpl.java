package custumpagefactory;

import factory.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

public class ElementLocatorImpl implements ElementLocator {
    private DriverProvider provider;
    private final boolean shouldCache;
    private final By by;
    private WebElement cachedElement;
    private List<WebElement> cachedElementList;

    public ElementLocatorImpl(DriverProvider provider1, Field field) {
        this(provider1, (AbstractAnnotations) (new Annotations(field)));
    }

    public ElementLocatorImpl(DriverProvider provider, AbstractAnnotations annotations) {
        this.provider = provider;
        this.shouldCache = annotations.isLookupCached();
        this.by = annotations.buildBy();
    }

    public WebElement findElement() {
        if (this.cachedElement != null && this.shouldCache()) {
            return this.cachedElement;
        } else {
            WebElement element = this.provider.get().findElement(this.by);
            if (this.shouldCache()) {
                this.cachedElement = element;
            }

            return element;
        }
    }

    public List<WebElement> findElements() {
        if (this.cachedElementList != null && this.shouldCache()) {
            return this.cachedElementList;
        } else {
            List<WebElement> elements = this.provider.get().findElements(this.by);
            if (this.shouldCache()) {
                this.cachedElementList = elements;
            }

            return elements;
        }
    }

    protected boolean shouldCache() {
        return this.shouldCache;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " '" + this.by + "'";
    }
}
