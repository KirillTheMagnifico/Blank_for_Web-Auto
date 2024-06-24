package ru.rendezvous.apiSpecs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static ru.rendezvous.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static ru.rendezvous.helpers.ApiHelpers.createAuthSession;

public class ApiSpecs {

//    public String sessionId = createAuthSession("other");
//    public String ordersSessionId = createAuthSession("order");

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static RequestSpecification requestAuthSpec = with()
            .cookie("PHPSESSID3", createAuthSession("other"))
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static RequestSpecification requestOrdersAuthSpec = with()
            .cookie("PHPSESSID3", createAuthSession("order"))
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
