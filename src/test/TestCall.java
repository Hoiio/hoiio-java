package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hoiio.sdk.Hoiio;
import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.voice.Call;
import com.hoiio.sdk.objects.voice.CallHistory;

public class TestCall {
	
	private static final String NOTIFY_URL = "http://uat.hoiio.com/open/private/test/notify";

	public static void main(String[] args) {
		//Hoiio hoiio = new Hoiio("MzNFiA456mBiU0rj", "umjwI2VFgVeOyiwP"); // UAT
		Hoiio hoiio = new Hoiio("QNTR9akVnnwIrACA", "LGaVnYvYYSy3GoBt");
		
		try {
			/** Make Call **/
//			CallTxn callTxn = hoiio.getVoiceService().makeCall("+6581289447", "+6598169985");						
//			System.out.println(callTxn.getTxnRef());
//			System.out.println(callTxn.getContent());
//			
//			Thread.sleep(25000);
//			
//			CallHangUp callHangup = hoiio.getVoiceService().hangup(callTxn.getTxnRef());
//			
//			System.out.println(callHangup.getContent());
			
			/** Fetch Call **/
//			Call call = hoiio.getVoiceService().fetchCall("AA-C-3060515");
//			System.out.println(call.getContent());
//			System.out.println(call.getDest1());
//			System.out.println(call.getDest2());
//			System.out.println(call.getTag());
//			System.out.println(call.getCallStatusDest1().getStatus());
//			System.out.println(call.getCallStatusDest2().getStatus());
//			System.out.println(call.getDate());
//			System.out.println(call.getDuration());
//			System.out.println(call.getCurrency().getCurrency());
//			System.out.println(call.getDebit());
//			System.out.println(call.getRate());
			
			/** Get rate **/
//			CallRate callRate = hoiio.getVoiceService().fetchRate("+6581289447", "+6598169985");
//			System.out.println(callRate.getContent());
//			System.out.println(callRate.getCurrency().getCurrency());
//			System.out.println(callRate.getRate());
//			System.out.println(callRate.getTalkTime());
			
			/** Get history **/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CallHistory callHistory = hoiio.getVoiceService().fetchHistory(sdf.parse("2012-01-01 00:00:00"), new Date(), 1);
			System.out.println(callHistory.getContent());
			System.out.println(callHistory.getPageCount());
			System.out.println(callHistory.getTotalCount());
			List<Call> callList = callHistory.getCallList();
			
			for (Call call : callList) {
				System.out.println("==============");
				System.out.println(call.getContent());
				System.out.println(call.getDest1());
				System.out.println(call.getDest2());
				System.out.println(call.getTag());
				System.out.println(call.getCallStatusDest1().toString());
				System.out.println(call.getCallStatusDest2().toString());
				System.out.println(call.getDate());
				System.out.println(call.getDuration());
				System.out.println(call.getCurrency().toString());
				System.out.println(call.getDebit());
				System.out.println(call.getRate());
			}
			
			/** Call Conference **/
//			List<String> numbers = new ArrayList<String>();
//			numbers.add("+6581289447");
//			Conference conference = hoiio.getVoiceService().createConference("+6581289447");
//			
//			System.out.println(conference.getContent());
//			System.out.println(conference.getRoom());
//			System.out.println(conference.getTxnRefs());
			
		} catch (HoiioException e) {
			System.out.println(e.getStatus().toString());
			System.out.println(e.getContent());
		} catch (ParseException e) {
			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}
}
