package com.hoiio.sdk.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.util.StringUtil;

public class CallService extends HttpService {
	
	private static final String PARAM_DEST1 = "dest1";
	private static final String PARAM_DEST2 = "dest2";
	private static final String PARAM_CALLER_ID = "caller_id";
	private static final String PARAM_TAG = "tag";
	private static final String PARAM_NOTIFY_URL = "notify_url";
	private static final String PARAM_FROM = "from";
	private static final String PARAM_TO = "to";
	private static final String PARAM_PAGE = "page";
	private static final String PARAM_TXN_REF = "txn_ref";
	private static final String PARAM_DEST = "dest";
	private static final String PARAM_ROOM = "room";
	
	private static final String URL_VOICE_CALL = "voice/call";
	private static final String URL_VOICE_GET_RATE = "voice/get_rate";
	private static final String URL_VOICE_GET_HISTORY = "voice/get_history";
	private static final String URL_VOICE_QUERY_STATUS = "voice/query_status";
	private static final String URL_VOICE_CONFERENCE = "voice/conference";
	private static final String URL_VOICE_HANGUP = "voice/hangup";

	public static Map<String, Object> call(String appId, String accessToken, String dest1, String dest2,
			String callerId, String tag, String notifyUrl) throws HoiioException {
		
		HashMap<String, String> map = initParam(appId, accessToken);
		
		map.put(PARAM_DEST2, dest2);
		map.put(PARAM_DEST1, dest1);
		map.put(PARAM_CALLER_ID, callerId);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		
		return doHttpPost(URL_VOICE_CALL, map);
	}
	
	public static Map<String, Object> getRate(String appId, String accessToken, 
			String dest1, String dest2) throws HoiioException {
		HashMap<String, String> map = initParam(appId, accessToken);
		
		map.put(PARAM_DEST2, dest2);		
		map.put(PARAM_DEST1, dest1);
			
		return doHttpPost(URL_VOICE_GET_RATE, map);
		
	}
	
	public static Map<String, Object> queryStatus(String appId, String accessToken,  
			String txnRef) throws HoiioException {
		HashMap<String, String> map = initParam(appId, accessToken);
		
		map.put(PARAM_TXN_REF, txnRef);
			
		return doHttpPost(URL_VOICE_QUERY_STATUS, map);		
	}
	
	public static Map<String, Object> getHistory(String appId, String accessToken,
			Date from, Date to, Integer page) throws HoiioException {
		
		HashMap<String, String> map = initParam(appId, accessToken);
				
		map.put(PARAM_FROM, StringUtil.dateToString(from));
		map.put(PARAM_TO, StringUtil.dateToString(to));
		map.put(PARAM_PAGE, page == null ? null : page.toString());
				
		return doHttpPost(URL_VOICE_GET_HISTORY, map);
	}
	
	public static Map<String, Object> conference(String appId, String accessToken,
			String dest, String room, String callerId, String tag, 
			String notifyUrl) throws HoiioException {
		
		HashMap<String, String> map = initParam(appId, accessToken);
				
		map.put(PARAM_DEST, dest);
		map.put(PARAM_ROOM, room);
		map.put(PARAM_CALLER_ID, callerId);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
				
		return doHttpPost(URL_VOICE_CONFERENCE, map);
	}
	
	public static Map<String, Object> hangup(String appId, String accessToken,
			String txnRef) throws HoiioException {
		HashMap<String, String> map = initParam(appId, accessToken);
		
		map.put(PARAM_TXN_REF, txnRef);
				
		return doHttpPost(URL_VOICE_HANGUP, map);
	}
}
