package map_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payLoad;

public class Update_Place {// update the place with new address
    public static void main(String[] args) throws InterruptedException {
        //given : all input details
    	//when : submit the API - resource, http method
    	//then : validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given()
            .log().all()
            .queryParam("key", "qaclick123")
            .header("Content-Type", "application/json")
            .body(payLoad.AddPlace()) 
        .when()
            .post("maps/api/place/add/json")
        .then()
            //.log().all()
            .assertThat()
            .statusCode(200)
            .body("scope", equalTo("APP"))
            .header("Server", "Apache/2.4.52 (Ubuntu)")
            .extract().response().asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response); // for parsing json
        String placeId = jsonPath.getString("place_id");
        System.out.println(placeId);
        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
        .body("{\r\n"
        		+ "\"place_id\":\""+placeId+"\",\r\n"
        		+ "\"address\":\"70 Summer walk, USA\",\r\n"
        		+ "\"key\":\"qaclick123\"\r\n"
        		+ "}")
        .when().put("maps/api/place/update/json")
        .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
     
        
        
    }
}
