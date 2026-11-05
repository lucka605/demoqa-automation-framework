package tests.ui;
import base.BaseUiTest;
import pages.WebTablesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class WebTablesTests extends BaseUiTest {
 @Test(groups={"regression"}) public void searchExistingEmployee(){open("/webtables");WebTablesPage p=new WebTablesPage();p.search("Cierra");Assert.assertTrue(p.contains("Cierra"),"Expected example fixture Cierra");}
}
