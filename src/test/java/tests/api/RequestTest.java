package tests.api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RequestTest {

        /*
        1. Make request (POST) to https://reqres.in/api/login with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
        2. Get response { "token": "QpwL5tke4Pnpja7X4" }
        3. Check token is QpwL5tke4Pnpja7X4
     */

    @Test
    @Tag("tests")
    void UnsuccessfulLoginTest() {
        String body = "{ \"login\": \"Kirill\", \"password\": \"123456\" }";
// @ login : string
//@ password : string
        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("error", is("Wrong data"));
    }

    @Test
    @Tag("tests")
    @Tag("api")
    void unSuccessfulLoginWithMissingEmailTest() {
        String body = "{ \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    @Tag("tests")
    @Tag("api")
    void unSuccessfulLoginWithMissingPasswordTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @Tag("tests")
    @Tag("api")
    void unSuccessfulLoginWithEmptyDataTest() {
        given()
                .log().uri()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

}
