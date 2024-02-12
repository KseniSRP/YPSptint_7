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
        OrdersClient.ordersListGet();
    }



}