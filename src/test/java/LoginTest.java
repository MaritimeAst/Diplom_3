import clients.UserClient;
import com.codeborne.selenide.Configuration;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.PersonalCabinetPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class LoginTest {
    PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage();
    private UserClient userClient;
    private User user;

    @Before
    public void userGeneration(){

//        Configuration.holdBrowserOpen = true;         //Для дебага, не закрывает браузер после теста

//        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");  // Для запуска тестов в ЯндексБраузеое
//        ChromeOptions options=new ChromeOptions();
//        options.setBinary("C:\\Users\\Marina\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//        WebDriver webDriver= new ChromeDriver(options);

        user = UserGenerator.getDefault();
        userClient = new UserClient();
        personalCabinetPage = open("https://stellarburgers.nomoreparties.site", PersonalCabinetPage.class);
        personalCabinetPage.personalCabinet();
        personalCabinetPage.registration(user.name, user.email, user.password);
        personalCabinetPage.registrationSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    @Description("Проверка возможности входа по кнопке «Войти в аккаунт» на главной")
    public void loginOnMainPage() {

        personalCabinetPage.transitionByLogoLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        personalCabinetPage.loginOnMainPage();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        personalCabinetPage.login(user.email, user.password);
        personalCabinetPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @Description("Проверка возможности входа через кнопку «Личный кабинет»")
    public void loginFromPersonalCabinet(){

        personalCabinetPage.personalCabinet();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        personalCabinetPage.login(user.email, user.password);
        personalCabinetPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @Description("Проверка возможности входа через кнопку в форме регистрации")
    public void loginAfterRegistration() {

        personalCabinetPage.login(user.email, user.password);
        personalCabinetPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @Description("Проверка возможности входа через кнопку в форме восстановления пароля")
    public void loginFromRestorePassword() {

        personalCabinetPage.personalCabinet();
        personalCabinetPage.loginFromRestorePassword();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        personalCabinetPage.login(user.email, user.password);
        personalCabinetPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @Description("Проверка возможности выхода")
    public void logout() {

        personalCabinetPage.login(user.email, user.password);                                                   //Логин
        personalCabinetPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        personalCabinetPage.personalCabinet();                                                                 //Переход в личный кабинет
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));
        personalCabinetPage.logout();                                                                          //Выход
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @After
    public void cleanUp() {
        ValidatableResponse responseLogin = userClient.login(user.email, user.password);                    //логин зарегистрированным пользователем при помощи restAssured для получения accessToken
        String accessToken = responseLogin.extract().path("accessToken");
        userClient.delete(accessToken);                                                                     //Удаление зарегистрированного пользователя
    }                                                                                                       //Закрытие окна браузера выполняет Selenide
}
