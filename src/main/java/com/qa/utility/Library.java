package com.qa.utility;

import org.json.JSONArray;
import org.json.JSONObject;

public class Library {

	// This method is to get the value of the JSON based on key
	public static String getValueOfJSON(JSONObject responseJSON, String jPath) {
		Object obj = responseJSON;
		for (String temp : jPath.split("/")) {
			if (!temp.isEmpty()) {
				if (!(temp.contains("[") || temp.contains("["))) {
					// If the value need to get from out side array then this
					// block of code will be executed
					obj = ((JSONObject) obj).get(temp);
				} else if (temp.contains("[") || temp.contains("[")) {
					// If the value need to get from inside array then this
					// block of code will be executed
					obj = ((JSONArray) ((JSONObject) obj).get(temp.split("\\[")[0]))
							.get(Integer.parseInt(temp.split("\\[")[1].replace("]", "")));
				}

			}
		}

		return obj.toString();
	}

}
