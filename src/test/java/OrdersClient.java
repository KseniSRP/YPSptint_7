import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class OrdersClient {

    @Step("Создание заказа с цветом: {0}")
    public static void createOrder(Order order){
        given()
                .header("Content-Type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Step("Получение списка всех заказов без указания courierId")
    public static void ordersListGet() {
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
