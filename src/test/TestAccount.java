package test;

import com.hoiio.sdk.Hoiio;
import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.account.Account;
import com.hoiio.sdk.objects.account.Balance;
import com.hoiio.sdk.util.DateUtil;

public class TestAccount {

	public static void main(String[] args) {
		Hoiio hoiio = new Hoiio("MzNFiA456mBiU0rj", "umjwI2VFgVeOyiwP"); // UAT
		//Hoiio hoiio = new Hoiio("QNTR9akVnnwIrACA", "LGaVnYvYYSy3GoBt");
		
		try {
			/** Account **/
			Account account = hoiio.getAccountService().fetchAccount();
			
			System.out.println(account.getContent());
			System.out.println(account.getCountry());
			System.out.println(account.getUid());
			System.out.println(account.getMobileNumber());
			System.out.println(account.getEmail());
			System.out.println(account.getPrefix());
			System.out.println(account.getCurrency().toString());
			
			/** balance **/
			Balance balance = hoiio.getAccountService().fetchBalance();
			System.out.println(balance.getContent());
			System.out.println(balance.getBalance());
			System.out.println(balance.getBonus());
			System.out.println(balance.getPoints());
			System.out.println(balance.getCurrency().toString());
			
		} catch (HoiioException e) {
			System.out.println(e.getStatus().toString());
			System.out.println(e.getContent());
		}
		
	}
}
