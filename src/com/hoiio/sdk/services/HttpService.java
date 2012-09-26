package com.hoiio.sdk.services;

/*
Copyright (C) 2012 Hoiio Pte Ltd (http://www.hoiio.com)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import net.sf.json.JSONObject;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.exception.InternalServerErrorException;
import com.hoiio.sdk.objects.HoiioRequest;
import com.hoiio.sdk.util.StringUtil;

public class HttpService {
	
	private static enum Params {
		APP_ID, ACCESS_TOKEN;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	private static final String API_BASE_URL = "https://secure.hoiio.com/open/";
	
	protected static final String INTERNAL_SERVER_EXCEPTION = "error_internal_server_error";
	
	protected static final String API_OUT_STATUS = "status";	
	protected static final String API_OUT_SUCCESS = "success_ok";
	
	protected String appId;
	protected String accessToken;
	
	/**
	 * Makes a HTTP request to Hoiio
	 * @param urlString URL of the API
	 * @param map Content of the request
	 * @return response from Hoiio
	 * @throws HoiioException
	 */
	protected JSONObject doHttpPost(String urlString, HoiioRequest map) throws HoiioException {
		
		String output = "";
		
		HttpURLConnection urlConn = null;
		
		map.putAll(initParam());
		
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
			
			if (!API_OUT_SUCCESS.equals(status)) {
				throw new HoiioException(status, json.toString());
			}
			
			return json;
		} catch (IOException e) {
			throw new InternalServerErrorException(e);
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
	}
	
	/**
	 * Initiates the default parameters: appID and accessToken
	 * @return A map containing appID and accessToken
	 */
	protected HashMap<String, String> initParam() {
		HashMap<String, String> map = new HashMap<String, String>();
		if (appId != null) {
			map.put(Params.APP_ID.toString(), appId);
		} 
		
		if (accessToken != null) {
			map.put(Params.ACCESS_TOKEN.toString(), accessToken);
		}
		
		return map;
	}
	
	private String doHttpURLConnectionPost(HttpURLConnection con, 
			String content) throws HoiioException {
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
			
		} catch (MalformedURLException e) {
			throw new InternalServerErrorException(e);
		} catch (IOException e) {
			throw new InternalServerErrorException(e);
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
}
