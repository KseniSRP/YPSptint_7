import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class CourierCreationAPITest extends BaseTest {


    @Test
    @DisplayName("Успешное создание курьера")
    @Description("Проверка возможности успешного создания учетной записи курьера")
    public void testCreateCourierSuccess() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        Courier courier = new Courier(uniqueLogin, "password123", "John");
        createCourier(courier);
    }

    @Test
    @DisplayName("Невозможно создать дублирующую учетную запись курьера")
    @Description("Проверка, что система предотвращает создание учетных записей с одинаковыми логинами")
    public void testCannotCreateDuplicateCouriers() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        Courier courier = new Courier(uniqueLogin, "password123", "John");
        createCourier(courier);
        attemptToCreateDuplicateCourier(courier);
    }

    @Test
    @DisplayName("Невозможно создать курьера без логина")
    @Description("Проверка, что необходимо указать логин для создания учетной записи курьера")
    public void testCannotCreateCourierWithoutLogin() {
        Courier courier = new Courier("", "12345", "John");
        attemptToCreateCourierWithInvalidData(courier);
    }

    @Test
    @DisplayName("Невозможно создать курьера без пароля")
    @Description("Проверка, что необходимо указать пароль для создания учетной записи курьера")
    public void testCannotCreateCourierWithoutPassword() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        Courier courier = new Courier(uniqueLogin, "", "John");
        attemptToCreateCourierWithInvalidData(courier);
    }

    @Step("Создание курьера")
    private void createCourier(Courier courier) {
        given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Step("Попытка создать дубликат учетной записи курьера")
    private void attemptToCreateDuplicateCourier(Courier courier) {
        given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Попытка создать курьера с невалидными данными")
    private void attemptToCreateCourierWithInvalidData(Courier courier) {
        given()
                .header("Content-Type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


}
