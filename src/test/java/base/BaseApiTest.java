package base;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.Settings;
public abstract class BaseApiTest {
 protected RequestSpecification request(){ return RestAssured.given().baseUri(Settings.baseUrl()).accept("application/json").redirects().follow(false); }
}
