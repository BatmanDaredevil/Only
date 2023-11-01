package com.ReusableLibraries;

import org.testng.Assert;
import org.testng.Reporter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableLibrary2 {
	public void setBaseUri(String baseUri) throws Exception
	{
		RestAssured.baseURI = baseUri;
	}
	
	public Response getCall(String endpoint) throws Exception
	{
		Response response = RestAssured.when().get(endpoint).then().log().all().extract().response();
		Reporter.log("Response: " + response.asString());
		return response;
	}
	
	public void validateFirstName(JsonPath jsonObject,String firstName) throws Exception
	{
		Assert.assertEquals(jsonObject.getString("data.first_name"), firstName);
		Reporter.log("First name as "+ firstName + " validated successfully");
	}
	
	public void validateLastName(JsonPath jsonObject,String lastName) throws Exception
	{
		Assert.assertEquals(jsonObject.getString("data.last_name"), lastName);
		Reporter.log("Last name as "+ lastName + " validated successfully");
	}
	
	public void validateStatusCode(Response response,int statusCode) throws Exception
	{
		Assert.assertEquals(response.getStatusCode(), statusCode);
		Reporter.log("Status code as "+ statusCode + " validated successfully");
	}
}
