package listeners;
import base.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

public class ScreenshotListener implements ITestListener {
 @Override public void onTestFailure(ITestResult result) {
  WebDriver driver=DriverFactory.get();
  if (!(driver instanceof TakesScreenshot)) return;
  try {
    byte[] shot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    Path folder=Path.of("target","screenshots");Files.createDirectories(folder);
    String name=result.getMethod().getMethodName().replaceAll("[^A-Za-z0-9_-]","_");
    Path path=folder.resolve(name+"-"+Instant.now().toEpochMilli()+".png");
    Files.write(path,shot);System.out.println("Failure screenshot: "+path.toAbsolutePath());
  } catch(Exception e){System.err.println("Screenshot capture failed: "+e.getMessage());}
 }
}
