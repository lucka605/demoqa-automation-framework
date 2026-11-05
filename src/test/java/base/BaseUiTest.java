package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Settings;

public abstract class BaseUiTest {
    @BeforeMethod(alwaysRun = true)
    public void setup() { DriverFactory.start(); }
    @AfterMethod(alwaysRun = true)
    public void tearDown() { DriverFactory.stop(); }
    protected void open(String path) { DriverFactory.get().get(Settings.baseUrl() + path); }
}
