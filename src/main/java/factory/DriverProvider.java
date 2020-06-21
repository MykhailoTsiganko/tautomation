package factory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface DriverProvider {
    WebDriver get(WebDriver diver);
}

