package com.rest.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETNONBDDAPI {
	
	//prepare the request
	//hit the API
	//get the response
	//fetch the values from response
	
	@Test
	public void getUser_Non_Bdd_Test(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k");
		
		Response response = request.get("public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
	}
	
	
	@Test
	public void getUser_Non_Bdd_WithQueryParams_Test(){
				
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k");
		
		request.queryParam("first_name", "John");
		request.queryParam("gender", "male");
		
		Response response = request.get("public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		System.out.println(response.getCookie("PHPSESSID"));
		System.out.println(response.getStatusLine());
		System.out.println(response.getContentType());

		JsonPath js = response.jsonPath();
		System.out.println(js.getString("_meta.success"));
		Assert.assertEquals(js.getString("_meta.success"), true);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");

	}
	
	
	@Test
	public void getUser_Non_Bdd_HashMap_QueryParams_Test(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("first_name", "John");
		params.put("gender", "male");
		
		request.queryParams(params);
		
		Response response = request.get("public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");
	}
	
	
	
	
	
	
	

}
