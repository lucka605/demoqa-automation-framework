package tests.ui;
import base.BaseUiTest;
import pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;

public class TextBoxTests extends BaseUiTest {
  @Test(groups={"smoke"},dataProvider="validUsers",dataProviderClass=TestData.class,description="Valid values appear in output")
  public void validSubmission(String name,String email,String current,String permanent) {
    open("/text-box"); String result=new TextBoxPage().fill(name,email,current,permanent).submit().output();
    Assert.assertTrue(result.contains("Name:"+name),result);
    Assert.assertTrue(result.contains("Email:"+email),result);
    Assert.assertTrue(result.contains("Current Address :"+current),result);
    Assert.assertTrue(result.contains("Permananet Address :"+permanent),result);
  }
  @Test(groups={"regression"},description="Invalid email is marked by native validation style")
  public void invalidEmailIsRejected() {
    open("/text-box"); TextBoxPage page=new TextBoxPage().fill("Test User","not-an-email","Paris","Lyon").submit();
    Assert.assertTrue(page.invalidEmail(),"Expected field-error class on invalid email");
  }
}
