import clients.UserClient;
import com.codeborne.selenide.Configuration;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class LoginTest {
    RegistrationPage registrationPage = new RegistrationPage();
    private UserClient userClient;
    private User user;

    @Before
    public void userGeneration(){
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        user = UserGenerator.getDefault();
        userClient = new UserClient();
        registrationPage = open("https://stellarburgers.nomoreparties.site", RegistrationPage.class);
        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    @Description("Проверка возможности входа по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPage() {

        registrationPage.transitionByLogoLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        registrationPage.loginOnMainPage(user.email, user.password);
        registrationPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));  //Доделать проверку на Оформить заказ
    }

    @Test
    @Description("Проверка возможности входа через кнопку «Личный кабинет»")
    public void loginFromPersonalCabinet(){

        registrationPage.personalCabinet();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        registrationPage.loginFromPersonalCabinet(user.email, user.password);
        registrationPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @Description("Проверка возможности входа через кнопку в форме регистрации")
    public void loginAfterRegistration() {

        registrationPage.loginAfterRegistration(user.email, user.password);
        registrationPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @Description("Проверка возможности входа через кнопку в форме восстановления пароля")
    public void loginFromRestorePassword() { //Вход через кнопку в форме восстановления пароля

        registrationPage.personalCabinet();
        registrationPage.loginFromRestorePassword();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        registrationPage.loginFromPersonalCabinet(user.email, user.password);
        registrationPage.loginSubmit();
        Configuration.timeout = 300;
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));  //Доделать проверку на Оформить заказ
    }


    @Test
    @Description("Проверка возможности выхода")
    public void logout() {

        registrationPage.loginAfterRegistration(user.email, user.password);                                 //Логин после регистрации
        registrationPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        registrationPage.personalCabinet();                                                                 //Переход в личный кабинет
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));
        registrationPage.logout();                                                                          //Выход
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @After
    public void cleanUp() {
        ValidatableResponse responseLogin = userClient.login(user.email, user.password);
        String accessToken = responseLogin.extract().path("accessToken");
        userClient.delete(accessToken);
    }
}
