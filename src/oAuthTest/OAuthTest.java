package oAuthTest;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

public class OAuthTest {

	public static void main(String[] args) {
		String[] webAuomationCourseTitles = {"Selenium Webdriver Java", "Cypress" ,"Protractor"};//60(expected array)
		
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println("access token issssssssssss   " + accessToken);
		GetCourse getCourseObject = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(getCourseObject);
		System.out.println(getCourseObject.getLinkedIn());//58
		System.out.println(getCourseObject.getInstructor());//58
        //59 price of Soap UI Webservices testing
		System.out.println(getCourseObject.getCourses().getApi().get(1).getCourseTitle());// to print course name
		List<Api> apiCourses = getCourseObject.getCourses().getApi();
		for (int i=0;i<apiCourses.size();i++) {
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());// to print course price
			}
		}
		//60 print all courses in webAutomation
		//list of courses is dynamic, thats why we use arryList to store the course titles
		ArrayList<String> arryList =new ArrayList<String>();
		List<WebAutomation> webAuomationCourse = getCourseObject.getCourses().getWebAutomation();
		for(int j=0;j<webAuomationCourse.size();j++) {
			System.out.println(webAuomationCourse.get(j).getCourseTitle());// this is to print
			arryList.add(webAuomationCourse.get(j).getCourseTitle());//(actual arryList) this is to compare
		}
		List<String> expectedList = Arrays.asList(webAuomationCourseTitles);//convert array to arryList
		Assert.assertTrue(arryList.equals(expectedList));
		
		
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
 * scope: trust
 ******************************************************************
 * GetCourseDetails EndPoint (Secured by OAuth) : https://rahulshettyacademy.com/oauthapi/getCourseDetails
 * HTTP Method : GET
 * Query Parameter : access_token
 */