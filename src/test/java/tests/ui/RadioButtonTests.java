package tests.ui;
import base.BaseUiTest;
import pages.RadioButtonPage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class RadioButtonTests extends BaseUiTest {
 @Test(groups={"smoke"}) public void yesRadio(){open("/radio-button");RadioButtonPage p=new RadioButtonPage();p.chooseYes();Assert.assertEquals(p.selection(),"Yes");}
 @Test(groups={"regression"}) public void impressiveRadio(){open("/radio-button");RadioButtonPage p=new RadioButtonPage();p.chooseImpressive();Assert.assertEquals(p.selection(),"Impressive");}
}
