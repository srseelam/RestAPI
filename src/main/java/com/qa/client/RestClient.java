package com.qa.client;

import java.io.IOException;

import javax.swing.text.html.parser.Entity;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	public void get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		HttpGet getHttp = new HttpGet(url);
		CloseableHttpResponse httpResponse =  httpClient.execute(getHttp);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code---->" + statusCode);
		String stringBody = EntityUtils.toString(httpResponse.getEntity());
		JSONObject jsonBody = new JSONObject(stringBody);
		System.out.println("Json Body ---->" + jsonBody);
	}
	

}
