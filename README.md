# hoiio-java
Hoiio-java is a Java SDK for Hoiio's API. It encapsulates the REST 
communications and let developers use the API via a few simple classes.

Currently, hoiio-java supports the Call, SMS, Fax, Account, Hoiio Number and IVR APIs.


# Installing
Place the jar file inside the ```lib``` folder to your library folder. 
You'll need to include the dependencies yourself (the list of dependency libraries 
can be found inside the ```third-party``` folder)


# Usage
Here are some examples you can use the SDK to access all Hoiio's API (via Hoiio class)

``` java
	// Create hoiio service
	Hoiio hoiio = new Hoiio("[APP_ID]", "[ACCESS_TOKEN]");

	try {
		// Make a call
		CallTxn callTxn = hoiio.getVoiceService().makeCall(dest1, dest2);	
		
		// Get the response from Hoiio
		System.out.println(callTxn.getTxnRef());

		// Send an sms
		SmsTxn smsTxn = hoiio.getSmsService().send(dest, msg);		
		
		// Get the response from Hoiio
		System.out.println(smsTxn.getTxnRef());

		// Get sms history
		SmsHistory smsHistory = hoiio.getSmsService().fetchHistory();
		for (Sms sms : smsHistory.getSmsList()) {
			System.out.println(sms.getContent());
			System.out.println(sms.getDest());
			System.out.println(sms.getSmsStatus().toString());
			System.out.println(sms.getTag());
			System.out.println(sms.getTxnRef());
			System.out.println(sms.getDebit());
			System.out.println(sms.getDate());
			System.out.println(sms.getRate());
			System.out.println(sms.getSplitCount());
			System.out.println(sms.getCurrency().toString());
		}
	} catch (HoiioException e) {
		// This is thrown when the request doesn't return success_ok
		System.out.println(e.getStatus().toString());
		System.out.println(e.getContent());
	}
```

# License
This project is under MIT License (http://en.wikipedia.org/wiki/MIT_License).
See LICENSE file for details.


# Contacts
If you have any questions, please feel free to contact us:

* Twitter:        @hoiiotweets
* Google Groups:  https://groups.google.com/forum/#!forum/hoiio-developers
* Facebook:       http://www.facebook.com/Hoiio
* Blog:           http://devblog.hoiio.com/
