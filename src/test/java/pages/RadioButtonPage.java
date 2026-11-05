package pages;
import org.openqa.selenium.By;
public class RadioButtonPage extends BasePage {
    public void chooseYes() { click(By.cssSelector("label[for='yesRadio']")); }
    public void chooseImpressive() { click(By.cssSelector("label[for='impressiveRadio']")); }
    public String selection() { return text(By.className("text-success")); }
}
