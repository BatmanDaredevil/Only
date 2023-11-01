package com.RestAssuredTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ReusableLibraries.ReusableLibrary1;
import com.ReusableLibraries.ReusableLibrary2;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestAssuredTest {
	ReusableLibrary1 test1 = new ReusableLibrary1();
	ReusableLibrary2 test2 = new ReusableLibrary2();
	
	@Test(testName = "POST Call Test")
	@Parameters({"baseUri","endpoint","dynamicValue1", "dynamicValue2", "dynamicValue3", "msg", "statusCode"})
	public void postCallTest(String baseUri, String endpoint, String dynamicValue1, String dynamicValue2, String dynamicValue3, String msg, int statusCode)
	{
		try 
		{
			//String baseUri = "http://216.10.245.166";
			//String endpoint = "/Library/Addbook.php";
//			String dynamicValue1 = "2234";
//			String dynamicValue2 = "6796988798";
//			String dynamicValue3 = "A";
//			String msg = "successfully added";
//			int statusCode = 200;
			
			test1.setBaseUri(baseUri);
			Response response = test1.postCall(endpoint,dynamicValue1,dynamicValue2,dynamicValue3);
			JsonPath jsonObject = new JsonPath(response.getBody().asString());
			test1.validateMsg(jsonObject,msg);
			test1.validateId(jsonObject,dynamicValue1+dynamicValue2);
			test1.validateStatusCode(response,statusCode);
		}
		catch(Exception e)
		{
			Reporter.log("POST call failed: " + e.getMessage());
			Assert.fail();
		}
		
	}
	
	@Test(testName = "GET- Call Test")
	@Parameters({"baseUri","endpoint","firstName","lastName","statusCode"})
	public void getCallTest(String baseUri, String endpoint, String firstName, String lastName, int statusCode)
	{
		try
		{
			//String baseUri = "https://reqres.in";
			//String endpoint = "/api/users/2";
//			String firstName = "Janet";
//			String lastName = "Weaver";
//			int statusCode = 200;
			
			test2.setBaseUri(baseUri);
			Response response = test2.getCall(endpoint);
			JsonPath jsonObject = new JsonPath(response.getBody().asString());
			test2.validateFirstName(jsonObject,firstName);
			test2.validateLastName(jsonObject,lastName);
			test2.validateStatusCode(response,statusCode);
		}
		catch(Exception e)
		{
			Reporter.log("GET call failed: " + e.getMessage());
			Assert.fail();
		}
	}
}
