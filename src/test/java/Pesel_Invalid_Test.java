import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class Pesel_Invalid_Test extends Pesel_Base_Test {

    @Test
    public void testGetRequest_INVL_Invalid_Pesel_Lenght() {
        Response response = get("/Pesel?pesel=9011213744");

        String actualResponse = response.path("errors[0].errorCode");
        Assert.assertEquals(actualResponse, "INVL");

        String errorMsg = response.path("errors[0].errorMessage");
        Assert.assertEquals(errorMsg, "Invalid length. Pesel should have exactly 11 digits.");
    }

    @Test
    public void testGetRequest_NBRQ_Invalid_Pesel_Characters() {
        Response response = get("/Pesel?pesel=901121374a");

        String actualResponse = response.path("errors[0].errorCode");
        Assert.assertEquals(actualResponse, "NBRQ");

        String errorMsg = response.path("errors[0].errorMessage");
        Assert.assertEquals(errorMsg, "Invalid characters. Pesel should be a number.");
    }

    @Test
    public void testGetRequest_INVC_Invalid_Pesel_Sum() {
        Response response = get("/Pesel?pesel=44051401357");

        String actualResponse = response.path("errors[0].errorCode");
        Assert.assertEquals(actualResponse, "INVC");

        String errorMsg = response.path("errors[0].errorMessage");
        Assert.assertEquals(errorMsg, "Check sum is invalid. Check last digit.");
    }

    @Test
    public void testGetRequest_INVD_Invalid_Pesel_Day() {
        Response response = get("/Pesel?pesel=90063102119");

        String actualResponse = response.path("errors[0].errorCode");
        Assert.assertEquals(actualResponse, "INVD");

        String errorMsg = response.path("errors[0].errorMessage");
        Assert.assertEquals(errorMsg, "Invalid day.");
    }

    @Test
    public void testGetRequest_INVM_Invalid_Pesel_Month() {
        Response response = get("/Pesel?pesel=90001512147");

        String actualYear = response.path("errors[0].errorCode");
        Assert.assertEquals(actualYear, "INVY");

        String errorMsgYear = response.path("errors[0].errorMessage");
        Assert.assertEquals(errorMsgYear, "Invalid year.");
    }

    @Test
    public void testGetRequest_INVY_Invalid_Pesel_Year() {
        Response response = get("/Pesel?pesel=45936781269");

        String actualResponse = response.path("errors[0].errorCode");
        Assert.assertEquals(actualResponse, "INVY");

        String errorMsg = response.path("errors[0].errorMessage");
        Assert.assertEquals(errorMsg, "Invalid year.");
    }

}
