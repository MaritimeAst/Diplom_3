import clients.UserClient;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.PersonalCabinetPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class RegistrationTest {
    PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage();
    private UserClient userClient;
    private User user;

    @Before
    public void userGeneration(){
        personalCabinetPage = open("https://stellarburgers.nomoreparties.site/", PersonalCabinetPage.class);
        user = UserGenerator.getDefault();
        userClient = new UserClient();
    }

    @Test
    @Description("Проверка регистрации нового пользователя. Позитивный сценарий")
    public void registrationAndLoginUserAvailable() {

        personalCabinetPage.personalCabinet();
        personalCabinetPage.registration(user.name, user.email, user.password);
        personalCabinetPage.registrationSubmit();
        personalCabinetPage.login(user.email, user.password);
        personalCabinetPage.loginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @After
    public void cleanUp() {
        ValidatableResponse responseLogin = userClient.login(user.email, user.password);
        String accessToken = responseLogin.extract().path("accessToken");
        userClient.delete(accessToken);
    }
}