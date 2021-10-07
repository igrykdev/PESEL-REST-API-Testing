import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class Pesel_Valid_Test extends Pesel_Base_Test {
    @Test
    public void testGetRequest_StatusCode_is_200() {
        Response response = get("/Pesel?pesel=99013061216");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testGetRequest_Pesel_is_Valid() {
        Response response = get("/Pesel?pesel=92120236214");
        Assert.assertTrue(response.path("isValid"));
    }

    @Test
    public void testGetRequest_BirthDate_is_Valid() {
        Response response = get("/Pesel?pesel=95113026641");
        Assert.assertTrue(response.path("isValid"));

        String birthdate = response.path("birthDate");
        Assert.assertEquals(birthdate, "1995-11-30T00:00:00");
    }

    @Test
    public void testGetResponse_FemaleSex_is_Valid() {
        Response response = get("/Pesel?pesel=94011150629");

        String actualResponse = response.path("sex");
        Assert.assertEquals(actualResponse, "Female");
    }

    @Test
    public void testGetResponse_MaleSex_is_Valid() {
        Response response = get("/Pesel?pesel=95031162456");

        String actualResponse = response.path("sex");
        Assert.assertEquals(actualResponse, "Male");
    }

    @Test
    public void testGetResponse_Body_is_Valid() {
        Response response = get("/Pesel?pesel=90111199540");

        String actualBody = response.getBody().asString();
        String expectedBody = "{\"pesel\":\"90111199540\",\"isValid\":true,\"birthDate\":\"1990-11-11T00:00:00\",\"sex\":\"Female\",\"errors\":[]}";

        Assert.assertEquals(actualBody, expectedBody);
    }

}

