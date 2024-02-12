import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;



public class CourierCreationAPITest extends BaseTest {

    private CourierClient courierClient;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Успешное создание курьера")
    @Description("Проверка возможности успешного создания учетной записи курьера")
    public void testCreateCourierSuccess() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        Courier courier = new Courier(uniqueLogin, "password123", "John");
        courierClient.createCourier(courier);
    }


    @Test
    @DisplayName("Невозможно создать дублирующую учетную запись курьера")
    @Description("Проверка, что система предотвращает создание учетных записей с одинаковыми логинами")
    public void testCannotCreateDuplicateCouriers() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        Courier courier = new Courier(uniqueLogin, "password123", "John");
        courierClient.createCourier(courier);
        CourierClient.attemptToCreateDuplicateCourier(courier);
    }

    @Test
    @DisplayName("Невозможно создать курьера без логина")
    @Description("Проверка, что необходимо указать логин для создания учетной записи курьера")
    public void testCannotCreateCourierWithoutLogin() {
        Courier courier = new Courier("", "12345", "John");
        CourierClient.attemptToCreateCourierWithInvalidData(courier);
    }

    @Test
    @DisplayName("Невозможно создать курьера без пароля")
    @Description("Проверка, что необходимо указать пароль для создания учетной записи курьера")
    public void testCannotCreateCourierWithoutPassword() {
        String uniqueLogin = "testLogin" + System.currentTimeMillis();
        Courier courier = new Courier(uniqueLogin, "", "John");
        CourierClient.attemptToCreateCourierWithInvalidData(courier);
    }








}