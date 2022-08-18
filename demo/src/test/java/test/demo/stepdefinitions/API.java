package test.demo.stepdefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;

public class API {

	RequestSpecification request;
	Response response;
	String EndPoint,id;
	public static final String requestBody = "src/test/resources/JsonRequests/request.json";
	JSONObject req_jsonObject;

	@Given("^The endpoint$")
	public void the_endpoint() {
		EndPoint = "https://6143a99bc5b553001717d06a.mockapi.io/testapi/v1/Users";
	}

	@When("^Get call made$")
	public void get_call_made() {
    	response = SerenityRest.given().header("Content-Type", "application/json").when().get(EndPoint);
	System.out.println("get response "+response.then().extract().asString());
	}

	@Then("^verify successfully posted$")
	public void verify_successfully_posted() {
		System.out.println("GET stcode "+response.getStatusCode());
		System.out.println("GET stcode "+response.then().log().all());
		
//		validate  response statuscode
		response.then().assertThat().statusCode(200);
		
//		validate response of specific field  id=255
		response.then().assertThat().body("x[98].createdAt", Matchers.equalTo(id));  //      https://jsonpathfinder.com/
		
//		retrieve the last json id
		List<String> ids=response.jsonPath().get("id");
		int size=ids.size();
		String lastId=ids.get(size-1);
		System.out.println("retrieve last id "+lastId);
		
//		validate the retrieved last id against post 
		System.out.println("POST call id value "+id);
		System.out.println("GET call id value(last index) "+id);
		response.then().assertThat().body("x[98].createdAt", Matchers.equalTo(lastId)); 
	}

	@When("^request sent$")
	public void request_sent() throws FileNotFoundException, IOException, ParseException {
		 String filepath=requestBody;
		 JSONParser parser=new JSONParser();
		 Object obj=parser.parse(new FileReader(filepath));
		 req_jsonObject=(JSONObject)obj;
		 System.out.println(req_jsonObject.toJSONString());
	}


	@When("^Post call made$")
	public void post_call_made1() {
		response = SerenityRest.given().contentType("application/json").body(req_jsonObject.toString()).when().post(EndPoint); 
		
//		retrieve and save id value from post
		id=response.jsonPath().getString("id");
		System.out.println("post call successful can see id "+id);
		
//		response = RestAssured.given().header("Content-Type","application/json").body(req_jsonObject.toString()).when().post(EndPoint); 
	
	}

	@Then("^verify post call successfull$")
	public void verify_post_call_successfull() {
		System.out.println("POST stcode "+response.getStatusCode());
		response.then().assertThat().statusCode(201);
	}

}
