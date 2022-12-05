package generators;

import models.User;

public class UserGenerator {
    private static String email = "UserTest" + (int) (Math.random() * 1000) + "@yandex.ru";

    public static User getDefault() {
        return new User(email, "password", "UserTest");
    }

    //тестовые данные для негативных кейсов по регистрации пользователя
    public static User getShortPassword() {
        return new User(email, "pass", "UserTest");
    }
}
