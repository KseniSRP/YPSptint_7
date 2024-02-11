import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationAPITest extends BaseTest {
    private final Order order;

    // Конструктор тестового класса для передачи объекта Order как параметра
    public OrderCreationAPITest(Order order) {
        this.order = order;
    }

    // Параметры для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data () {
        return Arrays.asList(new Object[][]{
                {new Order("Naruto", "Uzumaki", "Konoha 123", "3", "+7 800 123 45 67", 5, "2023-03-15", "Please hurry",  new String[]{"BLACK"})},
                {new Order("Sasuke", "Uchiha", "Konoha 4455", "4", "+7 800 987 65 43", 3, "2023-03-16", "Leave it at the door", new String[]{"GREY"})},
                {new Order("Sakura", "Uchiha", "Konoha 46", "5", "+7 800 987 95 43", 9, "2023-03-17", "Leave it at the door", new String[]{"BLACK", "GREY"})},
                {new Order("Vasya", "Pupkin", "Moskow 45", "6", "+7 800 087 65 43", 2, "2023-03-18", "Leave it at the door", new String[]{})},
        });
    }

    @Test
    @DisplayName("Проверка создания заказа с разными цветами")
    @Description("Тест создает заказ с указанными цветами")
    public void testCreateOrderWithColors () {
        createOrder(order);
    }

    @Step("Создание заказа с цветом: {0}")
    public void createOrder (Order order){
        given()
                .header("Content-Type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }

}

