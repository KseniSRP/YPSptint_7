
public class Courier {
    private String login; // Логин курьера, записывается в поле login таблицы Couriers
    private String password; // Пароль курьера, хэш от значения записывается в поле passwordHash таблицы Couriers
    private String firstName; // Имя курьера, записывается в поле firstName таблицы Couriers

    // Конструктор по умолчанию
    public Courier() {
    }

    // Конструктор со всеми параметрами
    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // Геттеры

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() { return firstName; }

    // Сеттеры
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
