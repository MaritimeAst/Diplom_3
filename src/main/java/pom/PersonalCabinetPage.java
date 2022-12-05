package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PersonalCabinetPage {

    private final SelenideElement

            openPersonalCabinetButton = $$("p").findBy(Condition.text("Личный Кабинет")),
            registrationButton = $$("a").findBy(Condition.text("Зарегистрироваться")),
            registrationFormNameInput = $(byXpath("//form/fieldset[1]/div/div/input")),
            registrationFormEmailInput = $(byXpath("//form/fieldset[2]/div/div/input")),
            registrationFormPassInput = $("input[type=password]"),
            registrationSubmitButton = $$("button").findBy(Condition.text("Зарегистрироваться")),
            registrationError = $$("p").findBy(Condition.text("Некорректный пароль")),
            loginSubmitButton = $$("button").findBy(Condition.text("Войти")),
            loginEmailInput = $(byXpath("//div/h2[text()='Вход']/following::form/fieldset[1]/div/div/input")),
            loginPasswordInput = $(byXpath("//div/h2[text()='Вход']/following::form/fieldset[2]/div/div/input")),
            loginOnMainPageButton = $$("button").findBy(Condition.text("Войти в аккаунт")),
            logoLink = $(byXpath("//div/header/nav/div/a")),
            constructorLink = $(byXpath("//div/header/nav/ul/li[1]/a")),
            logoutButton = $$("button").findBy(Condition.text("Выход")),
            restorePasswordLink = $$("a").findBy(Condition.text("Восстановить пароль")),
            loginFromRestorePasswordLink = $(byXpath("//div/main/div/div/p/a[text()='Войти']"));

    @Step("Переход в Личный кабинет")
    public void personalCabinet() {
        openPersonalCabinetButton.click();
    }

    @Step("заполнение формы регистрации")
    public void registration(String name, String email, String password) {
        registrationButton.click();
        registrationFormNameInput.click();
        registrationFormNameInput.clear();
        registrationFormNameInput.sendKeys(name);
        registrationFormEmailInput.click();
        registrationFormEmailInput.clear();
        registrationFormEmailInput.sendKeys(email);
        registrationFormPassInput.click();
        registrationFormPassInput.clear();
        registrationFormPassInput.sendKeys(password);
    }

    @Step("Вход на странице Личный кабинет")
    public void login(String email, String password) {
        loginEmailInput.click();
        loginEmailInput.sendKeys(email);
        loginPasswordInput.click();
        loginPasswordInput.sendKeys(password);
    }

    @Step("Нажатие кнопки Войти")
    public void loginSubmit() {
        loginSubmitButton.should(enabled);
        loginSubmitButton.should(Condition.interactable);
        Configuration.timeout = 600;
        loginSubmitButton.click();
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public void registrationSubmit() {
        registrationSubmitButton.should(enabled);
        registrationSubmitButton.should(Condition.interactable);
        registrationSubmitButton.click();
    }

    @Step("Отображение ошибки Некорректный пароль")
    public void registrationPasswordError() {
        registrationError.shouldBe(visible);
    }


    @Step("Переход на главную страницу по нажатию на логотип")
    public void transitionByLogoLink() {
        logoLink.click();
    }

    @Step("Переход на главную страницу по нажатию на кнопку Конструктор")
    public void transitionByConstructorLink() {
        constructorLink.click();
    }

    @Step("Вход по кнопке Войти на главной странице")
    public void loginOnMainPage() {
        loginOnMainPageButton.shouldBe(Condition.enabled);
        loginOnMainPageButton.shouldBe(Condition.interactable);
        loginOnMainPageButton.click();
    }

    @Step("Нажатие на кнопку Выход в Личном кабинете")
    public void logout() {
        logoutButton.click();
    }

    @Step("Вход по ссылке, на странице восстановления пароля")
    public void loginFromRestorePassword() {
        restorePasswordLink.should(enabled);
        restorePasswordLink.should(Condition.interactable);
        restorePasswordLink.click();
        loginFromRestorePasswordLink.should(enabled);
        loginFromRestorePasswordLink.should(Condition.interactable);
        loginFromRestorePasswordLink.click();
    }
}