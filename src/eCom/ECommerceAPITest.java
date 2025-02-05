package eCom;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.EComLoginRequest;
import pojo.EComLoginResponse;

import static io.restassured.RestAssured.*;

public class ECommerceAPITest {

	public static void main(String[] args) {// 70
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		EComLoginRequest ecomLoginRequest = new EComLoginRequest();
		ecomLoginRequest.setUserEmail("srikanthv1709@gmail.com");
		ecomLoginRequest.setUserPassword("Selenium@123");
		given().spec(req).body(ecomLoginRequest);
		RequestSpecification reqLogin = given().log().all().spec(req).body(ecomLoginRequest);
		EComLoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract()
				.response().as(EComLoginResponse.class);
		System.out.println("THE TOKEN IS   :   "+ loginResponse.getToken());
		System.out.println("THE USERID IS   :   "+ loginResponse.getUserId());

	}

}
