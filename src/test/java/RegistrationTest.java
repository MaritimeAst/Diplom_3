import clients.UserClient;
import com.codeborne.selenide.Configuration;
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


public class RegistrationTest {
    private UserClient userClient;
    private User user;

    @Before
    public void userGeneration(){
        user = UserGenerator.getDefault();
        userClient = new UserClient();
    }

    @Test
    public void registrationAndLoginUserAvailable() {
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/", RegistrationPage.class);

        //User user = UserGenerator.getDefault();

        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();

        System.out.println(user.email);
        System.out.println(user.password);
        registrationPage.loginAfterRegistration(user.email, user.password);
        registrationPage.LoginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @After
    public void cleanUp() {
        ValidatableResponse responseLogin = userClient.login(user.email, user.password);
        String accessToken = responseLogin.extract().path("accessToken");
        userClient.delete(accessToken);
    }
}