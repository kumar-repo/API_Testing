package com.rest.api.schema;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CheckSchemaTest {
	
	
	@Test
	public void bookings_Schema_Test(){
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("/Users/NaveenKhunteta/Documents/workspace/Nov2019APIBatch"
					+ "/src/test/java/com/rest/api/schema/bookings.json"))
			.when().log().all()
				.post("/booking")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.body(matchesJsonSchemaInClasspath("BookingsSchema.json"));
	}
	
	
	@Test
	public void get_user_API_Schema_Test(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
			.when().log().all()
				.get("/public-api/users?first_name=Reina&gender=male&status=active")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.body(matchesJsonSchemaInClasspath("getuserschema.json"));
	
	}
	

}
