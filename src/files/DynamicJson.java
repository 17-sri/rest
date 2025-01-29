package files;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("ContentType","application/json")
		.body(payLoad.Addbook())
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath jsonPath = ReusableMethods.rawToJson(response);
		String id = jsonPath.get("ID");
		System.out.println(id);//35
		
	}
}
