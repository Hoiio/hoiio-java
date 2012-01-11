# hoiio-java
Hoiio-java is a Java SDK for Hoiio's Voice and SMS API. It encapsulates the REST 
communications and let developers use the API via a few simple classes.

Currently, hoiio-php supports the Call, SMS, Account, Hoiio Number and IVR APIs.


# Installing 
Place the jar file inside the lib folder to your library folder. 
You'll need to include the dependencies yourself (the list of dependency libraries 
can be found inside the thrid-party folder)


# Usage
Here are some examples you can use the SDK to access all Hoiio's API (via HoiioService class)

``` java
	// Create hoiio service
	HoiioService hoiioService = new HoiioService("[APP_ID]", "[ACCESS_TOKEN]");

	// Make a call
	try {
		hoiioService.call(dest1, dest2, callerID, tag, notifyURL);
	} catch (HoiioException e) {
		return e.getException();
	}

	// Send an sms
	try {
		hoiioService.smsSend(dest, senderName, msg, tag, notifyUrl);
	} catch (HoiioException e) {
		return e.getException();
	}

	// Make an outgoing IVR
	try {
		ivrDial(msg, dest, callerID, tag, notifyUrl);
	} catch (HoiioException e) {
		return e.getException();
	}
```


# Examples
Most of the examples are written using Google App Engine. (you can read the details 
on how to setup here http://code.google.com/appengine/)

Import the examples into Eclipse (http://www.eclipse.org/), replace all the "[something]"
with your desired value: e.g.: [APP_ID] is the application ID you get when you create a app

You can run locally by using Eclipse or upload it to App Engine 
(http://code.google.com/appengine/docs/java/gettingstarted/uploading.html)


# License
This project is under MIT License (http://en.wikipedia.org/wiki/MIT_License).
See LICENSE file for details.


# Contacts
If you have any questions, please feel free to contact us:

Twitter:        @hoiiotweets
Google Groups:  https://groups.google.com/forum/#!forum/hoiio-developers
Facebook:       http://www.facebook.com/Hoiio
Blog:           http://devblog.hoiio.com/
