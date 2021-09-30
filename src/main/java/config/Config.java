package config;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties;


    public String getRestApiUrl() {
        return properties.getProperty("RestAssured.baseURL");
    }

    private Properties getProperties() {
        Properties properties = new Properties();

        try {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                properties.load(inputStream);
            }
        } catch (Exception e) {
            throw new RuntimeException(" config.properties load error" + e);
        }
        return properties;
    }

    public Config() {
        properties = getProperties();
    }


}
