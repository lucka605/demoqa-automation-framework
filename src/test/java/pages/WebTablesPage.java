package pages;
import org.openqa.selenium.By;
public class WebTablesPage extends BasePage {
    public void search(String value) { type(By.id("searchBox"),value); }
    public int rowCount() { return driver.findElements(By.cssSelector(".rt-tbody .rt-tr-group .rt-tr:not(.-padRow) ")).size(); }
    public boolean contains(String value) { return driver.findElements(By.cssSelector(".rt-tbody .rt-td")).stream().anyMatch(e -> e.getText().contains(value)); }
}
