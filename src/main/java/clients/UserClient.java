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
    private static final String DELETE_USER_PATH = "/auth/user";
    private static final String LOGIN_USER_PATH = "/auth/login";

    protected RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .statusCode(202);
    }

    @Step("Логин с кредами пользователя")
    public ValidatableResponse login(String login, String password){
        return given()
                .spec(getSpec())
                .log().all()
                .body("{ \"email\": \"" +  login + "\", \"password\": \""  + password + "\"}")
                .when()
                .post(LOGIN_USER_PATH)
                .then()
                .log().all();
    }
}
