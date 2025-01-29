package files;
// tip     >>> Ctrl+3 is for quick search
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("ContentType","application/json")
		.body(payLoad.Addbook(isbn, aisle))//36, 38
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath jsonPath = ReusableMethods.rawToJson(response);
		String id = jsonPath.get("ID");
		System.out.println(id);//35
	}
	@DataProvider(name = "BooksData")//37
	public Object[][] getData() {
		return new Object[][] {{"qwer","437"},{"zxcv","947"},{"poi","838"} };//Multidimensional	
	}
	//array : collection of elements
	//Multidimensional array : collection of arrays
	
	
}
