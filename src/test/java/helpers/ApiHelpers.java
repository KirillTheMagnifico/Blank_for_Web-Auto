package helpers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import ru.rendezvous.config.App;
import ru.rendezvous.models.captcha.CaptchaResponse;
import ru.rendezvous.models.checkPhoneCode.CheckPhoneCodeRequest;
import ru.rendezvous.models.productsId.ProductsIdResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class ApiHelpers {
    public static String[] getCaptchaForOldSite() {

        RestAssured.baseURI = App.config.apiUrl();

        String captchaId = given()
                .when()
                .get("/captcha")
                .then()
                .statusCode(200)
                .extract()
                .path("captcha.captcha_id");

        RestAssured.baseURI = App.config.webUrl();
                //RestAssured.baseURI.substring(0,27); // убираем api_v3 из урла

        String captcha = given().when().log().uri()
                .get("uploads/test_captcha.php?captcha_id=" + captchaId)
                .getBody().asString();

        RestAssured.baseURI = App.config.apiUrl();

        return new String[] {captchaId, captcha};
    }

    public static String createAuthSession(String type) {
        
        String authLogin = "";

        switch (type) {
            case "order": authLogin = App.config.apiOrdersUserLogin();
                      break;
            case "other": authLogin = App.config.apiUserLogin();
                      break;
            case "webDefault": authLogin = App.config.userLogin();
                      break;
        }

        String[] captchaKeys = getCaptchaForOldSite();

        Map<String, Object> body = new HashMap<>();
            body.put("login", authLogin);
            body.put("pass", App.config.userPassword());
            body.put("device", "ios");
            body.put("version", "3.19.0");
            body.put("captcha_id", captchaKeys[0]);
            body.put("captcha", captchaKeys[1]);

        return given()
            .body(body)
            .when()
            .post("/v2/login")
            .then()
            .statusCode(200)
            .extract()
            .cookie("PHPSESSID3");

    }

//    public static String apiFindSize() {
//        String sizeId = given()
//           // .filter(withCustomTemplates())
//            .when()
//            .get("/products/" + getModelById())
//            .then()
//            .statusCode(200)
//            .extract()
//            .path("product.available_sizes[0].product_size_id");
//
//        return sizeId;
//    }

    public static String apiFindSize() {
        String sizeId = "";
        ProductsIdResponse productsIdResponse = given()
                .spec(requestOrdersAuthSpec)
                .when()
                .get("/products/" + getModelById())
                .then()
               // .spec(responseSpec)
                .statusCode(200)
                .extract().as(ProductsIdResponse.class);

        for(int i = 0; i < productsIdResponse.getProduct().getAvailableSizes().size(); i++) {
            if (productsIdResponse.getProduct().getAvailableSizes().get(i).getStockCount() > 0) {
                sizeId = productsIdResponse.getProduct().getAvailableSizes().get(i).getProductSizeId();
                break;
            }
        }

        return sizeId;
    }

    public static void apiValidateBonuses(boolean isValidateForInfo) {

        RequestSpecification spec;
        String login;

        if (isValidateForInfo) {
            spec = requestAuthSpec;
            login = App.config.apiUserLogin();
        }
        else {
            spec = requestOrdersAuthSpec;
            login = App.config.apiOrdersUserLogin();
        }

        CaptchaResponse captchaResponse = given()
                .spec(spec)
                .when()
                .get("captcha?case=1")
                .then()
                .spec(responseSpec)
                .extract().as(CaptchaResponse.class);

        String captchaId = captchaResponse.getCaptchaItem().getCaptchaId();

        String captcha = given().when()
                .get(App.config.webUrl() + "uploads/test_captcha.php?captcha_id=" + captchaId)
                .getBody().asString();

        CheckPhoneCodeRequest сheckPhoneCodeRequest = new CheckPhoneCodeRequest();
        CheckPhoneCodeRequest сheckPhoneCodeVerifyRequest = new CheckPhoneCodeRequest();


        сheckPhoneCodeRequest.setPhone(login);
        сheckPhoneCodeRequest.setCaptchaCode(captcha);
        сheckPhoneCodeRequest.setCaptchaId(captchaId);

        given()
                .spec(spec)
                .body(сheckPhoneCodeRequest)
                .when()
                .post("/check/phone/code")
                .then()
                .spec(responseSpec);

        String phoneNoSymbols = login.replaceAll("[^\\p{L}\\p{N}]+", "");
        String smsCode = new GetSmsCode().getSmsCode(phoneNoSymbols);

     //   String smsCode = new GetSmsCode().getSmsCode(App.config.apiOrdersUserLogin());

        сheckPhoneCodeVerifyRequest.setPhone(login);
        сheckPhoneCodeVerifyRequest.setCode(smsCode);

        given()
                .spec(spec)
                .body(сheckPhoneCodeVerifyRequest)
                .when()
                .post("/check/phone/code/verify")
                .then()
                .spec(responseSpec);

    }
}
