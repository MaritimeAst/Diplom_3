import generators.UserGenerator;
import io.qameta.allure.Description;
import models.User;
import org.junit.Before;
import org.junit.Test;
import pom.PersonalCabinetPage;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationErrorTest {
    PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage();
    @Before
    public void setUp(){

//      System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");    // Для запуска тестов в ЯндексБраузере
//      ChromeOptions options=new ChromeOptions();
//      options.setBinary("C:\\Users\\Marina\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//      WebDriver webDriver= new ChromeDriver(options);

//      Configuration.browser = System.getProperty("browser", "firefox");                         //для запуска тестов в firefox
        PersonalCabinetPage personalCabinetPage = open("https://stellarburgers.nomoreparties.site/", PersonalCabinetPage.class);
    }

    @Test
    @Description("Регистрация пользователя с указанием короткого пароля. Негативный сценарий")
    public void registrationAvailable() {

        User user = UserGenerator.getShortPassword();                                   //получение данных пользователя для негативного кейса по регистрации с коротким паролем
        personalCabinetPage.personalCabinet();                                          //Открытие личного кабинета
        personalCabinetPage.registration(user.name, user.email, user.password);         //Заполнение формы регистрации
        personalCabinetPage.registrationSubmit();                                       //Подтверждение регистрации
        personalCabinetPage.registrationPasswordError();                                //Проверка появления сообщения об ошибке
    }
}
