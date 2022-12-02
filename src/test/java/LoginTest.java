import com.codeborne.selenide.Configuration;
import generators.UserGenerator;
import models.User;
import org.junit.Test;
import pom.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginTest {

    @Test
    public void loginAfterRegistration() {
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);

        User user = UserGenerator.getDefault();

        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();

        System.out.println(user.email);
        System.out.println(user.password);
        registrationPage.loginAfterRegistration(user.email, user.password);
        registrationPage.LoginSubmit();
        registrationPage.loginAfterRegistration(user.email, user.password);
        registrationPage.LoginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    public void loginFromMainPage() {
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/", RegistrationPage.class);

        User user = UserGenerator.getDefault();

        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();

        System.out.println(user.email);
        System.out.println(user.password);

        registrationPage.logoLink();
        System.out.println("переход");
        //webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        //registrationPage.loginOnMainPage(user.email, user.password);
        //webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }


}
