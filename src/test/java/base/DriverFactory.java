package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Settings;

public final class DriverFactory {
    private DriverFactory() {}
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    public static WebDriver get() { return DRIVER.get(); }
    public static void start() {
        WebDriver driver;
        switch (Settings.browser()) {
            case "firefox":
                FirefoxOptions ff = new FirefoxOptions();
                if (Settings.headless()) ff.addArguments("-headless");
                driver = new FirefoxDriver(ff);
                break;
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (Settings.headless()) options.addArguments("--headless=new");
                options.addArguments("--window-size=1440,1000", "--disable-notifications");
                if (System.getenv("CI") != null) options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
                break;
            default: throw new IllegalArgumentException("Unsupported browser: " + Settings.browser());
        }
        DRIVER.set(driver);
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 1000));
    }
    public static void stop() { WebDriver driver = DRIVER.get(); try { if (driver != null) driver.quit(); } finally { DRIVER.remove(); } }
}
