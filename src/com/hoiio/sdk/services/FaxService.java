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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.exception.InternalServerErrorException;
import com.hoiio.sdk.objects.HoiioRequest;
import com.hoiio.sdk.objects.enums.FaxType;
import com.hoiio.sdk.objects.fax.Fax;
import com.hoiio.sdk.objects.fax.FaxHistory;
import com.hoiio.sdk.objects.fax.FaxRate;
import com.hoiio.sdk.objects.fax.FaxTxn;
import com.hoiio.sdk.util.DateUtil;

/**
 * Fax API provides developers access to faxing capabilities by using our simple API. Developers can use the Fax API to send, and also to receive fax via Hoiio virtual numbers.<br/>
 * <br/>
 * For more info please refer to: <a href="http://developer.hoiio.com/docs/fax.html">http://developer.hoiio.com/docs/fax.html</a>
 */
public class FaxService extends HttpService {
	
	private static enum Params {
		DEST, FILE, FILENAME, CALLER_ID, TAG, NOTIFY_URL, 
		FROM, TO, PAGE, TXN_REF, INCOMING, TYPE;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private static final String URL_FAX_SEND = "fax/send";
	private static final String URL_FAX_GET_HISTORY = "fax/get_history";
	private static final String URL_FAX_GET_RATE = "fax/get_rate";
	private static final String URL_FAX_QUERY_STATUS = "fax/query_status";
	
	/**
	 * Constructs the service to make all Fax requests
	 * @param appId AppID of the developer
	 * @param accessToken AccessToken of the developer
	 */
	public FaxService(String appId, String accessToken) {
		this.appId = appId;
		this.accessToken = accessToken;
	}
	
	/**
	 * Querys the current status of a fax made previously
	 * @param txnRef The unique reference ID for the required transaction.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Fax fetchFax(String txnRef) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.TXN_REF.toString(), txnRef, true);
		
		return new Fax(doHttpPost(URL_FAX_QUERY_STATUS, map));
	}
	
	/**
	 * Retrieves the history of faxes sent by this application.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public FaxHistory fetchHistory() throws HoiioException {
		return fetchHistory(null, null, null, null);
	}
	
	/**
	 * Retrieves the history of faxes sent by this application.
	 * @param from (optional) Retrieve fax history made by this app starting from this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, fax history will be retrieved from the earliest transaction.
	 * @param to (optional) Retrieve fax history made by this app before this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, fax history will be retrieved up to the current point of time.
	 * @param page (optional) Each request returns a maximum of 100 entries. This parameter indicates which subset of entries to return.
	 * @param type (optional) The type of transaction you want to retrieve: outgoing, incoming, all
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public FaxHistory fetchHistory(Date from, Date to, Integer page, FaxType type) throws HoiioException {
		
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.FROM.toString(), DateUtil.dateToString(from), false);
		map.put(Params.TO.toString(), DateUtil.dateToString(to), false);
		map.put(Params.PAGE.toString(), page.toString(), false);
		map.put(Params.TYPE.toString(), type.toString(), false);
		
		return new FaxHistory(doHttpPost(URL_FAX_GET_HISTORY, map));
	}
	
	/**
	 * Retrieves the billable rate that will be charged for fax received	
	 * @param incoming Your Hoiio number to receive incoming fax. 
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public FaxRate fetchIncomingRate(String incoming) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.INCOMING.toString(), incoming, true);
		
		return new FaxRate(doHttpPost(URL_FAX_GET_RATE, map));
	}
	
	/**
	 * Retrieves the billable rate that will be charged for fax sent 
	 * @param dest The recipient number of the fax. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111. 
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public FaxRate fetchOutgoingRate(String dest) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST.toString(), dest, true);
		
		return new FaxRate(doHttpPost(URL_FAX_GET_RATE, map));
	}
	
	/**
	 * Sends a fax file to a fax number.
	 * @param dest The destination to send the fax to. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param file The file to be faxed. The file must be in PDF format with a page size of either A4 or Letter and its file size can be at most 2MB.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public FaxTxn send(String dest, File file) throws HoiioException {
		return send(dest, file, null, null, null, null);
	}
	
	/**
	 * Sends a fax file to a fax number.
	 * @param dest The destination to send the fax to. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param file The file to be faxed. The file must be in PDF format with a page size of either A4 or Letter and its file size can be at most 2MB.
	 * @param filename (optional) The name of the file that is downloaded from the fax_url given in FetchFax and FetchHistory.
	 * @param callerId (optional) This is the Caller ID that the receiver will see on their incoming fax
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification, FetchFax and FetchHistory for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S callback URL on your web server to be notified when the fax finishes
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public FaxTxn send(String dest, File file, String filename, String callerId,  
			String tag, String notifyUrl) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST.toString(), dest, true);
		map.put(Params.FILE.toString(), DatatypeConverter.printBase64Binary(convertFileToByteArray(file)), true);
		map.put(Params.FILENAME.toString(), filename, false);
		map.put(Params.CALLER_ID.toString(), callerId, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		
		return new FaxTxn(doHttpPost(URL_FAX_SEND, map));
	}
	
	private byte[] convertFileToByteArray(File file) {
		try {
			InputStream is = new FileInputStream(file);
	
	        byte[] bytes = new byte[(int)file.length()];
	
	        // Read in the bytes
	        int offset = 0;
	        int numRead = 0;
	        while ( (offset < bytes.length) &&
	                ( (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) ) {
	            offset += numRead;
	        }
	
	        is.close();
	        return bytes;
		} catch (IOException e) {
			throw new InternalServerErrorException(e);
		}
	}
}
