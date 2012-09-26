package com.hoiio.sdk.objects.ivr;

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

import net.sf.json.JSONObject;

import com.hoiio.sdk.objects.HoiioResponse;

public class Transfer extends HoiioResponse {
	
	private static enum Params {
		ROOM;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private String room;
	
	/**
	 * Constructs a new {@code Transfer} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public Transfer(JSONObject output) {
		response = output.toString();
		
		if (output.containsKey(Params.ROOM.toString())) {
			room = output.getString(Params.ROOM.toString());
		}
	}

	/**
	 * Gets the room ID when transferring the call to a conference room
	 * @return A text string representing the conference room ID when transferring the call to a conference room. If the room ID was omitted in the original request, this will be automatically generated for you. This parameter will not be available when transferring the call to a E.164 number.
	 */
	public String getRoom() {
		return room;
	}
	
}
