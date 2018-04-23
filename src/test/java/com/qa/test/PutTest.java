package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.baseClass.AbstractClass;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.utility.Library;

public class PutTest extends AbstractClass {

	String url;
	AbstractClass base;
	RestClient client;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setup() {
		base = new AbstractClass();
		url = prop.getProperty("baseUrl") + prop.getProperty("putUrl");
	}

	@Test
	public void updateUserTest() throws ClientProtocolException, IOException {
		client = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		ObjectMapper mapper = new ObjectMapper();
		Users userData = new Users("Srini", "test Manager");

		String userJsonString = mapper.writeValueAsString(userData);

		httpResponse = client.put(url, userJsonString, headerMap);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);
		Assert.assertEquals(statusCode, 200 , "Status code is not 200");
		
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject jsonResponse = new JSONObject(responseString);
		
		String updatedName = Library.getValueOfJSON(jsonResponse, "/name");
		System.out.println("updated name --->" + updatedName);
		
		
		Assert.assertEquals(updatedName, userData.getName(), "Name not updated in data base");
	}

}
