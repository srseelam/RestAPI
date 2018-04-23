package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.baseClass.AbstractClass;
import com.qa.client.RestClient;
import com.qa.utility.Library;

public class GetTest extends AbstractClass {
	AbstractClass base;
	RestClient client;
	String url;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setup() {
		base = new AbstractClass();
		url = prop.getProperty("baseUrl") + prop.getProperty("getUrl");

	}

	//Test case without headers
	@Test
	public void verifyuserInfo() throws ClientProtocolException, IOException {
		client = new RestClient();
		httpResponse = client.get(url);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code---->" + statusCode);
		String stringBody = EntityUtils.toString(httpResponse.getEntity());
		JSONObject jsonBody = new JSONObject(stringBody);
		System.out.println("Json Body ---->" + jsonBody);
		
		String totalValue = Library.getValueOfJSON(jsonBody, "/total");
		System.out.println("Value of total is ---->" +  totalValue);
		
		String firstName = Library.getValueOfJSON(jsonBody, "/data[0]/first_name");
		String lastName = Library.getValueOfJSON(jsonBody, "/data[0]/last_name");
		String id = Library.getValueOfJSON(jsonBody, "/data[0]/id");
		String avatar = Library.getValueOfJSON(jsonBody, "/data[0]/avatar");

		System.out.println("Value of First Name ---> " + firstName);
		System.out.println("Value of Last Name ---> " + lastName);
		System.out.println("Value of id  ---> " + id);
		System.out.println("Value of avatar ---> " + avatar);

		
	}
	
	
	//Test case with headers
	@Test
	public void getResponseWithHeader() throws ClientProtocolException, IOException{
		HashMap<String, String> headerMap = new HashMap<String, String>();
		client = new RestClient();
		headerMap.put("Content-Type" , "application/json");
		
		httpResponse = client.get(url, headerMap);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code---->" + statusCode);
		String stringBody = EntityUtils.toString(httpResponse.getEntity());
		JSONObject jsonBody = new JSONObject(stringBody);
		System.out.println("Json Body ---->" + jsonBody);
		
		String totalValue = Library.getValueOfJSON(jsonBody, "/total");
		System.out.println("Value of total is ---->" +  totalValue);
		
		String firstName = Library.getValueOfJSON(jsonBody, "/data[0]/first_name");
		String lastName = Library.getValueOfJSON(jsonBody, "/data[0]/last_name");
		String id = Library.getValueOfJSON(jsonBody, "/data[0]/id");
		String avatar = Library.getValueOfJSON(jsonBody, "/data[0]/avatar");

		System.out.println("Value of First Name ---> " + firstName);
		System.out.println("Value of Last Name ---> " + lastName);
		System.out.println("Value of id  ---> " + id);
		System.out.println("Value of avatar ---> " + avatar);
		
	}
}
