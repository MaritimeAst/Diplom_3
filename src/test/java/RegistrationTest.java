import clients.UserClient;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.PersonalCabinetPage;

import static clients.UserClient.MAIN_URL;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class RegistrationTest {
    PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage();
    private UserClient userClient;
    private User user;

    @Before
    public void userGeneration(){

//        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");  // Для запуска тестов в ЯндексБраузере
//        ChromeOptions options=new ChromeOptions();
//        options.setBinary("C:\\Users\\Marina\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//        WebDriver webDriver= new ChromeDriver(options);

//         Configuration.browser = System.getProperty("browser", "firefox");                         //для запуска тестов в firefox

        personalCabinetPage = open(MAIN_URL, PersonalCabinetPage.class);
        user = UserGenerator.getDefault();
        userClient = new UserClient();
    }

    @Test
    @Description("Проверка регистрации нового пользователя. Позитивный сценарий")
    public void registrationAndLoginUserAvailable() {

        personalCabinetPage.personalCabinet();
        personalCabinetPage.registration(user.getName(), user.getEmail(), user.getPassword());
        personalCabinetPage.registrationSubmit();
        personalCabinetPage.login(user.getEmail(), user.getPassword());
        personalCabinetPage.loginSubmit();
        webdriver().shouldHave(url(MAIN_URL));
    }

    @After
    public void cleanUp() {
        ValidatableResponse responseLogin = userClient.login(user.getEmail(), user.getPassword());
        String accessToken = responseLogin.extract().path("accessToken");
        userClient.delete(accessToken);
    }
}