package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

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
        String devise = System.getProperty("devise");
        WebDriver driver;
        if ("mobile".equals(devise)) {
            driver = createMobile();
        } else {
            driver = createWeb(devise);
        }
        driver.manage().window().maximize();
        setWait(driver);
        DRIVER = driver;
    }

    private static WebDriver createWeb(String devise) {
        if ("firefox".equals(devise)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if ("edge".equals(devise)) {
            WebDriverManager.edgedriver().operatingSystem(OperatingSystem.WIN).setup();
            return new EdgeDriver();
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver createMobile() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
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
