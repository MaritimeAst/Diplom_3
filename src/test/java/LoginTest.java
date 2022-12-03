import clients.UserClient;
import generators.UserGenerator;
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
    private UserClient userClient;
    private User user;

    @Before
    public void userGeneration(){
        user = UserGenerator.getDefault();
        userClient = new UserClient();
    }

    @Test
    public void loginAfterRegistration() {

        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();
        System.out.println(user.email);
        System.out.println(user.password);
        registrationPage.loginAfterRegistration(user.email, user.password);
        registrationPage.LoginSubmit();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    public void loginFromMainPage() {
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);

        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();
        registrationPage.transitionByLogoLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        registrationPage.loginOnMainPage(user.email, user.password);
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));  //Доделать проверку на Оформить заказ
    }

    @Test
    public void logout() {
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/login", RegistrationPage.class);
        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();                                                              //Регистрация нового пользователя
        registrationPage.loginAfterRegistration(user.email, user.password);                                 //Логин после регистрации
        registrationPage.LoginSubmit();
        System.out.println(user.email);
        System.out.println(user.password);
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
