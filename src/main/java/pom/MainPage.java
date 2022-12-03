package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement
            logoLink = $("a[class=active]"),
            loginOnMainPageButton = $$("button").findBy(Condition.text("Войти в аккаунт")),
            bunDiv = $$("span").findBy(Condition.text("Булки")),
            bunH2 = $$("h2").findBy(Condition.text("Булки")),
            sauceDiv = $$("span").findBy(Condition.text("Соусы")),
            sauceH2 = $$("h2").findBy(Condition.text("Соусы")),
            fillingDiv = $$("span").findBy(Condition.text("Начинки")),
            fillingH2 = $$("h2").findBy(Condition.text("Начинки"));


    //Переход к разделу "Булки"
    public void transitionToBun(){
        bunDiv.click();
        bunH2.shouldBe(Condition.visible);
    }

    //Переход к разделу "Соусы"
    public void transitionToSauce(){
        sauceDiv.click();
        sauceH2.shouldBe(Condition.visible);
    }

    //Переход к разделу "Начинки"
    public void transitionToFilling(){
        fillingDiv.click();
        fillingH2.shouldBe(Condition.visible);
    }
}
