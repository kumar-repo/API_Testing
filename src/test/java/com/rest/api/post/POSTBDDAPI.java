package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTBDDAPI {
	
	@Test
	public void tokenPostBDDAPI_JSONSTRING_Test(){
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().log().all()
			.contentType(ContentType.JSON)
			.body("{\"username\" : \"admin\",\"password\" : \"password123\"}")
		.when().log().all()
			.post("/auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	@Test
	public void tokenPOSTBDDAPI_FILE_TEST(){
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenID = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("/Users/NaveenKhunteta/Documents/workspace/Nov2019APIBatch"
					+ "/src/test/java/DataFiles/credentials.json"))
		.when().log().all()
			.post("/auth")
		.then().log().all()
			.extract().path("token");
		System.out.println(tokenID);
		Assert.assertNotNull(tokenID);
		
	}
	
	@Test
	public void createUser_Post_API_JSONStringTest(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
			.body("{\"first_name\": \"Brian\",\"last_name\": \"Ratke\",\"gender\": \"male\",\"email\": \"naveen1@roberts.com\",\"status\": \"active\"}")
		.when().log().all()
			.post("/public-api/users")
		.then().log().all()
			.assertThat()
				.body("_meta.success", equalTo(true));
	}
	
	
	@Test
	public void createUser_Post_API_FileTest(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
			.body(new File("/Users/NaveenKhunteta/Documents/workspace/Nov2019APIBatch"
					+ "/src/test/java/DataFiles/user.json"))
		.when().log().all()
			.post("/public-api/users")
		.then().log().all()
			.assertThat()
				.body("_meta.success", equalTo(true));
	}
	

}
