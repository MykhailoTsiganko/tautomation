package elements;

import java.util.Collection;
import java.util.List;

public interface PageElementCollection<P extends PageElement> extends Collection<PageElement> {
    List<String> getTexts();

    PageElement get(int index);

    <T> PageElement[] toArray(PageElement[] a);
}
