import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TransitionToIngredients {

    MainPage mainPage = new MainPage();

    @Before
    public void setUp() {

//        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");  // Для запуска тестов в ЯндексБраузеое
//        ChromeOptions options=new ChromeOptions();
//        options.setBinary("C:\\Users\\Marina\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//        WebDriver webDriver= new ChromeDriver(options);

//        Configuration.browser = System.getProperty("browser", "firefox");                         //для запуска тестов в firefox
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
    }

    @Test
    @Description("Проверка перехода к разделу Булки")
    public void transitionToBunAvailable() {
        mainPage.transitionToFilling(); //Переход к разделу Начинки, т.к. раздел "Булки" и так по умолчанию отображается первым
        mainPage.transitionToBun();     //Переход к разделу "Булки" И Проверка что произошел переход к  разделу и заголовок "Булки" отображается
    }

    @Test
    @Description("Проверка перехода к разделу Соусы")
    public void transitionToSauceAvailable() {
        mainPage.transitionToSauce();  //Переход к разделу "Соусы" И проверка что при нажатии на вкладку Соусы заголовок "Соусы" отображается
    }

    @Test
    @Description("Проверка перехода к разделу Начинки")
    public void transitionToFillingAvailable() {
        mainPage.transitionToFilling(); //Переход к разделу "Начинки" И проверка что при нажатии на вкладку Соусы заголовок "Начинки" отображается
    }
}
