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
    RegistrationPage registrationPage = new RegistrationPage();
    private UserClient userClient;
    private User user;

    @Before
    public void userGeneration(){
        registrationPage = open("https://stellarburgers.nomoreparties.site/", RegistrationPage.class);
        user = UserGenerator.getDefault();
        userClient = new UserClient();
    }

    @Test
    public void registrationAndLoginUserAvailable() {

        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();
        
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