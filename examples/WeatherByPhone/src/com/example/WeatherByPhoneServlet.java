package com.example;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.hoiio.sdk.HoiioService;
import com.hoiio.sdk.exception.HoiioException;

@SuppressWarnings("serial")
public class WeatherByPhoneServlet extends HttpServlet {
	
	private static final String TAG_ASK_ZIP = "askZip";
	private static final String TAG_PLAY = "play";
	private static final String NOTIFY_URL = "[NOTIFY_URL]";
	
	private HoiioService hoiioService = new HoiioService("[APP_ID]", "[ACCESS_TOKEN]");	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callState = request.getParameter("call_state");
		
		Map<String, String> notifyParams = hoiioService.parseNotify(request.getQueryString());
		
		if ("ringing".equals(callState)) {
			// Receive an incoming call
			askZip((String)notifyParams.get("session"));
		} else if ("ongoing".equals(callState)) {
			if (TAG_ASK_ZIP.equals(request.getParameter("tag"))) {
				checkDigitPressed(notifyParams);
			} else{
				hangup((String)notifyParams.get("session"));
			}
			
		} else if ("ended".equals(callState)) {
			// can check status and stuff here
		} else {
			// do nothing for now
		}
	}
	
	private void askZip(String session) {
		try {
			hoiioService.ivrGather(session, "Please enter your 5 digit zip code", 
				5, null, 3, TAG_ASK_ZIP, NOTIFY_URL);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
	
	private void checkDigitPressed(Map<String, String> notifyParams) {
		String digits = notifyParams.get("digits");
		String session = notifyParams.get("session");
		
		if (digits.length() != 5) {
			playMessage(session, "You have entered an invalid ZIP code");
			return;
		}
		
		try {
			SAXParser sp = SAXParserFactory.newInstance().newSAXParser();
			WeatherHandler dh = new WeatherHandler();
			
			sp.parse("http://weather.yahooapis.com/forecastrss?p=" + digits, dh);
			
			playMessage(session, "It is currently " + dh.getTemp() + " degrees, and the condition is "
					+ dh.getCondition() + " in " + dh.getLocation());
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void playMessage(String session, String message) {
		try {
			hoiioService.ivrPlay(session, message, TAG_PLAY, NOTIFY_URL);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
	
	private void hangup(String session) {
		try {
			hoiioService.ivrHangup(session, "Thank you for using our service. Bye bye", null, NOTIFY_URL);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
}
