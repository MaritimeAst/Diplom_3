import com.codeborne.selenide.Configuration;
import generators.UserGenerator;
import models.User;
import org.junit.Test;
import pom.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

//@RunWith(Parameterized.class)
public class RegistrationTest {

//    private final String
//    name,
//    email,
//    password;

//    public RegistrationTest(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }

    //тестовые данные
//    @Parameterized.Parameters
//    public static Object[][] getRegistrationData() {
//        return new Object[][]{
//                {"Martian123", "Martian123@email.ru", "password"},
//                {"Martian124", "Martian124@email.ru", "pass"},
//
//        }
//        }
    @Test
    public void registrationAndLoginUserAvailable() {
        //Для дебага, не закрывает браузер после теста
        Configuration.holdBrowserOpen = true;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/", RegistrationPage.class);

        User user = UserGenerator.getDefault();

        registrationPage.personalCabinet();
        registrationPage.openRegistrationForm(user.name, user.email, user.password);
        registrationPage.registrationSubmit();

        System.out.println(user.email);
        System.out.println(user.password);
        registrationPage.loginAfterRegistration(user.email, user.password);
        registrationPage.LoginSubmit();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));

    }

}