package eCom;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
//73
public class DummmmyDelete {
    public static void main(String[] args) {  // code by chatGpt
        // Replace with your actual token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmQxZjBmYWFlMmFmZDRjMGI2MDY0ZGYiLCJ1c2VyRW1haWwiOiJzcmlrYW50aHYxNzA5QGdtYWlsLmNvbSIsInVzZXJNb2JpbGUiOjEyMzQ1Njc4OTAsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE3Mzg4MjAyMjcsImV4cCI6MTc3MDM3NzgyN30.apyA5uEwPyIYonH47-EtqDF9BncsbrEYe-uMjKs-lbQ";
        
        // Replace with the correct product ID
        String productId = "67a2ffe5e2b5443b1f480c6b"; //enter product Id to delete
        
        // Build request specification
        RequestSpecification deleteOrderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token)  // ✅ Fix header format
                .setContentType(ContentType.JSON).build();

        // Send DELETE request
        String deleteProductResponse = given().log().all().spec(deleteOrderBaseReq).pathParam("productId", productId)
                .when()
                .delete("/api/ecom/product/delete-product/{productId}")  // ✅ Correct API end point
                .then().log().all().extract().asString();

        System.out.println("Delete Product Response: " + deleteProductResponse);
    }
}
