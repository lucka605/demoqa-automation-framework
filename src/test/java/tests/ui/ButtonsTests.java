package tests.ui;
import base.BaseUiTest;
import pages.ButtonsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class ButtonsTests extends BaseUiTest {
 @Test(groups={"smoke"}) public void doubleClickMessage(){open("/buttons");ButtonsPage p=new ButtonsPage();p.doubleClick();Assert.assertTrue(p.doubleClickMessage().contains("double click"));}
 @Test(groups={"regression"}) public void rightClickMessage(){open("/buttons");ButtonsPage p=new ButtonsPage();p.rightClick();Assert.assertTrue(p.rightClickMessage().contains("right click"));}
}
