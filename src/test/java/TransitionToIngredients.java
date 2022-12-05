import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import pom.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TransitionToIngredients {

    MainPage mainPage = new MainPage();

    @Before
    public void setUp() {
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
