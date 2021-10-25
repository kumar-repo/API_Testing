package com.rest.api.post;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutAPI {

	// create a user
	// POST - URL
	// REQUEST JSON BODY -->
	// USER JAVA CLASS (POJO)---> JSON Object
	// Encapsulation --> private variables --> public(getter and setter methods)
	// POJO -- Plain Old Java Object-- Java Class --> private variables -->
	// public(getter and setter methods)

	@Test
	public void update_User_With_Pojo_Test() {

		User user = new User("Nisha", "John", "female", "01-01-1990", "nisha13@gmail.com", "+1-999-9989-990",
				"http://www.naveenautomationlabs.com", "123, new Avenue, SFO, CA", "active");

		// convert Java pojo to json -- Serialization -- JACKSON API

		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println(userJson);
		RestAssured.baseURI = "https://gorest.co.in";

		String userId = given().log().all().contentType(ContentType.JSON)
				.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k").body(userJson).when().log()
				.all().post("/public-api/users").
				then().log().all().
				assertThat().contentType(ContentType.JSON).
				extract().
					path("result.id");
		System.out.println(userId);
		
		//put
		user.setStatus("inactive");
		String userUpdatedJson = null;
		try {
			userUpdatedJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//update using put
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
			.body(userUpdatedJson)
			.when().log().all()
				.put("/public-api/users/"+userId)
			.then().log().all()
				.assertThat()
					.contentType(ContentType.JSON)
						.and()
							.body("result.status", equalTo(user.getStatus()));
				
	}

}
