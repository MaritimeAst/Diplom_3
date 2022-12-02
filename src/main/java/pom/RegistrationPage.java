package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.security.PublicKey;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationPage {

    //private final static String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final SelenideElement

            openPersonalCabinetButton = $$("p").findBy(Condition.text("Личный Кабинет")),
            registrationButton = $$("a").findBy(Condition.text("Зарегистрироваться")),
            registrationFormNameInput = $(byXpath("//form/fieldset[1]/div/div/input")),
            registrationFormEmailInput = $(byXpath("//form/fieldset[2]/div/div/input")),
            registrationFormPassInput = $("input[type=password]"),
            registrationSubmitButton = $$("button").findBy(Condition.text("Зарегистрироваться")),
            registrationError = $$("p").findBy(Condition.text("Некорректный пароль")),
            loginAfterRegistrationLink = $$("a").findBy(Condition.text("Войти")),
            loginSubmitButton = $$("button").findBy(Condition.text("Войти")),
            loginEmailInput = $(byXpath("//div/h2[text()='Вход']/following::form/fieldset[1]/div/div/input")),
            loginPasswordInput = $(byXpath("//div/h2[text()='Вход']/following::form/fieldset[2]/div/div/input")),

            loginFromPersonalCabinetButton = $$("button").findBy(Condition.text("Войти")),
            loginOnMainPageButton = $$("button").findBy(Condition.text("Войти в аккаунт")),
            logoLink = $("a[class=AppHeader_header__link__3D_hX]");

    //Переход в Личный кабинет
    public void personalCabinet() {
        openPersonalCabinetButton.click();
    }

    //заполнение формы регистрации
    public void openRegistrationForm(String name, String email, String password) {
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

    //нажатие кнопки Зарегистрироваться
    public void registrationSubmit() {
        registrationSubmitButton.should(enabled);
        registrationSubmitButton.should(Condition.interactable);
        registrationSubmitButton.click();
    }

    //отображение ошибки Некорректный пароль
    public void registrationPasswordError() {
        registrationError.shouldBe(visible);
    }

    //вход из личного кабинета
    public void loginFromPersonalCabinet(String email, String password) {
        loginEmailInput.click();
        loginEmailInput.sendKeys(email);
        loginPasswordInput.click();
        loginPasswordInput.sendKeys(password);
    }

    //вход по ссылке после регистрации
    public void loginAfterRegistration(String email, String password) {
        loginEmailInput.click();
        loginEmailInput.sendKeys(email);
        loginPasswordInput.click();
        loginPasswordInput.sendKeys(password);
    }

    //нажатие кнопки Войти
    public void LoginSubmit() {
        loginSubmitButton.should(enabled);
        loginSubmitButton.should(Condition.interactable);
        loginSubmitButton.click();
    }

    //нажатие на логотип
    public void logoLink() {
        logoLink.click();
    }

    //Вход по кнопке Войти на главной странице
    public void loginOnMainPage(String email, String password){
        loginOnMainPageButton.click();
        loginEmailInput.click();
        loginEmailInput.sendKeys(email);
        loginPasswordInput.click();
        loginPasswordInput.sendKeys(password);
    }
}