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
import com.hoiio.sdk.objects.account.Account;
import com.hoiio.sdk.objects.account.Balance;

/**
 * The Account API will allow developers to check their credit balances and other account information.<br/>
 * <br/>
 * For more info please refer to: <a href="http://developer.hoiio.com/docs/account.html">http://developer.hoiio.com/docs/account.html</a>
 */
public class AccountService extends HttpService {

	private static final String URL_ACCOUNT_INFO = "user/get_info";
	private static final String URL_ACCOUNT_BALANCE = "user/get_balance";
	
	/**
	 * Constructs the service to make all Account requests
	 * @param appId AppID of the developer
	 * @param accessToken AccessToken of the developer
	 */
	public AccountService(String appId, String accessToken) {
		this.appId = appId;
		this.accessToken = accessToken;
	}
	
	/**
	 * Retrieves the general information of your account.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Account fetchAccount() throws HoiioException {
		return new Account(doHttpPost(URL_ACCOUNT_INFO, new HoiioRequest()));
	}
	
	/**
	 * Retrieves the current credit balance of your account
	 * @return Object containing all the responses from the server 
	 * @throws HoiioException
	 */
	public Balance fetchBalance() throws HoiioException {
		return new Balance(doHttpPost(URL_ACCOUNT_BALANCE, new HoiioRequest()));
	}
}
