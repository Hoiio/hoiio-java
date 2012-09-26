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

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioRequest;
import com.hoiio.sdk.objects.enums.NumberDuration;
import com.hoiio.sdk.objects.enums.VoiceMode;
import com.hoiio.sdk.objects.number.ActiveNumber;
import com.hoiio.sdk.objects.number.AvailableNumber;
import com.hoiio.sdk.objects.number.NumberRate;
import com.hoiio.sdk.objects.number.Subscribe;
import com.hoiio.sdk.objects.number.SupportedCountry;
import com.hoiio.sdk.objects.number.UpdateForwarding;

/**
 * The Number API will allow developers to have full control over how they assign their Hoiio Numbers to their IVR applications.<br/>
 * <br/>
 * For more info please refer to: <a href="http://developer.hoiio.com/docs/number.html">http://developer.hoiio.com/docs/number.html</a>
 */
public class NumberService extends HttpService {
	
	private static enum Params {
		COUNTRY, STATE, PAGE, NUMBER, DURATION, FORWARD_TO, FORWARD_TO_SMS, MODE;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}

	private static final String URL_NUMBER_GET_COUNTRIES = "number/get_countries";
	private static final String URL_NUMBER_GET_CHOICES = "number/get_choices";
	private static final String URL_NUMBER_GET_RATES = "number/get_rates";
	private static final String URL_NUMBER_SUBSCRIBE = "number/subscribe";
	private static final String URL_NUMBER_UPDATE_FORWARDING = "number/update_forwarding";
	private static final String URL_NUMBER_GET_ACTIVE = "number/get_active";
	
	/**
	 * Constructs the service to make all Number requests
	 * @param appId AppID of the developer
	 * @param accessToken AccessToken of the developer
	 */
	public NumberService(String appId, String accessToken) {
		this.appId = appId;
		this.accessToken =  accessToken;
	}
	
	/**
	 * Retrieves the list of Hoiio Numbers that is assigned to your application and their current configuration.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public ActiveNumber fetchActiveNumbers() throws HoiioException {				
		return new ActiveNumber(doHttpPost(URL_NUMBER_GET_ACTIVE, new HoiioRequest()));
	}
	
	/**
	 * Returns a list of available Hoiio Numbers for subscription in a given country
	 * @param country Select the country that you wish to browse the list of available Hoiio Numbers. Use country code in ISO 3166-1 alpha-2 format.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public AvailableNumber fetchAvailableNumbers(String country) throws HoiioException {
		return fetchAvailableNumbers(country, null, null);
	}
	
	/**
	 * Returns a list of available Hoiio Numbers for subscription in a given country
	 * @param country Select the country that you wish to browse the list of available Hoiio Numbers. Use country code in ISO 3166-1 alpha-2 format.
	 * @param state (required for USA) Select the state within the country to browse the list of available Hoiio Numbers. Use the two letter state code defined in ISO 3166-2.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public AvailableNumber fetchAvailableNumbers(String country, 
			String state) throws HoiioException {
		return fetchAvailableNumbers(country, state, null);
	}
	
	/**
	 * Returns a list of available Hoiio Numbers for subscription in a given country
	 * @param country Select the country that you wish to browse the list of available Hoiio Numbers. Use country code in ISO 3166-1 alpha-2 format.
	 * @param state (required for USA) Select the state within the country to browse the list of available Hoiio Numbers. Use the two letter state code defined in ISO 3166-2.
	 * @param page (optional) Each request returns a maximum of 20 entries. This parameter indicates which subset of entries to return.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public AvailableNumber fetchAvailableNumbers(String country, 
			String state, Integer page) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.COUNTRY.toString(), country, true);
		if (country.equals("US")) {
			map.put(Params.STATE.toString(), state, true);
		} else {
			map.put(Params.STATE.toString(), state, false);
		}
		map.put(Params.PAGE.toString(), page, false);
		
		return new AvailableNumber(doHttpPost(URL_NUMBER_GET_CHOICES, map));
	}
	
	/**
	 * Retrieves the billable rate that will be charged for subscribing to a Hoiio Number
	 * @param country Select the country that you wish to check the rates. Use country code in ISO 3166-1 alpha-2 format.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public NumberRate fetchRate(String country) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.COUNTRY.toString(), country, true);
		
		return new NumberRate(doHttpPost(URL_NUMBER_GET_RATES, map));
	}
	
	/**
	 * Retrieves a list of countries for which there are Hoiio Numbers available for subscription.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SupportedCountry fetchSupportedCountry() throws HoiioException {
		return new SupportedCountry(doHttpPost(URL_NUMBER_GET_COUNTRIES, new HoiioRequest()));
	}
	
	/**
	 * Subscribes for a new Hoiio Number or extend an existing subscription of a Hoiio Number
	 * @param number The Hoiio Number that you want to subscribe or extend subscription. Numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param duration The number of months that you wished to subscribe or extend subscription for the specified Hoiio Number: 1,3,12, auto_extend
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Subscribe subscribe(String number, NumberDuration duration) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.NUMBER.toString(), number, true);
		map.put(Params.DURATION.toString(), duration.toString(), true);
		
		return new Subscribe(doHttpPost(URL_NUMBER_SUBSCRIBE, map));
	}
	
	/**
	 * Configures the URL that the incoming call notification and incoming SMS notification for your Hoiio Number will be sent to
	 * @param number The Hoiio Number to configure. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public UpdateForwarding updateForwarding(String number) throws HoiioException {
		return updateForwarding(number, null, null, null);
	}
	
	/**
	 * Configures the URL that the incoming call notification and incoming SMS notification for your Hoiio Number will be sent to
	 * @param number The Hoiio Number to configure. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param forwardTo (optional) A fully-qualified HTTP/S URL on your web server to be notified when there is an incoming voice call/fax
	 * @param forwardToSms (optional) A fully-qualified HTTP/S URL on your web server to be notified when there is an incoming SMS
	 * @param mode (optional) Indicate whether to use the number for incoming voice call or fax
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public UpdateForwarding updateForwarding(String number, String forwardTo,
			String forwardToSms, VoiceMode mode) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.NUMBER.toString(), number, true);
		map.put(Params.FORWARD_TO.toString(), forwardTo, false);
		map.put(Params.FORWARD_TO_SMS.toString(), forwardToSms, false);
		map.put(Params.MODE.toString(), mode.toString(), false);
		
		return new UpdateForwarding(doHttpPost(URL_NUMBER_UPDATE_FORWARDING, map));
	}
}
