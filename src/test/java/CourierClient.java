import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierClient {



    @Step("Создание курьера")
    public static void createCourier(Courier courier) {
        given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }


    @Step("Логин курьера")
    public static void loginCourier(String login, String password) {
        given()
                .header("Content-Type", "application/json")
                .body("{\"login\":\"" + login + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200)
                .body("id", notNullValue());

    }

    @Step("Удаление курьера")
    public static void deleteCourier(int courierId) {
        given()
                .header("Content-Type", "application/json")
                .pathParam("id", courierId)
                .when()
                .delete("/api/v1/courier/{id}")
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }

    @Step("Попытка создать дубликат учетной записи курьера")
    public static void attemptToCreateDuplicateCourier(Courier courier) {
        given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(409) // Ожидается конфликт из-за дублирования
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Попытка создать курьера с невалидными данными")
    public static void attemptToCreateCourierWithInvalidData(Courier courier) {
        given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Попытка входа в систему с невалидными учетными данными {login}/{password}")
    public static void loginWithInvalidCredentials(String login, String password) {
        given()
                .header("Content-Type", "application/json")
                .body("{\"login\":\"" + login + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Попытка логина без учетных данных")
    public static void loginWithoutCredentials() {
        given()
                .header("Content-Type", "application/json")
                .body("{\"login\":\"\", \"password\":\"\"}")
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Логин курьера с логином {login} и паролем {password}")
    public static void courierLogin(String login, String password) {
        given()
                .header("Content-Type", "application/json")
                .body("{\"login\":\"" + login + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

}