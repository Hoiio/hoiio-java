package test;

import java.util.List;

import com.hoiio.sdk.Hoiio;
import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.enums.NumberCapability;
import com.hoiio.sdk.objects.enums.NumberDuration;
import com.hoiio.sdk.objects.number.AvailableNumber;
import com.hoiio.sdk.objects.number.Country;
import com.hoiio.sdk.objects.number.State;
import com.hoiio.sdk.objects.number.Subscribe;
import com.hoiio.sdk.objects.number.SupportedCountry;

public class TestNumber {
	
	public static void main(String[] args) {
		Hoiio hoiio = new Hoiio("MzNFiA456mBiU0rj", "umjwI2VFgVeOyiwP");
		
		try {
			/** Supported Countries **/
			SupportedCountry supportedCountry = hoiio.getNumberService().fetchSupportedCountry();
			System.out.println(supportedCountry.getContent());
			
			System.out.println(supportedCountry.getCount());
			
			List<Country> countryList = supportedCountry.getCountryList();
			
			for (Country country : countryList) {
				System.out.println(country.getCode());
				System.out.println(country.getName());
				System.out.println(country.getPrefix());
				List<State> stateList = country.getStateList();
				
				for (State state : stateList) {
					System.out.println(state.getCode());
					System.out.println(state.getName());
					for (NumberCapability n : state.getCapability()) {
						System.out.println(n.toString());
					}
				}
			}
			
			/** Get choices **/
//			AvailableNumber availableNumber = hoiio.getNumberService().fetchAvailableNumbers("SG");
//			System.out.println(availableNumber.getContent());
//			System.out.println(availableNumber.getPageCount());
//			System.out.println(availableNumber.getTotalCount());
//			
//			List<String> numberList = availableNumber.getNumberList();
//			for (String number : numberList) {
//				System.out.println(number);
//			}
			
			/** Get rate **/
//			NumberRate numberRate = hoiio.getNumberService().fetchRate("SG");
//			System.out.println(numberRate.getContent());
//			System.out.println(numberRate.getCount());
//			System.out.println(numberRate.getCurrency().getCurrency());
//			
//			List<Rate> rateList = numberRate.getRateList();
//			
//			for (Rate rate : rateList) {
//				System.out.println(rate.getDuration());
//				System.out.println(rate.getRate());
//			}
			
			/** Get active **/
//			ActiveNumber activeNumber = hoiio.getNumberService().fetchActiveNumbers();
//			System.out.println(activeNumber.getContent());
//			System.out.println(activeNumber.getPageCount());
//			
//			List<Number> numberList = activeNumber.getNumberList();
//			for (Number number : numberList) {
//				System.out.println(number.getCountry());
//				System.out.println(number.getForwardTo());
//				System.out.println(number.getNumber());
//				System.out.println(number.getState());
//				System.out.println(number.getExpiry());
//				System.out.println(number.getAutoExtendStatus().getStatus());
//			}
			
			/** Update forwarding **/
//			UpdateForwarding updateForwarding = hoiio.getNumberService().updateForwarding("+6566028085", "http://uat.hoiio.com/", null, VoiceMode.VOICE);
//			System.out.println(updateForwarding.getContent());
			
			/** subscribe **/
//			Subscribe subscribe = hoiio.getNumberService().subscribe("+6566318509", NumberDuration.ONE);
//			System.out.println(subscribe.getContent());
//			System.out.println(subscribe.getDebit());
//			System.out.println(subscribe.getCurrency());
//			System.out.println(subscribe.getExpiry());
			
		} catch (HoiioException e) {
			e.printStackTrace();
			System.out.println(e.getStatus().toString());
		}
		
	}
}
