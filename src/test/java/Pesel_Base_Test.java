import config.Config;
import io.restassured.RestAssured;

public class Pesel_Base_Test {
    protected Config config = new Config();

    public Pesel_Base_Test() {
        RestAssured.baseURI = config.getRestApiUrl();
    }
}
