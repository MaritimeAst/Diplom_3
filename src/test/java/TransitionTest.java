import com.codeborne.selenide.Configuration;
import org.junit.Test;
import pom.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class TransitionTest {
    @Test
    public void transitionByPersonalCabinetLink() {
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site", RegistrationPage.class);
        registrationPage.personalCabinet();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    public void transitionByLogoLink() {
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/login", RegistrationPage.class);
        registrationPage.transitionByLogoLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    public void transitionByConstructorLink() {
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/login", RegistrationPage.class);
        registrationPage.transitionByConstructorLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }
}
