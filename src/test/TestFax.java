package test;

import java.io.File;

import com.hoiio.sdk.Hoiio;
import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.fax.FaxRate;

public class TestFax {
	
	private static final String NOTIFY_URL = "http://uat.hoiio.com/open/private/test/notify";

	public static void main(String[] args) {
		//Hoiio hoiio = new Hoiio("MzNFiA456mBiU0rj", "umjwI2VFgVeOyiwP"); // UAT
		Hoiio hoiio = new Hoiio("QNTR9akVnnwIrACA", "LGaVnYvYYSy3GoBt");
		
		File file = new File("C:/Users/Max Tuan Duong/Desktop/sample_invoice.pdf");
		
		try {
			/** Send Fax **/
//			FaxTxn faxTxn = hoiio.getFaxService().send("+6566815644", file, "filename", null, null, NOTIFY_URL);
//			
//			System.out.println(faxTxn.getContent());
//			System.out.println(faxTxn.getTxnRef());
			
			/** Fetch Fax **/
//			Fax fax = hoiio.getFaxService().fetchFax("AA-C-3060997");
//			System.out.println(fax.getContent());
//			System.out.println(fax.getTag());
//			System.out.println(fax.getTxnRef());
//			System.out.println(fax.getSrc());
//			System.out.println(fax.getDest());
//			System.out.println(fax.getFaxStatus().getStatus());
//			System.out.println(fax.getFaxPages());
//			System.out.println(fax.getFaxUrl());
//			System.out.println(fax.getDebit());
//			System.out.println(fax.getRate());
//			System.out.println(fax.getCurrency());
//			System.out.println(fax.getDate());
			
			/** Get rate **/
			FaxRate faxRate = hoiio.getFaxService().fetchOutgoingRate("+6581289447");
			System.out.println(faxRate.getContent());
			System.out.println(faxRate.getCurrency().toString());
			System.out.println(faxRate.getRate());
			
			/** Get history **/
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			FaxHistory faxHistory = hoiio.getFaxService().fetchHistory(sdf.parse("2012-01-01 00:00:00"), new Date(), 1, FaxType.ALL);
//			System.out.println(faxHistory.getContent());
//			System.out.println(faxHistory.getPageCount());
//			System.out.println(faxHistory.getTotalCount());
//			List<Fax> faxList = faxHistory.getFaxList();
//			
//			for (Fax fax : faxList) {
//				System.out.println("==============");
//				System.out.println(fax.getContent());
//				System.out.println(fax.getTag());
//				System.out.println(fax.getTxnRef());
//				System.out.println(fax.getSrc());
//				System.out.println(fax.getDest());
//				System.out.println(fax.getFaxStatus().getStatus());
//				System.out.println(fax.getFaxPages());
//				System.out.println(fax.getFaxUrl());
//				System.out.println(fax.getDebit());
//				System.out.println(fax.getRate());
//				System.out.println(fax.getCurrency());
//				System.out.println(fax.getDate());
//			}
			
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
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}
}
