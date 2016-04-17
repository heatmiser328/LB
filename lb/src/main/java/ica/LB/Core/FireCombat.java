package ica.LB.Core;

import java.util.*;

/**
 * Created by jcapuano on 5/16/2014.
 */
public class FireCombat {
    private ArrayList<Odds> oddsTable;

	public FireCombat () {
		oddsTable = new ArrayList<Odds>();
		oddsTable.add(new Odds(-3, "1:3"));
		oddsTable.add(new Odds(-2.5, "1:2.5"));
		oddsTable.add(new Odds(-2, "1:2"));
		oddsTable.add(new Odds(-1.5, "1:1.5"));
		oddsTable.add(new Odds(1, "1:1"));
		oddsTable.add(new Odds(1.5, "1.5:1"));
		oddsTable.add(new Odds(2, "2:1"));
		oddsTable.add(new Odds(2.5, "2.5:1"));
		oddsTable.add(new Odds(3, "3:1"));
		oddsTable.add(new Odds(4, "4:1"));
		oddsTable.add(new Odds(5, "5:1"));
		oddsTable.add(new Odds(6, "6:1"));
		oddsTable.add(new Odds(7, "7:1"));
		oddsTable.add(new Odds(8, "8:1"));
		oddsTable.add(new Odds(9, "9:1"));
		oddsTable.add(new Odds(10, "10:1"));
	}
	
	public Odds getDefaultOdds() {
        return oddsTable.get(4);
	}
	
	public String[] getOddsList() {
		ArrayList<String> odds = new ArrayList<String>();
		for (Odds o : oddsTable)
			odds.add(o.getName());
        String[] l = new String[odds.size()];
		odds.toArray(l);
        return l;
	}
	
	public int getOddsIndex(String name) {
		for (int i=0; i<oddsTable.size(); i++) {
			if (oddsTable.get(i).getName() == name)
				return i;
		}
		return 4;
	}
	
	public Odds getOddsItem(int idx) {
		if (idx >= 0 && idx < oddsTable.size())
			return oddsTable.get(idx);
		return getDefaultOdds();
	}
	
	public Odds calculate(double att, double def, boolean cannister)  {
        return Odds.calculate(oddsTable, att, def, (cannister ? 1 : 0));
    }
	
	public String resolve(Odds odds, int defincr, int dice) {
	    if (defincr > 9) {
	        Base6Value b6i = new Base6Value(dice);
	        dice = b6i.add(defincr - 9);
	    }
	    
	    String result = "NE";
		if (odds.getValue() == -3) {	//1-3
			if (dice >= 65) {
				result = "1";
	        }
		}
		else if (odds.getValue() == -2.5) {	//1-2.5
			if (dice >= 64) {
				result = "1";
            }
		}
		else if (odds.getValue() == -2) {	//1-2
			if (dice >= 62) {
				result = "1";
            }
		}
		else if (odds.getValue() == -1.5) {	//1-1.5
			if (dice >= 55) {
				result = "1";
            }
		}
		else if (odds.getValue() == 1) {	//1-1
			if (dice >= 51) {
				result = "1";
            }
		}
		else if (odds.getValue() == 1.5) {	//1.5-1
			if (dice >= 42) {
				result = "1";
            }
		}
		else if (odds.getValue() == 2) {	//2-1
			if (dice >= 33) {
				result = "1";
            }
		}
		else if (odds.getValue() == 2.5) {	//2.5-1
			if (dice >= 64) {
				result = "2";
            }
			else if (dice >= 26) {
				result = "1";
            }
		}
		else if (odds.getValue() == 3) {	//3-1
			if 	(dice >= 56) {
				result = "2";
            }
			else if (dice >= 22) {
				result = "1";
            }
		}
		else if (odds.getValue() == 4) {	//4-1
			if (dice >= 54) {
				result = "2";
            }
			else if (dice >= 13) {
				result = "1";
            }
		}
		else if (odds.getValue() == 5) {	//5-1
			if (dice >= 66) {
				result = "3";
            }
			else if (dice >= 45) {
				result = "2";
            }
			else if (dice >= 11) {
				result = "1";
            }
		}
		else if (odds.getValue() == 6) {	//6-1
			if (dice >= 62) {
				result = "3";
            }
			else if (dice >= 33) {
				result = "2";
            }
			else if (dice >= 11) {
				result = "1";
            }
		}
		else if (odds.getValue() == 7) {	//7-1
			if (dice >= 52) {
				result = "3";
            }
			else if (dice >= 23) {
				result = "2";
            }
			else if (dice >= 11) {
				result = "1";
            }
		}
		else if (odds.getValue() == 8) {	//8-1
			if (dice >= 66) {
				result = "4";
            }
			else if (dice >= 45) {
				result = "3";
            }
			else if (dice >= 15) {
				result = "2";
            }
			else if (dice >= 11) {
				result = "1";
            }
		}
		else if (odds.getValue() == 9) {	//9-1
			if (dice >= 63) {
				result = "4";
            }
			else if (dice >= 42) {
				result = "3";
            }
			else if (dice >= 11) {
				result = "2";
            }
		}
		else if (odds.getValue() == 10) {	//10-1
			if (dice >= 65) {
				result = "5";
            }
			else if (dice >= 55) {
				result = "4";
            }
			else if (dice >= 26) {
				result = "3";
            }
			else if (dice >= 11) {
				result = "2";
            }
		}
		else {
			result = "NE";
		}
		
	    /*
		if (dice >= 65) {
	        result += " &";
		}            
		*/
	    
	    return result;
	}
	
	public LeaderLossResult resolveLeaderLoss(int dice, int lossdie, int durationdie1, int durationdie2) {
		return LeaderLoss.resolve(dice, lossdie, durationdie1, durationdie2, false);
	}

}
