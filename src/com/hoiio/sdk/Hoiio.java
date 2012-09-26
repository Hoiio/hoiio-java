package com.hoiio.sdk;

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

import com.hoiio.sdk.services.AccountService;
import com.hoiio.sdk.services.FaxService;
import com.hoiio.sdk.services.IvrService;
import com.hoiio.sdk.services.NumberService;
import com.hoiio.sdk.services.SmsService;
import com.hoiio.sdk.services.VoiceService;

public class Hoiio {
	
	private VoiceService voiceService;
	private SmsService smsService;
	private IvrService ivrService;
	private NumberService numberService;
	private FaxService faxService;
	private AccountService accountService;

	/**
	 * Initiates Hoiio Service
	 * @param appId AppID of the developer
	 * @param accessToken AccessToken of the developer
	 */
	public Hoiio(String appId, String accessToken) {
		voiceService = new VoiceService(appId, accessToken);
		smsService = new SmsService(appId, accessToken);
		ivrService = new IvrService(appId, accessToken);
		numberService = new NumberService(appId, accessToken);
		faxService = new FaxService(appId, accessToken);
		accountService = new AccountService(appId, accessToken);
	}

	/**
	 * Gets the VoiceService to make all the Voice APIs
	 * @return {@code VoiceService} object
	 */
	public VoiceService getVoiceService() {
		return voiceService;
	}

	/**
	 * Gets the SmsService to make all the SMS APIs
	 * @return {@code SmsService} object
	 */
	public SmsService getSmsService() {
		return smsService;
	}

	/**
	 * Gets the IvrService to make all the IVR APIs
	 * @return {@code IvrService} object
	 */
	public IvrService getIvrService() {
		return ivrService;
	}

	/**
	 * Get the NumberService to make all the Number APIs
	 * @return {@code NumberService} object
	 */
	public NumberService getNumberService() {
		return numberService;
	}

	/**
	 * Gets the FaxService to make all the Fax APIs
	 * @return {@code FaxService} object
	 */
	public FaxService getFaxService() {
		return faxService;
	}

	/**
	 * Gets the AccountService to make all the Account APIs
	 * @return {@code AccountService} object
	 */
	public AccountService getAccountService() {
		return accountService;
	}
}
