package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Settings;
import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver = DriverFactory.get();
    protected final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Settings.timeoutSeconds()));
    protected WebElement visible(By locator) { return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); }
    protected WebElement clickable(By locator) { return wait.until(ExpectedConditions.elementToBeClickable(locator)); }
    protected void type(By locator, String value) { WebElement e = visible(locator); e.clear(); e.sendKeys(value); }
    protected void click(By locator) { WebElement e = clickable(locator); ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", e); e.click(); }
    protected String text(By locator) { return visible(locator).getText(); }
}
