package eCom;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.EComLoginRequest;
import pojo.EComLoginResponse;
import pojo.OrderDetails;
import pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ECommerceAPITest {

	public static void main(String[] args) {// 70
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		EComLoginRequest ecomLoginRequest = new EComLoginRequest();
		ecomLoginRequest.setUserEmail("srikanthv1709@gmail.com");
		ecomLoginRequest.setUserPassword("Selenium@123");
		given().spec(req).body(ecomLoginRequest);
		RequestSpecification reqLogin = given().log().all().spec(req).body(ecomLoginRequest);
		EComLoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract()
				.response().as(EComLoginResponse.class);
		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();
		System.out.println("THE TOKEN IS   :   " + token);
		System.out.println("THE USERID IS   :   " + loginResponse.getUserId());

		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build(); // 71
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
				.param("productAddedBy", userId).param("productCategory", "Electronics")
				.param("productSubCategory", "Electronics").param("productPrice", "7777")
				.param("productDescription", "Dell").param("productFor", "personal use")
				.multiPart("productImage", new File("C://Rest_Course//screen.png"));

		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all()
				.extract().asString();
		JsonPath jsonPath = new JsonPath(addProductResponse);
		String productId = jsonPath.get("productId");
		System.out.println("productId IS  :  " + productId);

		RequestSpecification createorderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).setContentType(ContentType.JSON).build();// 72
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(productId);
		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
		orderDetailList.add(orderDetails);
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		RequestSpecification createOrderReq = given().log().all().spec(createorderBaseReq).body(orders);
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
				.extract().response().asString();
		System.out.println("product ordered Id is   " + responseAddOrder);
	}

}
