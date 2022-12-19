package clients;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String MAIN_UI_URL = "https://stellarburgers.nomoreparties.site";
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private static final String DELETE_USER_PATH = "/auth/user";
    private static final String LOGIN_USER_PATH = "/auth/login";

    protected RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Вызов метода API: удаление пользователя с использованием accessToken")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .statusCode(202);
    }

    @Step("Вызов метода API: логин с кредами зарегистрированного пользователя, для получения accessToken")
    public ValidatableResponse login(String login, String password){
        return given()
                .spec(getSpec())
                .body("{ \"email\": \"" +  login + "\", \"password\": \""  + password + "\"}")
                .when()
                .post(LOGIN_USER_PATH)
                .then();
    }
}
