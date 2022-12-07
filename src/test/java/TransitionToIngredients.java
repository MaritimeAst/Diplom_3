import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.MainPage;

import static clients.UserClient.MAIN_UI_URL;
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
        MainPage mainPage = open(MAIN_UI_URL, MainPage.class);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка, что при переключении на таб 'Соусы', у блока появляется класс '.tab_tab_type_current__2BEPc', который подчеркивает выбранный элемент")
    public void goToTheSaucesTab() {
        mainPage.clickSausesButton();
        String actual = mainPage.getClassSaucesButton();
        String expected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
        Assert.assertEquals("Проверка, что при клике на таб появляется подчеркивание", actual, expected);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка, что при переключении на таб 'Начинки', у блока появляется класс '.tab_tab_type_current__2BEPc', который подчеркивает выбранный элемент")
    public void goToTheFillingsSection() {
        mainPage.clickFillingButton();
        String actual = mainPage.getClassFillingButton();
        String expected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
        Assert.assertEquals("Проверка, что при клике на таб появляется подчеркивание", actual, expected);
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка, что при переключении на таб 'Булки', у блока появляется класс '.tab_tab_type_current__2BEPc', который подчеркивает выбранный элемент")
    public void goToTheBunsSection() {
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        String actual = mainPage.getClassBunsButton();
        String expected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
        Assert.assertEquals("Проверка, что при клике на таб появляется подчеркивание", actual, expected);
    }
}
