package map_API;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class ADD_Place {
    // Validate if Add API is working as expected
    public static void main(String[] args) throws InterruptedException {
        String payLoad = "{\r\n"
                + "  \"location\": {\r\n"
                + "    \"lat\": -38.383494,\r\n"
                + "    \"lng\": 33.427362\r\n"
                + "  },\r\n"
                + "  \"accuracy\": 50,\r\n"
                + "  \"name\": \"Frontline house\",\r\n"
                + "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
                + "  \"address\": \"29, side layout, cohen 09\",\r\n"
                + "  \"types\": [\r\n"
                + "    \"shoe park\",\r\n"
                + "    \"shop\"\r\n"
                + "  ],\r\n"
                + "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
                + "  \"language\": \"French-IN\"\r\n"
                + "}";
        
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given()
            .log().all()
            .queryParam("key", "qaclick123")
            .header("Content-Type", "application/json") // Correct header
            .body(payLoad)
        .when()
            .post("maps/api/place/add/json")
        .then()
            .log().all()
            .assertThat()
            .statusCode(200);
    }
}
