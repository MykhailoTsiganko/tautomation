package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver DRIVER;
    private static final long implicitlyWait = 25;

    private static void setWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);

    }

    public static void initDriver() {
        String browser = System.getProperty("browser");
        WebDriver driver = null;
        DriverManagerType driverType = DriverManagerType.CHROME;
        if (browser != null && browser.equals("firefox")) {
            driverType = DriverManagerType.FIREFOX;
        } else if (browser != null && browser.equals("edge")) {
            driverType = DriverManagerType.EDGE;
        }
        WebDriverManager.getInstance(driverType).setup();
        try {
            Class<?> chromeClass = Class.forName(driverType.browserClass());
            driver = (WebDriver) chromeClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert driver != null;
        driver.manage().window().maximize();
        setWait(driver);
        DRIVER = driver;
    }

    public static WebDriver getDiver() {
        if (DRIVER == null) {
            initDriver();
        }
        return DRIVER;
    }

    public static void refresh() {
        DRIVER.navigate().refresh();
    }

    public static void quitDriver() {
        if (DRIVER != null) {
            DRIVER.quit();
            DRIVER = null;
        }
    }
}
