package com.ReusableLibraries;

import org.testng.Assert;
import org.testng.Reporter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableLibrary1 {
	public void setBaseUri(String baseUri) throws Exception
	{
		RestAssured.baseURI = baseUri;
	}
	
	public Response postCall(String endpoint,String dynamicValue1,String dynamicValue2) throws Exception
	{
		String requestBody = "{\n" + "  \"title\": \"" +  dynamicValue1 + "\",\n" + "  \"body\": \"" + dynamicValue2  + "\",\n" + "  \"userId\": \"1\" \n}";
		Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).when().post(endpoint).then().log().all().extract().response();
		Reporter.log("Response: " + response.asString());
		return response;
	}
	
	public void validateTitle(JsonPath jsonObject,String title) throws Exception
	{
		Assert.assertEquals(jsonObject.getString("title"), title);
		Reporter.log("Title as "+ title + " validated successfully");
	}
	
	public void validateBody(JsonPath jsonObject,String body) throws Exception
	{
		Assert.assertEquals(jsonObject.getString("body"), body);
		Reporter.log("Body as "+ body + " validated successfully");
	}
	
	public void validateStatusCode(Response response,int statusCode) throws Exception
	{
		Assert.assertEquals(response.getStatusCode(), statusCode);
		Reporter.log("Status code as "+ statusCode + " validated successfully");
	}
}
