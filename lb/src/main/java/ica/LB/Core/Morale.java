package ica.LB.Core;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class Morale {

	public static String resolve(int morale, int dice) {
		String result = "NE";
		if (dice > morale)
			result = "Pass";
		else
			result = "Fail";
		return result;
	}

}
