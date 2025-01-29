package map_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Static_payLoad {
	// Validate if Add API is working as expected
	public static void main(String[] args) throws InterruptedException, IOException {
		// given : all input details
		// when : submit the API - resource, http method
		// then : validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Rest_Course\\addPlace.json")))).when()
				.post("maps/api/place/add/json").then()// 40
				// .log().all()
				.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response); // for parsing json
		String placeId = jsonPath.getString("place_id");
		System.out.println(placeId);

	}
}
