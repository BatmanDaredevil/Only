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
	
	public void validateBookName(JsonPath jsonObject,String bookName) throws Exception
	{
		int n = jsonObject.getList("books").size();
		boolean containsBook = false;
		for (int i=0; i<n; i++)
		{
			System.out.println(jsonObject.getString("books["+i+"].title"));
			if(jsonObject.getString("books["+i+"].title").equals("Learning JavaScript Design Patterns"))
			{
				containsBook = true;
			}
		}
		Assert.assertTrue(containsBook);
		Reporter.log("Book name as "+ bookName + " validated successfully");
	}
	
	
	public void validateStatusCode(Response response,int statusCode) throws Exception
	{
		Assert.assertEquals(response.getStatusCode(), statusCode);
		Reporter.log("Status code as "+ statusCode + " validated successfully");
	}
}
