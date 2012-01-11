package com.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WeatherHandler extends DefaultHandler {

	private String temp;
	private String location;
	private String condition;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("yweather:condition".equals(qName)) {
			temp = attributes.getValue("temp");
			condition = attributes.getValue("text");
		}
		
		if ("yweather:location".equals(qName)) {
			location = attributes.getValue("city");
		}
	}
	
	public String getTemp() {
		return temp;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public String getLocation() {
		return location;
	}
}
