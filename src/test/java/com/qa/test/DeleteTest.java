package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.baseClass.AbstractClass;
import com.qa.client.RestClient;

public class DeleteTest extends AbstractClass {

	RestClient client;
	String url;
	AbstractClass base;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setup() {
		base = new AbstractClass();
		url = prop.getProperty("baseUrl") + prop.getProperty("deleteUrl");
	}

	@Test(enabled = true)
	public void DeleteUserTest() throws ClientProtocolException, IOException {
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		client = new RestClient();
		httpResponse = client.delete(url, headerMap);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code --->" + statusCode);
		Assert.assertEquals(statusCode, 204, "Status code is not 204");
	}

}
