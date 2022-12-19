import clients.UserClient;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.Before;
import org.junit.Test;
import pom.PersonalCabinetPage;

import static clients.UserClient.MAIN_UI_URL;
import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationErrorTest {
    PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage();
    private UserClient userClient;

    @Before
    public void setUp() {

//      System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");    // Для запуска тестов в ЯндексБраузере
//      ChromeOptions options=new ChromeOptions();
//      options.setBinary("C:\\Users\\Marina\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//      WebDriver webDriver= new ChromeDriver(options);

//      Configuration.browser = System.getProperty("browser", "firefox");                         //для запуска тестов в firefox
        PersonalCabinetPage personalCabinetPage = open(MAIN_UI_URL, PersonalCabinetPage.class);
        userClient = new UserClient();
    }

    @Test
    @Description("Регистрация пользователя с указанием короткого пароля. Негативный сценарий")
    public void registrationNotAvailableWithShotPassword() {

        User user = UserGenerator.getShortPassword();                                               //получение данных пользователя для негативного кейса по регистрации с коротким паролем
        personalCabinetPage.personalCabinet();                                                      //Открытие личного кабинета

        personalCabinetPage.registration(user.getName(), user.getEmail(), user.getPassword());      //Заполнение формы регистрации
        personalCabinetPage.registrationSubmit();                                                //Подтверждение регистрации

        ValidatableResponse responseLogin = userClient.login(user.getEmail(), user.getPassword()); //После попытки регистрации вызываем метод логина с кредами зарегистрированного пользователя
        int statusCodeRegistration = responseLogin.extract().statusCode();
        if (statusCodeRegistration == SC_OK) {                                                     //Если статус-код логина 200, значит регистрация прошла успешно, вопреки ожидаемомоу результату, и далее удаляем созданного пользователя по токену, иначе - ничего не делаем.
        String accessToken = responseLogin.extract().path("accessToken");
        userClient.delete(accessToken);
        }
        personalCabinetPage.registrationPasswordError();

    }
}
