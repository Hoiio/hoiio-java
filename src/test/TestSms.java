package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hoiio.sdk.Hoiio;
import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.sms.BulkSmsTxn;
import com.hoiio.sdk.objects.sms.Sms;
import com.hoiio.sdk.objects.sms.SmsHistory;

public class TestSms {
	
	private static final String NOTIFY_URL = "http://uat.hoiio.com/open/private/test/notify";

	public static void main(String[] args) {
		//Hoiio hoiio = new Hoiio("MzNFiA456mBiU0rj", "umjwI2VFgVeOyiwP"); // UAT
		Hoiio hoiio = new Hoiio("QNTR9akVnnwIrACA", "LGaVnYvYYSy3GoBt");
		
		try {
			/** Send SMS **/
//			SmsTxn smsTxn = hoiio.getSmsService().send("+6581289447", "test SDK", "maxtest", "test sms", NOTIFY_URL);						
//			System.out.println(smsTxn.getTxnRef());
//			System.out.println(smsTxn.getContent());
			
			/** Fetch SMS **/
//			Sms sms = hoiio.getSmsService().fetchSms("AA-S-578530");
//			System.out.println(sms.getContent());
//			System.out.println(sms.getDest());
//			System.out.println(sms.getSmsStatus().getStatus());
//			System.out.println(sms.getTag());
//			System.out.println(sms.getTxnRef());
//			System.out.println(sms.getDebit());
//			System.out.println(sms.getDate());
//			System.out.println(sms.getRate());
//			System.out.println(sms.getSplitCount());
//			System.out.println(sms.getCurrency().getCurrency());
			
			/** Get rate **/
//			SmsRate smsRate = hoiio.getSmsService().fetchRate("+6581289447", "test");
//			System.out.println(smsRate.getContent());
//			System.out.println(smsRate.getCurrency().getCurrency());
//			System.out.println(smsRate.getRate());
//			System.out.println(smsRate.getSplitCount());
//			System.out.println(smsRate.getTotalCost());
			
			/** Get history **/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SmsHistory smsHistory = hoiio.getSmsService().fetchHistory(sdf.parse("2012-01-01 00:00:00"), new Date(), 1);
			System.out.println(smsHistory.getContent());
			System.out.println(smsHistory.getPageCount());
			System.out.println(smsHistory.getTotalCount());
			List<Sms> smsList = smsHistory.getSmsList();
			
			for (Sms sms: smsList) {
				System.out.println("==============");
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
			
			/** Send bulk sms **/
//			BulkSmsTxn bulkSmsTxn = hoiio.getSmsService().sendBulk("+6581289447", "test bulk sdk", "maxtest", "test sms bulk", NOTIFY_URL);
//			System.out.println(bulkSmsTxn.getContent());
//			System.out.println(bulkSmsTxn.getBulkTxnRef());
		} catch (HoiioException e) {
			System.out.println(e.getStatus().toString());
			System.out.println(e.getContent());
		} catch (ParseException e) {
			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
		}
		
	}
}
