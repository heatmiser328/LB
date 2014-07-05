package ica.LB.Core;

/**
 * Created by jcapuano on 5/16/2014.
 */
public class LeaderLoss {

	public static LeaderLossResult resolve(int dice, int lossdie, int durationdie1, int durationdie2, boolean melee) {
	    boolean loss = melee ? (dice <= 12 || dice >= 64) : (dice >= 64);
	    LeaderLossResult result = new LeaderLossResult();
	
	    if (loss) {
	        if (melee) {
                result.setAttacker(dice <= 12);
                result.setDefender(!result.getAttacker());
	        }
            else {
                result.setDefender(true);
            }
	        if (lossdie == 1) {
                result.setKilled(true);
                result.setInjury("Head");
	        }
	        else if (lossdie == 2) {
                result.setKilled(true);
                result.setInjury("Chest");
	        }
	        else if (lossdie == 3) {
                result.setWounded(true);
                result.setInjury("Leg");
                result.setDuration(durationdie1 + durationdie2);
	        }
	        else if (lossdie == 4) {
                result.setWounded(true);
                result.setInjury("Arm");
                result.setDuration(durationdie1);
	        }
	        else if (lossdie == 5) {
                result.setCaptured(true);
                result.setInjury("Capture");
	        }
	        else {//if (lossdie == 6) {
                result.setInjury("Flesh Wound");
	        }
	    }
	    return result;
	}
}
