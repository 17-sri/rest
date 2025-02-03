package serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {
	public static void main(String args[]) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setLanguage("French-IN");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("https://rahulshettyacademy.com");
		addPlace.setName("Frontline house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		addPlace.setTypes(myList);
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		Response response = given().queryParam("key", "qaclick123")
				.body(addPlace).when()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
		String responseString = response.asString();
		System.out.println(responseString);

	}
}
