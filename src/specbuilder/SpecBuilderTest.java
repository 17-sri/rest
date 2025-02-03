package specbuilder;
//67
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {
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
		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(requestSpec).body(addPlace);
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		Response responseSpec = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();
		
		String responseString = responseSpec.asString();
		System.out.println(responseString);

	}
}
