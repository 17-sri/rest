package jira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

//46
public class Bug_Test {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://srikanthv.atlassian.net/";
		String createIssueResponse = given()
		.header("Content-Type","application/json")
		.header("Authorization","Basic c3Jpa2FudGh2MTcwOUBnbWFpbC5jb206QVRBVFQzeEZmR0YwTld5S0VTUjFzZFRseDc1RHliVVpHR3dLSjlkTHRzQmk1SEU2TVBFRzNDdmROZ0xIb1pFYzRaQkZWVVgxYXlHM04zVHdTWUlCMldDUHhXUThwLVNEbE44M1hFRjZIdUJ1amlUZkczVWY5QlhhWlg1OFpWWW9sVmtPejNMeVZuODhudlNhZDNfMUo1NWx1QnZmdEhOVlltcGtiYld4ZXhpNVVDUWxQZUFDbkNVPTJBOUU4MjI0")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"MenuItems are not working - Automation\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		JsonPath jsonPath = new JsonPath(createIssueResponse);
		String issueID = jsonPath.getString("id");
		System.out.println(issueID);
	}

}
