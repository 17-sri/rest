package eCom;
// dummy delete code
import static io.restassured.RestAssured.given;
// not working
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Dummydelete {
	RequestSpecification deleteorderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmQxZjBmYWFlMmFmZDRjMGI2MDY0ZGYiLCJ1c2VyRW1haWwiOiJzcmlrYW50aHYxNzA5QGdtYWlsLmNvbSIsInVzZXJNb2JpbGUiOjEyMzQ1Njc4OTAsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE3Mzg4MjAyMjcsImV4cCI6MTc3MDM3NzgyN30.apyA5uEwPyIYonH47-EtqDF9BncsbrEYe-uMjKs-lbQ")
			.setContentType(ContentType.JSON).build();//73
	RequestSpecification deleteProdReq = given().spec(deleteorderBaseReq).pathParam("productId", "67a44a76e2b5443b1f49a6e9");
	String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/67a44a76e2b5443b1f49a6e9").then().extract().response().asString();

}
