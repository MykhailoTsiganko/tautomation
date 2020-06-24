package custumpagefactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import elements.PageElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;


public class LocatingCustomElementListHandler implements InvocationHandler {
	private final ElementLocator locator;
	private final Class<PageElement> clazz;

	public LocatingCustomElementListHandler(ElementLocator locator, Class<PageElement> clazz) {
		this.locator = locator;
		this.clazz = clazz;
	}

	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
		List<WebElement> elements = locator.findElements();
		List<PageElement> customs = new ArrayList<>();

		for (WebElement element : elements) {
			customs.add(WrapperFactory.createInstance(clazz, element));
		}
		try {
			return method.invoke(customs, objects);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}
}