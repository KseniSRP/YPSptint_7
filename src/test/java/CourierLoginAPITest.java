import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class CourierLoginAPITest extends BaseTest {


    @Test
    @DisplayName("Проверка логина пользователя")
    @Description("Тест создает нового пользователя и проверяет его логин в системе")
    public void testCourierLoginSuccess() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        String password = "password123";
        Courier courier = new Courier(uniqueLogin, password, "John");
        CourierClient.createCourier(courier);
        CourierClient.courierLogin(uniqueLogin, password);
    }

    @Test
    @DisplayName("Тест логина курьера с невалидным логином")
    @Description("Тест проверяет, что логин курьера с невалидным логином не проходит.")
    public void testLoginWithInvalidLoginFails() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        String invalidLogin = uniqueLogin + "1";
        String password = "password123";
        Courier courier = new Courier(uniqueLogin, password, "John");

        CourierClient.createCourier(courier);

        CourierClient.loginWithInvalidCredentials(invalidLogin, password);
    }

    @Test
    @DisplayName("Тест логина курьера с невалидным паролем")
    @Description("Тест проверяет, что логин курьера с невалидным паролем не проходит.")
    public void testLoginWithInvalidPasswordFails() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        String password = "password123";
        String invalidPassword = "password1234";
        Courier courier = new Courier(uniqueLogin, password, "John");

        CourierClient.createCourier(courier);

        CourierClient.loginWithInvalidCredentials(uniqueLogin, invalidPassword);
    }

    @Test
    @DisplayName("Тест логина курьера без учетных данных")
    @Description("Тест проверяет, что логин курьера без указания учетных данных не проходит.")
    public void testLoginWithoutCredentialsFails() {
        CourierClient.loginWithoutCredentials();
    }



}