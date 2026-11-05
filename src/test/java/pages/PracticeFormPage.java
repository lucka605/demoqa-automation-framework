package pages;
import org.openqa.selenium.By;
public class PracticeFormPage extends BasePage {
    public void fillBasic(String first,String last,String email,String mobile) {
      type(By.id("firstName"),first); type(By.id("lastName"),last); type(By.id("userEmail"),email);
      click(By.cssSelector("label[for='gender-radio-1']")); type(By.id("userNumber"),mobile);
    }
    public void submit() { click(By.id("submit")); }
    public String modalTitle() { return text(By.id("example-modal-sizes-title-lg")); }
}
