package stepdefinitions;

import static org.junit.Assert.*;

import java.io.IOException;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.APIResources;
import resources.BaseClass;

public class Place_Steps extends BaseClass{

	RequestSpecification req;
	ResponseSpecification res;

	Response response;

	TestDataBuild data=new TestDataBuild();
	
	static String place_id;
	
	@Given("User enter Add place payload with {string} {string} {string}")
	public void user_enter_add_place_payload_with(String name, String language, String address) throws IOException {
	    
		req=given()
				.spec(requestSpec())
				.body(data.addPLacePayload(name,language,address));
	}
	@When("User calls {string} with post http request")
	public void user_calls_with_post_http_request(String resource) {
		res=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		response=req.
				when()
				.post(APIResources.valueOf(resource).getResource())
				.then()
				.log().all()
				.spec(res)
				.extract().response();
	}
	@Then("Verify status code is {int}")
	public void verify_status_code_is(int expectedStatusCode) {

		int actualStatusCode=response.getStatusCode();
		assertEquals(actualStatusCode,expectedStatusCode);
	}

	@Given("User enter Delete place payload")
	public void user_enter_delete_place_payload() throws IOException {
		
		req=given()
		.spec(requestSpec())
		.body(deletePlacePayload(place_id));
	}

}
