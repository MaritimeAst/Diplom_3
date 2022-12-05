import generators.UserGenerator;
import io.qameta.allure.Description;
import models.User;
import org.junit.Test;
import pom.PersonalCabinetPage;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationErrorTest {
    @Test
    @Description("Регистрация пользователя с указанием короткого пароля. Негативный сценарий")
    public void registrationAvailable() {
        PersonalCabinetPage personalCabinetPage = open("https://stellarburgers.nomoreparties.site/", PersonalCabinetPage.class);
        User user = UserGenerator.getShortPassword();                               //получение данных пользователя для негативного кейса по регистрации с коротким паролем
        personalCabinetPage.personalCabinet();                                         //Открытие личного кабинета
        personalCabinetPage.registration(user.name, user.email, user.password); //Заполнение формы регистрации
        personalCabinetPage.registrationSubmit();                                       //Подтверждение регистрации
        personalCabinetPage.registrationPasswordError();                                //Проверка появления сообщения об ошибке
    }
}
