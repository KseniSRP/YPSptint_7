import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;


public class OrdersClient {

    @Step("Создание заказа с цветом: {0}")
    public static void createOrder(Order order){
        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then()
                .statusCode(201)
                .body("track", Matchers.notNullValue());
    }

    @Step("Получение списка всех заказов без указания courierId")
    public static void ordersListGet() {
        RestAssured.given()
                .header("Content-Type", "application/json")
                .when()
                .get("/api/v1/orders")
                .then()
                .statusCode(200) // Проверка статуса кода 200
                .body("orders", Matchers.notNullValue()) // Проверка на наличие массива заказов
                .body("orders.size()", Matchers.greaterThan(0)); // Проверка, что в списке есть заказы

    }
}
