import io.qameta.allure.Description;
import org.junit.Test;
import pom.PersonalCabinetPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class TransitionTest {
    @Test
    @Description("Проверка возможности перехода с основной страницы, на страницу Личный кабинет")
    public void transitionByPersonalCabinetLink() {
        PersonalCabinetPage personalCabinetPage = open("https://stellarburgers.nomoreparties.site", PersonalCabinetPage.class);
        personalCabinetPage.personalCabinet();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    @Description("Проверка возможности перехода со страницы Личный кабинет, на главную страницу по логотипу")
    public void transitionByLogoLink() {
        PersonalCabinetPage personalCabinetPage = open("https://stellarburgers.nomoreparties.site/login", PersonalCabinetPage.class);
        personalCabinetPage.transitionByLogoLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @Description("Проверка возможности перехода со страницы Личный кабинет, на главную страницу по кнопке Конструктор")
    public void transitionByConstructorLink() {
        PersonalCabinetPage personalCabinetPage = open("https://stellarburgers.nomoreparties.site/login", PersonalCabinetPage.class);
        personalCabinetPage.transitionByConstructorLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }
}
