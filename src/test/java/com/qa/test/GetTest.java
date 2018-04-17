package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.baseClass.AbstractClass;
import com.qa.client.RestClient;

public class GetTest extends AbstractClass {
	AbstractClass base;
	RestClient client;
	String serviceUrl;
	String apiUrl;
	String url; 
	
	
	
	@BeforeMethod
	public void setup() {
		base = new AbstractClass();
		serviceUrl = prop.getProperty("url");
		apiUrl = prop.getProperty("apiUrl");
		url = serviceUrl + apiUrl;
		System.out.println("Final url  =  "+ url);
		
	}

	@Test
	public void verifyuserInfo() throws ClientProtocolException, IOException{
		client = new RestClient();
		client.get(url);
	}
}
