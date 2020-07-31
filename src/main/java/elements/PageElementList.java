package elements;

import factory.DriverContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class PageElementList implements MyList<PageElement> {
    private List<PageElement> elements;
    private final By locator;

    public PageElementList(By locator) {
        this.elements = new ArrayList<>();
        this.locator = locator;
    }

    private List<PageElement> getElements() {
        List<WebElement> webElements = DriverContainer.getDiver().findElements(locator);
        elements = new ArrayList<>();
        webElements.forEach(el -> elements.add(new PageElementImpl(el, locator)));
        return elements;
    }


    public List<String> getTexts() {
        return getElements().stream().map(PageElement::getText).collect(Collectors.toList());
    }


    public PageElement getPageElement(int index) {
        return getElements().get(index);
    }

    @Override
    public int size() {
        return getElements().size();
    }

    @Override
    public boolean isEmpty() {
        return getElements().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getElements().contains(o);
    }

    @Override
    public Iterator<PageElement> iterator() {
        return new PageElementIterator();
    }

    private class PageElementIterator implements Iterator<PageElement> {
        Iterator<PageElement> iterator = getElements().iterator();

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public PageElement next() {
            return this.iterator.next();
        }

        @Override
        public void remove() {
            this.iterator.remove();
        }
    }

    @Override
    public Object[] toArray() {
        return getElements().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getElements().toArray(a);
    }

    @Override
    public boolean add(PageElement element) {
        return elements.add(element);
    }

    @Override
    public boolean remove(Object o) {
        return getElements().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getElements().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends PageElement> c) {
        return getElements().addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getElements().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getElements().retainAll(c);
    }

    @Override
    public void clear() {
        elements.clear();
    }
}
