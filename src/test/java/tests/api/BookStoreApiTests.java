
package tests.api;

import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class BookStoreApiTests extends BaseApiTest {

    @Test(groups = {"api"}, description = "Book catalog is returned in JSON")
    public void getBooksCatalog() {

        Response r = request().get("/BookStore/v1/Books");

        Assert.assertEquals(r.statusCode(), 200, r.asString());

        List<Map<String, Object>> books =
                r.jsonPath().getList("books");

        Assert.assertNotNull(books, "Expected books array");
        Assert.assertFalse(books.isEmpty(), "Expected at least one book");
        Assert.assertNotNull(books.get(0).get("isbn"));
    }

    @Test(groups = {"api"}, description = "Retrieve one ISBN discovered from catalog")
    public void getBookByIsbn() {

        Response catalog = request().get("/BookStore/v1/Books");

        Assert.assertEquals(catalog.statusCode(), 200);

        String isbn = catalog.jsonPath().getString("books[0].isbn");

        Assert.assertNotNull(isbn);

        Response r = request()
                .queryParam("ISBN", isbn)
                .get("/BookStore/v1/Book");

        Assert.assertEquals(r.statusCode(), 200, r.asString());
        Assert.assertEquals(r.jsonPath().getString("isbn"), isbn);
    }

    @Test(groups = {"api"}, description = "Unknown user returns not found")
    public void invalidCredentialsNotAuthorized() {

        Response r = request()
                .contentType("application/json")
                .body("""
                    {
                        "userName": "qa-nonexistent-user",
                        "password": "invalid-password"
                    }
                    """)
                .post("/Account/v1/Authorized");

        Assert.assertEquals(r.statusCode(), 404, r.asString());

        Assert.assertEquals(
                r.jsonPath().getString("code"),
                "1207"
        );

        Assert.assertEquals(
                r.jsonPath().getString("message"),
                "User not found!"
        );
    }
}
