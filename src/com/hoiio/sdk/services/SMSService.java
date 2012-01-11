package com.hoiio.sdk.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.util.StringUtil;

public class SMSService extends HttpService {
	
	private static final String PARAM_DEST = "dest";
	private static final String PARAM_SENDER_NAME = "sender_name";
	private static final String PARAM_MSG = "msg";
	private static final String PARAM_TAG = "tag";
	private static final String PARAM_NOTIFY_URL = "notify_url";
	private static final String PARAM_FROM = "from";
	private static final String PARAM_TO = "to";
	private static final String PARAM_PAGE = "page";
	private static final String PARAM_TXN_REF = "txn_ref";
	
	private static final String URL_SMS_SEND = "sms/send";
	private static final String URL_SMS_GET_RATE = "sms/get_rate";
	private static final String URL_SMS_GET_HISTORY = "sms/get_history";
	private static final String URL_SMS_QUERY_STATUS = "sms/query_status";

	public static Map<String, Object> send(String appId, String accessToken, String dest, 
			String senderName, String msg, String tag, String notifyUrl) throws HoiioException {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_DEST, dest);
		map.put(PARAM_MSG, msg);
		map.put(PARAM_SENDER_NAME, senderName);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		
		return doHttpPost(URL_SMS_SEND, map);
	}
	
	public static Map<String, Object> getRate(String appId, String accessToken, 
			String dest, String msg) throws HoiioException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_DEST, dest);
		map.put(PARAM_MSG, msg);
			
		return doHttpPost(URL_SMS_GET_RATE, map);		
	}
	
	public static Map<String, Object> queryStatus(String appId, String accessToken,  
			String txnRef) throws HoiioException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_TXN_REF, txnRef);
			
		return doHttpPost(URL_SMS_QUERY_STATUS, map);		
	}
	
	public static Map<String, Object> getHistory(String appId, String accessToken,
			Date from, Date to, Integer page) throws HoiioException {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
				
		map.put(PARAM_FROM, StringUtil.dateToString(from));
		map.put(PARAM_TO, StringUtil.dateToString(to));
		map.put(PARAM_PAGE, page == null ? null : page.toString());
				
		return doHttpPost(URL_SMS_GET_HISTORY, map);
	}	
}