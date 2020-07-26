package factory;

import com.google.common.base.Suppliers;
import config.ConfigProperties;
import inject.GuiceInjector;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


public class DriverFactory {
    private static final String chromeNode = "http://192.168.56.1:5557/wd/hub";
    private static final String firefoxNode = "http://192.168.56.1:5556/wd/hub";
    private static final String edgeNode = "http://192.168.56.1:5558/wd/hub";
    private static final ThreadLocal<WebDriver> parallelDriver = new ThreadLocal<>();
    private static final int implicitlyWait = 25;
    private static Supplier<ConfigProperties> configPropertiesProvider = Suppliers.memoize(()
            -> GuiceInjector.getBean(ConfigProperties.class));

    public static void setWait(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void runWithZeroImplicitly(Supplier<WebElement> supplier) {
        setWait(getDiver(), 0);
        supplier.get();
        setWait(getDiver(), implicitlyWait);
    }

    private static void initDriver() {
        String device = configPropertiesProvider.get().getBrowserName();
        WebDriver driver = null;
        try {
            driver = createWebDriver(device);
        } catch (MalformedURLException e) {
            System.out.println("the built-in URL class encounters an invalid URL");
        }
        driver.manage().window().maximize();
        setWait(driver, implicitlyWait);
        parallelDriver.set(driver);
    }

    private static WebDriver createWebDriver(String device) throws MalformedURLException {
        MutableCapabilities options;
        String baseUrl;

        if (configPropertiesProvider.get().getRemoteDriver()) {
            if ("firefox".equals(device)) {
                WebDriverManager.firefoxdriver().setup();
                options = new FirefoxOptions();
                baseUrl = firefoxNode;
                options.setCapability("browser", "firefox");
                options.setCapability(FirefoxDriver.MARIONETTE, true);
            } else if ("edge".equals(device)) {
                WebDriverManager.edgedriver().setup();
                options = new EdgeOptions();
                baseUrl = edgeNode;
            } else {
                WebDriverManager.chromedriver().setup();
                options = new ChromeOptions();
                baseUrl = chromeNode;
                if ("mobile".equals(device)) {
                    setChromeEmulation(options);
                }
            }
            options.setCapability("platform", "WINDOWS");
            options.setCapability("newCommandTimeout", 5000);
            return new RemoteWebDriver(new URL(baseUrl), options);
        } else {
            if ("firefox".equals(device)) {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            } else if ("edge".equals(device)) {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            } else {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        }
    }


    private static void setChromeEmulation(MutableCapabilities options) {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPad");
        String emulator = "mobileEmulation";
        options.setCapability(emulator, mobileEmulation);
    }

    public static WebDriver getDiver() {
        if (parallelDriver.get() == null) {
            initDriver();
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
