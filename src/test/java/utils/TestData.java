package utils;
import org.testng.annotations.DataProvider;
public final class TestData {
  private TestData() {}
  @DataProvider(name="validUsers")
  public static Object[][] validUsers(){return new Object[][]{
    {"Test User","qa.demo.one@example.com","Paris, France","Lyon, France"},
    {"Alex Morgan","alex.qa@example.com","Street 42","Avenue 8"}
  };}
}
