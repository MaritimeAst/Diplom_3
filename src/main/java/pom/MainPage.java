package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement
            logoLink = $("a[class=active]"),
            loginOnMainPageButton = $$("button").findBy(Condition.text("Войти в аккаунт"));

//    private final ElementsCollection
//            logoLink = $$("a[class=active]");


    //Вход по кнопке на главной странице
//    public void loginOnMainPage(){
//        loginOnMainPageButton.click();
//    }
}
