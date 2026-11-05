package tests.ui;
import base.BaseUiTest;
import pages.AlertsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class AlertsTests extends BaseUiTest {
 @Test(groups={"smoke"}) public void acceptSimpleAlert(){open("/alerts");Assert.assertTrue(new AlertsPage().acceptAlert().contains("clicked a button"));}
 @Test(groups={"regression"}) public void dismissConfirmation(){open("/alerts");AlertsPage p=new AlertsPage();p.confirm(false);Assert.assertTrue(p.confirmResult().contains("Cancel"));}
}
