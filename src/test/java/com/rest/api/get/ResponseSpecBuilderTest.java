package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {

	// t1 t2 t3 t4 ...t100
	// status code = 200 --> 201
	// content type
	// header

	ResponseSpecBuilder res = new ResponseSpecBuilder();
	ResponseSpecification resSpec_200_OK = res.
			expectContentType(ContentType.JSON).
			expectStatusCode(200).
			expectHeader("Server", "nginx").
			build();
	
	ResponseSpecification resSpec_400_BAD_REQUEST = res.
			expectStatusCode(400).
			expectHeader("Server", "nginx").
			build();
	
	ResponseSpecification resSpec_401_AUTH_FAIL = res.
			expectStatusCode(401).
			expectHeader("Server", "nginx").
			build();

	@Test
	public void ResponseSpecTest(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		given()
		.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
		.when()
			.get("/public-api/users")
		.then()
			.assertThat()
				.spec(resSpec_200_OK);
	}
	
	@Test
	public void ResponseSpec_Auth_Fail_Test(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		given()
		.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k11111")
		.when()
			.get("/public-api/users")
		.then()
			.assertThat()
				.spec(resSpec_401_AUTH_FAIL);
	}
	

}
