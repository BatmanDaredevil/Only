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
	
	public Response postCall(String endpoint,String dynamicValue1,String dynamicValue2,String dynamicValue3) throws Exception
	{
		String payload="{\r\n" +" \"name\" : \"Rest Assured SBA Assesment\",\r\n" +
				" \"isbn\":  \"" + dynamicValue1 + "\",\r\n" +" \"aisle\":\"" + dynamicValue2 + 
				"\",\r\n" +" \"author\":\"" + dynamicValue3 + "\"\r\n" +"}\r\n" + "";
		Response response = RestAssured.given().contentType(ContentType.JSON).body(payload).when().post(endpoint).then().log().all().extract().response();
		Reporter.log("Response: " + response.asString());
		return response;
	}
	
	public void validateMsg(JsonPath jsonObject,String msg) throws Exception
	{
		Assert.assertEquals(jsonObject.getString("Msg"), msg);
		Reporter.log("Msg as "+ msg + " validated successfully");
	}
	
	public void validateId(JsonPath jsonObject,String id) throws Exception
	{
		Assert.assertEquals(jsonObject.getString("ID"), id);
		Reporter.log("ID as "+ id + " validated successfully");
	}
	
	public void validateStatusCode(Response response,int statusCode) throws Exception
	{
		Assert.assertEquals(response.getStatusCode(), statusCode);
		Reporter.log("Status code as "+ statusCode + " validated successfully");
	}
}
