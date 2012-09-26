package test;

import com.hoiio.sdk.Hoiio;
import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.enums.TransferOnFailture;
import com.hoiio.sdk.objects.ivr.Dial;
import com.hoiio.sdk.objects.ivr.Gather;
import com.hoiio.sdk.objects.ivr.Hangup;
import com.hoiio.sdk.objects.ivr.Monitor;
import com.hoiio.sdk.objects.ivr.Play;
import com.hoiio.sdk.objects.ivr.Record;
import com.hoiio.sdk.objects.ivr.Transfer;

public class TestIvr {
	
	private static final String NOTIFY_URL = "http://uat.hoiio.com/open/private/test/notify";

	public static void main(String[] args) {
		//Hoiio hoiio = new Hoiio("MzNFiA456mBiU0rj", "umjwI2VFgVeOyiwP"); // UAT
		Hoiio hoiio = new Hoiio("QNTR9akVnnwIrACA", "LGaVnYvYYSy3GoBt");
		
		try {
			/** Dial **/
			Dial dial = hoiio.getIvrService().dial("+6581289447", "welcome to hoiio", null, null, NOTIFY_URL);
			System.out.println(dial.getContent());
			System.out.println(dial.getSession());
			System.out.println(dial.getTxnRef());
			
			String session = "S10279";
			
//			/** Play **/
//			Play play = hoiio.getIvrService().play(session, "play message", "play", NOTIFY_URL);
//			System.out.println(play.getContent());
//			
//			/** Gather **/
//			Gather gather = hoiio.getIvrService().gather(session, NOTIFY_URL, "press 3 digits", 3, 30, 2, "gather");
//			System.out.println(gather.getContent());
//			
//			/** Record **/
//			Record record = hoiio.getIvrService().record(session, "recoding", 10, "record", NOTIFY_URL);
//			System.out.println(record.getContent());
//			
//			/** Monitor **/
//			Monitor monitor = hoiio.getIvrService().monitor(session, "monitoring", "monitor", NOTIFY_URL);
//			System.out.println(monitor.getContent());
//			
//			/** Transfer **/
			Transfer transfer = hoiio.getIvrService().transfer(session, "+6598169985", "transferring", null, 
					TransferOnFailture.HANGUP, "transfer", NOTIFY_URL);
			System.out.println(transfer.getContent());
			System.out.println(transfer.getRoom());
//			
//			/** Transfer with continue **/
//			Transfer transfer = hoiio.getIvrService().transfer(session, "+6598169985", "transferring", null, 
//					TransferOnFailture.CONTINUE, "transfer", NOTIFY_URL);
//			System.out.println(transfer.getContent());
//			System.out.println(transfer.getRoom());
//			
//			/** Hangup **/
//			Hangup hangup = hoiio.getIvrService().hangup(session, "bye bye", "hangup", NOTIFY_URL);
//			System.out.println(hangup.getContent());
			
		} catch (HoiioException e) {
			System.out.println(e.getStatus().toString());
			System.out.println(e.getContent());
//		} catch (ParseException e) {
//			e.printStackTrace();
		}
		
	}
}
