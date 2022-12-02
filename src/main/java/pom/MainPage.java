package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement

            loginOnMainPageButton = $$("button").findBy(Condition.text("Войти в аккаунт"));
}
