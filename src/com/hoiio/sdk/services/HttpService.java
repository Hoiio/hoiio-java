package com.hoiio.sdk.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.util.StringUtil;

public class HttpService {
	
	private static final String PARAM_APP_ID = "app_id";
	private static final String PARAM_ACCESS_TOKEN = "access_token";
	private static final String API_BASE_URL = "http://secure.hoiio.com/open/";
	
	protected static final String INTERNAL_SERVER_EXCEPTION = "internal_server_exception";
	protected static final String API_OUT_STATUS = "status";
	
	protected static final String API_OUT_SUCCESS = "success_ok";
	
	protected static Map<String, Object> doHttpPost(String urlString, HashMap<String, String> map) throws HoiioException {
		
		String output = "";
		
		HttpURLConnection urlConn = null;
		try {
			URL url = new URL(API_BASE_URL + urlString);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			String content = StringUtil.convertMapToUrlEncodedString(map);
			
			output = doHttpURLConnectionPost(urlConn, content);

			JSONObject json = JSONObject.fromObject(output);
			
			if (json == null) {
				throw new HoiioException(INTERNAL_SERVER_EXCEPTION);
			}
			
			String status = json.getString(API_OUT_STATUS);
			if (status == null) {
				throw new HoiioException(INTERNAL_SERVER_EXCEPTION);
			}
			
			return parseResult(json);
		} catch (java.io.IOException e) {
			throw new HoiioException(INTERNAL_SERVER_EXCEPTION);
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
	}
	
	private static String doHttpURLConnectionPost(HttpURLConnection con, String content) throws HoiioException {
		String output = "";

		BufferedReader br = null;
		InputStream is = null;

		OutputStream os = null;

		try {
			con.setConnectTimeout(60000);
			con.setReadTimeout(60000);

			os = con.getOutputStream();
			os.write(content.getBytes("UTF-8"));

			if (con.getResponseCode() >= 400) {
				is = con.getErrorStream();
			} else {
				is = con.getInputStream();
			}

			br = new BufferedReader(new InputStreamReader(is));

			StringBuilder sb = new StringBuilder();
			String str;
			while (null != ((str = br.readLine()))) {
				sb.append(str);
			}
			output = sb.toString();
		} catch (java.net.MalformedURLException e) {
			throw new HoiioException(INTERNAL_SERVER_EXCEPTION);
		} catch (java.io.IOException e) {
			throw new HoiioException(INTERNAL_SERVER_EXCEPTION);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (os != null) {
				try {
					os.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return output;
	}
	
	protected static HashMap<String, String> initParam(String appId, String accessToken) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(PARAM_APP_ID, appId);
		map.put(PARAM_ACCESS_TOKEN, accessToken);
		
		return map;
	}
	
	private static Map<String, Object> parseResult(JSONObject json) throws HoiioException {
		String status = json.getString(API_OUT_STATUS);
		
		if (!API_OUT_SUCCESS.equals(status)) {
			throw new HoiioException(status);
		}
		
		return StringUtil.jsonToMap(json);
	}
}
