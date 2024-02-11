import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrderListAPITest extends BaseTest {

    @Test
    @DisplayName("Получение списка всех заказов")
    @Description("Тест проверяет получение списка всех активных и завершенных заказов")
    public void ordersListGetWithoutCourierId() {
        ordersListGet();
    }

    @Step("Получение списка всех заказов без указания courierId")
    public void ordersListGet() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("/api/v1/orders")
                .then()
                .statusCode(200) // Проверка статуса кода 200
                .body("orders", notNullValue()) // Проверка на наличие массива заказов
                .body("orders.size()", greaterThan(0)); // Проверка, что в списке есть заказы

    }

}
