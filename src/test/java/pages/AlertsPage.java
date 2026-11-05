package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class AlertsPage extends BasePage {
    public String acceptAlert() { click(By.id("alertButton")); Alert alert = wait.until(ExpectedConditions.alertIsPresent()); String message=alert.getText(); alert.accept(); return message; }
    public void confirm(boolean accept) { click(By.id("confirmButton")); Alert a=wait.until(ExpectedConditions.alertIsPresent()); if (accept) a.accept(); else a.dismiss(); }
    public String confirmResult() { return text(By.id("confirmResult")); }
}
