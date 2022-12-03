import com.codeborne.selenide.Configuration;
import org.junit.Test;
import pom.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TransitionToIngredients {


    @Test
    public void transitionToBunAvailable(){
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.transitionToFilling();
        mainPage.transitionToBun();
    }

    @Test
    public void transitionToSauceAvailable(){
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.transitionToSauce();
    }

    @Test
    public void transitionToFillingAvailable(){
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.transitionToFilling();
    }
}
