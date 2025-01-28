package map_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payLoad;

public class Get_Place {
    public static void main(String[] args) throws InterruptedException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Step 1: Add Place
        String response = given()
            .log().all()
            .queryParam("key", "qaclick123")
            .header("Content-Type", "application/json")
            .body(payLoad.AddPlace())
        .when()
            .post("maps/api/place/add/json")
        .then()
            .assertThat()
            .statusCode(200)
            .body("scope", equalTo("APP"))
            .header("Server", "Apache/2.4.52 (Ubuntu)")
            .extract().response().asString();
        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);
        String placeId = jsonPath.getString("place_id");
        System.out.println("Place ID: " + placeId);

        // Step 2: Update Place
        String newAddress = "Summer Walk, Africa";
        given().log().all()
            .queryParam("key", "qaclick123")
            .header("Content-Type", "application/json")
            .body("{\r\n"
                + "\"place_id\":\"" + placeId + "\",\r\n"
                + "\"address\":\"" + newAddress + "\",\r\n"
                + "\"key\":\"qaclick123\"\r\n"
                + "}")
        .when()
            .put("maps/api/place/update/json")
        .then()
            .assertThat()
            .log().all()
            .statusCode(200)
            .body("msg", equalTo("Address successfully updated"));

        // Step 3: Get Place
        String getPlaceResponse = given().log().all()
            .queryParam("key", "qaclick123")
            .queryParam("place_id", placeId)  
        .when()
            .get("maps/api/place/get/json")
        .then()
            .assertThat()
            .log().all()
            .statusCode(200)
            .extract().response().asString();

        JsonPath jsonPath2 = ReusableMethods.rawToJson(getPlaceResponse);
        String actualAddress = jsonPath2.getString("address");
        System.out.println("Actual Address: " + actualAddress);
        System.out.println("New Address: " + newAddress);
        Assert.assertEquals(actualAddress, newAddress);//testNG assertion
    }
}
