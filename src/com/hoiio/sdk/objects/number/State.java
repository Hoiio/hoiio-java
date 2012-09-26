package com.hoiio.sdk.objects.number;

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

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hoiio.sdk.objects.enums.NumberCapability;

public class State {
	
	private static enum Params {
		NAME, CODE, CAPABILITY;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private String name;
	private String code;
	private List<NumberCapability> capability;
	
	/**
	 * Constructs a new {@code State} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public State(JSONObject output) {
		name = output.getString(Params.NAME.toString());
		code = output.getString(Params.CODE.toString());
		capability = new ArrayList<NumberCapability>();
		
		JSONArray capabilities = (JSONArray) output.get(Params.CAPABILITY.toString());
		
		for (int i = 0; i < capabilities.size(); i++) {
			capability.add(NumberCapability.fromString(capabilities.getString(i)));
		}
	}
	
	/**
	 * Gets the capabilities of numbers in this state
	 * @return The capabilities of numbers in this state: voice, sms
	 */
	public List<NumberCapability> getCapability() {
		return capability;
	}

	/**
	 * Gets the state code
	 * @return two letter state code defined in ISO 3166-2
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the state name
	 * @return state name
	 */
	public String getName() {
		return name;
	}
}
