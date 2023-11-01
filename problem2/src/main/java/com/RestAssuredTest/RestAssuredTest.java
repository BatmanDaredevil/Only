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
	@Parameters({"baseUri","endpoint","dynamicValue1", "dynamicValue2", "statusCode"})
	public void postCallTest(String baseUri, String endpoint, String dynamicValue1, String dynamicValue2, int statusCode)
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
			Response response = test1.postCall(endpoint,dynamicValue1,dynamicValue2);
			JsonPath jsonObject = new JsonPath(response.getBody().asString());
			test1.validateTitle(jsonObject,dynamicValue1);
			test1.validateBody(jsonObject,dynamicValue2);
			test1.validateStatusCode(response,statusCode);
		}
		catch(Exception e)
		{
			Reporter.log("POST call failed: " + e.getMessage());
			Assert.fail();
		}
		
	}
	
	@Test(testName = "GET- Call Test")
	@Parameters({"baseUri","endpoint","param1","value1","bookName","statusCode"})
	public void getCallTest(String baseUri, String endpoint,String param1, String value1, String bookName, int statusCode)
	{
		try
		{
			//String baseUri = "https://reqres.in";
			//String endpoint = "/api/users/2";
//			String firstName = "Janet";
//			String lastName = "Weaver";
//			int statusCode = 200;
			
			test2.setBaseUri(baseUri);
			Response response = test2.getCall(endpoint,param1,value1);
			JsonPath jsonObject = new JsonPath(response.getBody().asString());
			test2.validateBookName(jsonObject,bookName);
			test2.validateStatusCode(response,statusCode);
		}
		catch(Exception e)
		{
			Reporter.log("GET call failed: " + e.getMessage());
			Assert.fail();
		}
	}
}
