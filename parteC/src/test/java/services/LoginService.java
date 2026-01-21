package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.LoginRequest;
import utils.ConfigLoader;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.given;

public class LoginService {
    public Response makeLogin(LoginRequest payload) {
        return given()
            .baseUri(ConfigLoader.getProperty("api.base.uri"))
            .header("Content-Type", "application/json")
            .body(payload)
        .when()
            .post("/auth/login")
        .then()
            .log().ifValidationFails()
            .extract().response();
    }

    public Response statusTest(int statusCode) {
        return given()
            .baseUri(ConfigLoader.getProperty("api.status.uri"))
        .when()
            .get("/status/" + statusCode);
    }
}