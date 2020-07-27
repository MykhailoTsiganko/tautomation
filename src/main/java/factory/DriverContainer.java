package factory;

import org.openqa.selenium.WebDriver;

public class DriverContainer {
    private static final ThreadLocal<WebDriver> parallelDriver = new ThreadLocal<>();

    public static WebDriver getDiver() {
        if (parallelDriver.get() == null) {
            parallelDriver.set(DriverFactory.createDriver());
        }
        return parallelDriver.get();
    }

    public static void refresh() {
        parallelDriver.get().navigate().refresh();
    }

    public static void quitDriver() {
        if (parallelDriver.get() != null) {
            parallelDriver.get().quit();
            parallelDriver.remove();
        }
    }
}
