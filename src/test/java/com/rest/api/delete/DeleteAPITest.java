package com.rest.api.delete;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DeleteAPITest {
	
	@Test
	public void delete_user_API_Test(){
		
		//post --> delete --> get
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
			.header("Authorization", "Bearer _FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k").
		when().log().all()
			.delete("public-api/users/1882")
				.then().log().all()
					.assertThat().contentType(ContentType.JSON)
					.and()
						.body("result", equalTo(null));
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

}
