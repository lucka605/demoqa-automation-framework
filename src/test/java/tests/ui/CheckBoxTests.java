
package tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseUiTest;
import base.DriverFactory;
import pages.CheckBoxPage;

public class CheckBoxTests extends BaseUiTest {

    @Test(groups = {"smoke"})
    public void homeSelectionDisplaysResult() {

        // Open the Checkbox page
        open("/checkbox");

        WebDriver driver = DriverFactory.get();
        System.out.println("DEBUG HOME HTML:");

driver.findElements(
    By.xpath("//*[normalize-space(text())='Home']")
).forEach(element -> {
    System.out.println(element.getAttribute("outerHTML"));
});

        // Print browser information
        System.out.println("DEBUG URL: " + driver.getCurrentUrl());
        System.out.println("DEBUG TITLE: " + driver.getTitle());

        // Print page body text
        String bodyText = driver.findElement(By.tagName("body")).getText();

        System.out.println("DEBUG BODY:");
        System.out.println(
            bodyText.substring(0, Math.min(1000, bodyText.length()))
        );

        // Count checkbox elements
        System.out.println("DEBUG CHECKBOX COUNT: " +
            driver.findElements(By.cssSelector(".rct-checkbox")).size());

        // Count Home elements
        System.out.println("DEBUG HOME COUNT: " +
            driver.findElements(By.xpath(
                "//span[contains(@class,'rct-title') and normalize-space()='Home']"
            )).size());

        // Execute the actual test
        CheckBoxPage page = new CheckBoxPage();
        page.selectHome();

        // Verify the result
        Assert.assertTrue(
            page.result().contains("home"),
            "Home checkbox selection was not reflected in the result"
        );
    }
}
