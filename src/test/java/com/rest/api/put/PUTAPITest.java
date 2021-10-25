package com.rest.api.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PUTAPITest {

	// Create a user with post call
	// user info
	// update the info with put call
	// 1. other attributes should remain same
	// 2. the attribute which has been changed, need to check

	@Test
	public void update_User_PUT_API_Test() {
		// 1. Create a POST REQUEST with Payload
		User user = new User("Umang", "Sharma", "male", "01-12-1990", "umang20@gmail.com", "+91-998989898",
				"http://www.umang.com", "12 4th floor bangalore", "active");

		// Conver this POJO to JSON -- using JACKSON API - ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;

		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("POST CALL JSON is: "+ userJson );
		
		//write POST CALL:
		RestAssured.baseURI = "https://gorest.co.in";
		
		String userId = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
			.body(userJson).
		when().log().all()
			.post("/public-api/users").
		then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.extract().path("result.id");
		
		System.out.println("user id is:::>"+ userId);
		
		//Call the PUT API:
		user.setEmail("umang.sharma.pune@gmail.com");
		user.setAddress("Hinjewadi, Pune");
		user.setPhone("+91-900000000");
		
		// Conver this POJO to JSON -- using JACKSON API - ObjectMapper
		String updatedUserJson = null;

				try {
					updatedUserJson = mapper.writeValueAsString(user);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k")
			.body(updatedUserJson).
		when().log().all()
			.put("public-api/users/"+userId).
		then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.and()
					.body("result.email", equalTo(user.getEmail()))
				.and()
					.body("result.id", equalTo(userId))
				.and()
					.body("result.first_name", equalTo(user.getFirst_name()));

		
		//post--> get
		//post--> put --> get
		
		
		
	}

}
