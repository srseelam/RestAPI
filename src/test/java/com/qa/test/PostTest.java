package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.text.Utilities;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.baseClass.AbstractClass;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.utility.Library;

public class PostTest extends AbstractClass {

	AbstractClass base;
	String url;
	CloseableHttpResponse httpResponse;
	RestClient client;

	@BeforeMethod
	public void setup() {
		base = new AbstractClass();
		url = prop.getProperty("url") + prop.getProperty("apiUrl");
	}

	//Assertion using the user class object
	@Test
	public void createUserTest() throws JsonGenerationException, JsonMappingException, IOException {
		client = new RestClient();
		HashMap<String, String> haderMap = new HashMap<String, String>();
		haderMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();
		Users userData = new Users("Srini", "Test Manager");

		//Java Object to JSON
		// mapper.writeValue(new
		// File("C:\\Users\\sseela\\workspace\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"),
		// userData);

		String userJsonString = mapper.writeValueAsString(userData);
		System.out.println(userJsonString);

		httpResponse = client.post(url, userJsonString, haderMap);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code --->" + statusCode);
		
		//JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON--->" + responseJson);
		
		//JSON to String
		Users userResponseObj = mapper.readValue(responseString, Users.class);
		System.out.println("Users response object--->" + userResponseObj);
		
		System.out.println(userResponseObj.getName());
		System.out.println(userResponseObj.getJob());
		
		Assert.assertEquals(userData.getName(), userResponseObj.getName());
		Assert.assertEquals(userData.getJob(), userResponseObj.getJob());
	}
	
	//Assertion using the utility library
	@Test
	public void verifyCreatedUserWithUtility() throws JsonGenerationException, JsonMappingException, IOException {
		client = new RestClient();
		HashMap<String, String> haderMap = new HashMap<String, String>();
		haderMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();
		Users userData = new Users("Srini", "Test Manager");

		//Java Object to JSON
		// mapper.writeValue(new
		// File("C:\\Users\\sseela\\workspace\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"),
		// userData);

		String userJsonString = mapper.writeValueAsString(userData);
		System.out.println(userJsonString);

		httpResponse = client.post(url, userJsonString, haderMap);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code --->" + statusCode);
		
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		
		Assert.assertEquals(userData.getName(), Library.getValueOfJSON(responseJson, "/name"));
		Assert.assertEquals(userData.getJob(), Library.getValueOfJSON(responseJson, "/job"));
		
	}
}
