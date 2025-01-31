package oAuthTest;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import pojo.GetCourse;

public class OAuthTest {

	public static void main(String[] args) {
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when()//.log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println("access token issssssssssss   " + accessToken);
		GetCourse getCourseObject = given().queryParam("access_token", accessToken).when()//.log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(getCourseObject);
		System.out.println(getCourseObject.getLinkedIn());//58
		System.out.println(getCourseObject.getInstructor());//58


	}

}
/*
 * API Contract download
 ******************************************************************
 * Authorization Server EndPoint:  https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token 
 * HTTP Method : POST 
 * Form parameters 
 * client_id:692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com
 * client_secret: erZOWM9g3UtwNRj340YYaK_W
 * grant_type: client_credentials
 * scope:trust
 ******************************************************************
 * GetCourseDetails EndPoint (Secured by OAuth) : https://rahulshettyacademy.com/oauthapi/getCourseDetails
 * HTTP Method : GET
 * Query Parameter : access_token
 */