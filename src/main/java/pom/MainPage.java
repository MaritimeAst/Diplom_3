package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement
            bunsButton = $(byXpath("//div/main/section[1]/div[1]/div[1]")),
            saucesButton = $(byXpath("//div/main/section[1]/div[1]/div[2]")),
            fillingButton = $(byXpath("//div/main/section[1]/div[1]/div[3]"));


    @Step("Клик по табу 'Булки'")
    public void clickBunsButton() {
        bunsButton.click();
    }

    @Step("Клик по табу 'Соусы'")
    public void clickSausesButton() {
        saucesButton.click();
    }

    @Step("Клик по табу 'Начинки'")
    public void clickFillingButton() {
        fillingButton.click();
    }

    @Step("Получить class таба 'Булки'")
    public String getClassBunsButton() {
        return bunsButton.getAttribute("class");
    }

    @Step("Получить class таба 'Соусы'")
    public String getClassSaucesButton() {
        return saucesButton.getAttribute("class");
    }

    @Step("Получить class таба 'Начинки'")
    public String getClassFillingButton() {
        return fillingButton.getAttribute("class");
    }
}
