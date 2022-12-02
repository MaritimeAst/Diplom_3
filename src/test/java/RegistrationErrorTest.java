import com.codeborne.selenide.Configuration;
import generators.UserGenerator;
import models.User;
import org.junit.Test;
import pom.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationErrorTest {
    @Test
    public void registrationAvailable() {
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/", RegistrationPage.class);

        User user = UserGenerator.getShortPassword();

        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();
        registrationPage.registrationPasswordError();

    }
}
