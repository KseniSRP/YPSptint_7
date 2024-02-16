import io.restassured.RestAssured;
import org.junit.After;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BaseTest {
    private Integer courierId;


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }


    @After
    public void tearDown() {
        // Удаление курьера после каждого теста, если он был создан
        if (courierId != null) {
            CourierClient.deleteCourier(courierId);
        }

    }


}