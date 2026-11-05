
package pages;

import base.DriverFactory;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckBoxPage extends BasePage {

   
public void selectHome() {

    By homeCheckbox = By.xpath(
        "//span[contains(@class,'rc-tree-title') " +
        "and normalize-space()='Home']" +
        "/ancestor::span[contains(@class,'rc-tree-node-content-wrapper')]" +
        "/preceding-sibling::span[contains(@class,'rc-tree-checkbox')]"
    );

    WebElement checkbox = new WebDriverWait(
        DriverFactory.get(),
        Duration.ofSeconds(20)
    ).until(ExpectedConditions.elementToBeClickable(homeCheckbox));

    System.out.println("AUTO: Clicking Home checkbox");

    checkbox.click();

    System.out.println("AUTO: Checkbox clicked successfully");
}


    public String result() {

        By resultLocator = By.cssSelector(".display-result");

        return new WebDriverWait(
            DriverFactory.get(),
            Duration.ofSeconds(10)
        )
        .until(ExpectedConditions.visibilityOfElementLocated(resultLocator))
        .getText();
    }
}
