package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class DriverFactory {
    private static WebDriver DRIVER;
    private static final long implicitlyWait = 25;

    private static void setWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);

    }

    private static void initDriver() {
        String device = System.getProperty("device");
        WebDriver driver;
       driver = createWebDriver(device);
        driver.manage().window().maximize();
        setWait(driver);
        DRIVER = driver;
    }

    private static WebDriver createWebDriver(String device) {
        if ("firefox".equals(device)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if ("edge".equals(device)) {
            WebDriverManager.edgedriver().operatingSystem(OperatingSystem.WIN).setup();
            return new EdgeDriver();
        }
        ChromeOptions options = new ChromeOptions();
        if("mobile".equals(device)) {
            setChromeEmulation(options);
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    private static void setChromeEmulation(ChromeOptions options) {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPad");
        String emulator = "mobileEmulation";
        options.setExperimentalOption(emulator, mobileEmulation);
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
