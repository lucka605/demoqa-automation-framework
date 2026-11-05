package pages;
import org.openqa.selenium.By;

public class TextBoxPage extends BasePage {
    private final By name = By.id("userName"), email = By.id("userEmail"), current = By.id("currentAddress"), permanent = By.id("permanentAddress"), submit = By.id("submit"), output = By.id("output");
    public TextBoxPage fill(String n, String e, String c, String p) {
        type(name,n); type(email,e); type(current,c); type(permanent,p); return this;
    }
    public TextBoxPage submit() { click(submit); return this; }
    public String output() { return text(output); }
    public boolean invalidEmail() { return visible(email).getAttribute("class").contains("field-error"); }
}
