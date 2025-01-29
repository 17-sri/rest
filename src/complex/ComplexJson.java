package complex;

import org.testng.Assert;
import files.payLoad;
import io.restassured.path.json.JsonPath;
//30,31
public class ComplexJson {
	public static void main(String[] args) {
		JsonPath jsonPath = new JsonPath(payLoad.CoursePrice());
		//1. print number of courses returned by API
		int count = jsonPath.getInt("courses.size()");
		System.out.println(count);
		//2. print purchase amount
		int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		//3. print title of the first course
		String firstCourseTitle = jsonPath.get("courses[0].title");
		System.out.println(firstCourseTitle);
		//4. print all course titles and their respective prices
		for(int i=0;i<count;i++) {
			String courseTitles = jsonPath.get("courses["+i+"].title");
			System.out.println(courseTitles);
			System.out.println(jsonPath.get("courses["+i+"].price").toString());
		}
		//5. print number of copies sold by RPA course
		for(int i=0;i<count;i++) {
			String courseTitles = jsonPath.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA")) {
				int copies = jsonPath.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		//6. verify if sum of all course prices matches with purchase amount
		int sum = 0;
		for(int i=0;i<count;i++) {
			int price = jsonPath.getInt("courses["+i+"].price");
			int copies = jsonPath.getInt("courses["+i+"].copies");
			int amount = price*copies;
			System.out.println(amount);
			sum = sum+amount;
		}
		System.out.println(sum);
		Assert.assertEquals(totalAmount, sum);
	}
}
