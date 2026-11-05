package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
public class ButtonsPage extends BasePage {
    public void doubleClick() { new Actions(driver).doubleClick(clickable(By.id("doubleClickBtn"))).perform(); }
    public void rightClick() { new Actions(driver).contextClick(clickable(By.id("rightClickBtn"))).perform(); }
    public String doubleClickMessage() { return text(By.id("doubleClickMessage")); }
    public String rightClickMessage() { return text(By.id("rightClickMessage")); }
}
