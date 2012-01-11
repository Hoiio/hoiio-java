package com.hoiio.sdk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class StringUtil {
	
	public static String dateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(JSONObject json) {
		Iterator<String> keys = json.keys();
		Map<String, Object> outMap = new HashMap<String, Object>();
		while(keys.hasNext()) {
			String key = keys.next();
			String value = json.getString(key);
						
			try {
				/** If the string is JSON type, further get the nested JSONObject **/
				JSONObject nestedJson = JSONObject.fromObject(value);
				Iterator<String> nestedKeys = json.keys();
				Map<String, Object> nestedMap = new HashMap<String, Object>();
				while(nestedKeys.hasNext()) {
					String nestedKey = nestedKeys.next();
					nestedMap.put(nestedKey, nestedJson.getString(nestedKey));
				}
				outMap.put(key, nestedMap);
			} catch (JSONException e) {
				/** If the string is not JSON format, treat as a normal string **/
				outMap.put(key, value);
			}
		}
		return outMap;
	}
	
	public static Map<String, String> parseNvp(final String inputString) {
		if (inputString == null || inputString.trim().equals("")) {
			return new HashMap<String, String>();
		}

		final Map<String, String> hm = new HashMap<String, String>();

		final StringTokenizer st = new StringTokenizer(inputString, "&;");

		while (st.hasMoreTokens()) {
			final String s = st.nextToken();
			final String[] nvp = s.split("=", 2);
			try {
				hm.put(nvp[0], URLDecoder.decode(String.valueOf(nvp[1]), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				return new HashMap<String, String>();
			}
		}
		return hm;
	}
	
	public static String convertMapToUrlEncodedString(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		List<NameValuePair> qparams = new LinkedList<NameValuePair>();
		for (Entry<String, String> item : map.entrySet()) {
			if (item.getValue() != null)
				qparams.add(new BasicNameValuePair(item.getKey(), item.getValue()));
		}
		return URLEncodedUtils.format(qparams, "UTF-8");
	}
	
	public static boolean isEmpty(String str) {
		return (str == null || str.isEmpty());
	}
}
