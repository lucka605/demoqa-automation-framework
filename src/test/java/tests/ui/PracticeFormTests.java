package tests.ui;
import base.BaseUiTest;
import pages.PracticeFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class PracticeFormTests extends BaseUiTest {
 @Test(groups={"regression"}) public void submitMinimumValidStudent(){open("/automation-practice-form");PracticeFormPage p=new PracticeFormPage();p.fillBasic("Test","Student","student@example.com","1234567890");p.submit();Assert.assertEquals(p.modalTitle(),"Thanks for submitting the form");}
}
