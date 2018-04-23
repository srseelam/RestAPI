package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.parser.Entity;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// GET method without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getHttp = new HttpGet(url);
		CloseableHttpResponse httpResponse = httpClient.execute(getHttp);
		return httpResponse;
	}

	// GET method with Headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getHttp = new HttpGet(url);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			getHttp.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse httpResponse = httpClient.execute(getHttp);
		return httpResponse;
	}

	// POST method
	public CloseableHttpResponse post(String url, String stringEntity, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();//Create default client
		HttpPost httpPost = new HttpPost(url);//Create post and add url
		for (Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());//add headers
		}
		httpPost.setEntity(new StringEntity(stringEntity));//Add body
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost); //add body to post request
		return httpResponse; //return http response
	}

	// PUT method
		public CloseableHttpResponse put(String url, String stringEntity, HashMap<String, String> headerMap)
				throws ClientProtocolException, IOException {

			CloseableHttpClient httpClient = HttpClients.createDefault();//Create default client
			HttpPut httpPut = new HttpPut(url);//Create post url
			for (Entry<String, String> entry : headerMap.entrySet()) {
				httpPut.addHeader(entry.getKey(), entry.getValue());//add headers
			}
			httpPut.setEntity(new StringEntity(stringEntity));//Add body
			CloseableHttpResponse httpResponse = httpClient.execute(httpPut); //add body to post request
			return httpResponse; //return http response
			
		}
		
		// DELETE method
		public CloseableHttpResponse delete(String url, HashMap<String, String> headerMap)
				throws ClientProtocolException, IOException {

			CloseableHttpClient httpClient = HttpClients.createDefault();//Create default client
			HttpDelete httpDelete = new HttpDelete(url);//Create post url
			for (Entry<String, String> entry : headerMap.entrySet()) {
				httpDelete.addHeader(entry.getKey(), entry.getValue());//add headers
			}
			CloseableHttpResponse httpResponse = httpClient.execute(httpDelete); //add body to post request
			return httpResponse; //return http response
			
		}
}
