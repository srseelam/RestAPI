package com.qa.utility;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qa.client.RestClient;

public class Library {


	// This method is to get the value of the JSON based on key
	public static String getValueOfJSON(JSONObject responseJSON, String jPath) {
		Object obj = responseJSON;

		for (String temp : jPath.split("/")) {
			if (!temp.isEmpty()) {
				if (!(temp.contains("[") || temp.contains("["))) {
					/*
					 * If the value need to get from out side array then this
					 * block of code will be executed
					 */
					obj = ((JSONObject) obj).get(temp);
				} else if (temp.contains("[") || temp.contains("[")) {
					/*
					 * If the value need to get from inside array then this
					 * block of code will be executed
					 */
					obj = ((JSONArray) ((JSONObject) obj).get(temp.split("\\[")[0]))
							.get(Integer.parseInt(temp.split("\\[")[1].replace("]", "")));
				}

			}
		}

		return obj.toString();
	}

	public static String getJSONValueBasedOnKey(JSONObject responseJSON, String jPath)
			throws ClientProtocolException, IOException {

		return responseJSON.get(jPath).toString();

	}

	public static Object getJSONArryForIndex(JSONObject responseJSON, String jPath, int index)
			throws ClientProtocolException, IOException {

		JSONArray obj;
		obj = responseJSON.getJSONArray(jPath);
		
		return obj.get(index);

	}
}
