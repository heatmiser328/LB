package ica.LB.Core;

import java.util.*;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class MeleeCombat {
	private ArrayList<Odds> oddsTable;

	public MeleeCombat ()
	{
		oddsTable = new ArrayList<Odds>();
		oddsTable.add(new Odds(-2, "1:2"));
		oddsTable.add(new Odds(1, "1:1"));
		oddsTable.add(new Odds(1.5, "1.5:1"));
		oddsTable.add(new Odds(2, "2:1"));
		oddsTable.add(new Odds(2.5, "2.5:1"));
		oddsTable.add(new Odds(3, "3:1"));
		oddsTable.add(new Odds(3.5, "3.5:1"));
		oddsTable.add(new Odds(4, "4:1"));
		oddsTable.add(new Odds(4.5, "4.5:1"));
		oddsTable.add(new Odds(5, "5:1"));
	}
	
	public Odds getDefaultOdds() {
        return oddsTable.get(1);
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
    
	public Odds calculate(double att, double def) {
        return Odds.calculate(oddsTable, att, def, 0);
    }
	
	public String resolve(Odds odds, int dice) {
		String result = "Blank";
		if (odds.getValue() == -2) {
			if (dice <= 14) {
				result = "AR";
            }
			else if (dice <= 34) {
				result = "AD";
			}
			else if (dice == 52) {
				result = "0*/0";
            }
			else if (dice == 53) {
				result = "1/1";
            }
			else if (dice == 54) {
				result = "1/2*";
            }
			else if (dice == 55) {
				result = "0/1";
            }
			else if (dice == 56) {
				result = "1*/0";
            }

			else if (dice == 61) {
				result = "0/2";
            }
			else if (dice == 62) {
				result = "2/1*";
            }
			else if (dice == 63) {
				result = "0/0";
            }
			else if (dice == 64) {
				result = "2/2";
            }

			else if (dice >= 65) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 1) {
			if 		(dice <= 15) {
				result = "AD";
            }

			else if (dice == 42) {
				result = "2*/1";
            }
			else if (dice == 43) {
				result = "2/1*";
            }
			else if (dice == 44) {
				result = "2/1";
            }
			else if (dice == 45) {
				result = "1*/1";
            }
			else if (dice == 46) {
				result = "1/2";
            }

			else if (dice == 51) {
				result = "1/1";
            }
			else if (dice == 52) {
				result = "0/0*";
            }
			else if (dice == 53) {
				result = "2/1";
            }
			else if (dice == 54) {
				result = "1*/2";
            }
			else if (dice == 55) {
				result = "2/2";
            }
			else if (dice == 56) {
				result = "0/0";
            }

			else if (dice == 61) {
				result = "1/0*";
            }
				
			else if (dice >= 62) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 1.5) {
			if 		(dice <= 12) {
				result = "AD";
            }

			else if (dice == 33) {
				result = "1/2";
            }
			else if (dice == 34) {
				result = "0/0";
            }
			else if (dice == 35) {
				result = "1/1";
            }
			else if (dice == 36) {
				result = "2*/0";
            }

			else if (dice == 41) {
				result = "0/1*";
            }
			else if (dice == 42) {
				result = "1/1";
            }
			else if (dice == 43) {
				result = "2/2*";
            }
			else if (dice == 44) {
				result = "3/1";
            }
			else if (dice == 45) {
				result = "0/2";
            }
			else if (dice == 46) {
				result = "2/1";
            }

			else if (dice == 51) {
				result = "1/1*";
            }
			else if (dice == 52) {
				result = "2*/1";
            }

			else if (dice >= 53) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 2) {
			if 		(dice <= 11) {
				result = "AD";
            }

			else if (dice == 25) {
				result = "0/3";
            }
			else if (dice == 26) {
				result = "1/2";
            }
			else if (dice == 31) {
				result = "2*/1";
            }
			else if (dice == 32) {
				result = "0/0";
            }
			else if (dice == 33) {
				result = "0/1*";
            }
			else if (dice == 34) {
				result = "1/0";
            }
			else if (dice == 35) {
				result = "3/2*";
            }
			else if (dice == 36) {
				result = "1/1";
            }
			else if (dice == 41) {
				result = "2/2*";
            }
			else if (dice == 42) {
				result = "1*/2";
            }
			else if (dice == 43) {
				result = "1*/1";
            }
			else if (dice == 44) {
				result = "0/2*";
            }
				
			else if (dice >= 45) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 2.5) {
			if 		(dice == 23) {
				result = "1/4";
            }
			else if (dice == 24) {
				result = "2/3";
            }
			else if (dice == 25) {
				result = "0*/0";
            }
			else if (dice == 26) {
				result = "1/1*";
            }
			else if (dice == 31) {
				result = "2/3*";
            }
			else if (dice == 32) {
				result = "3/3";
            }
			else if (dice == 33) {
				result = "0/1";
            }
			else if (dice == 34) {
				result = "1/0";
            }
			else if (dice == 35) {
				result = "2/2*";
            }

			else if (dice >= 36) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 3) {
			if 		(dice == 16) {
				result = "0/0*";
            }
			else if (dice == 21) {
				result = "2/3";
            }
			else if (dice == 22) {
				result = "0/2";
            }
			else if (dice == 23) {
				result = "2*/0";
            }
			else if (dice == 24) {
				result = "1/2";
            }
			else if (dice == 25) {
				result = "0/1";
            }
			else if (dice == 26) {
				result = "2*/3";
            }
			else if (dice == 31) {
				result = "1/2*";
            }
				
			else if (dice >= 65) {
				result = "DR";
            }

			else if (dice >= 32) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 3.5) {
			if 		(dice == 12) {
				result = "0/0";
            }
			else if (dice == 13) {
				result = "2*/2";
            }
			else if (dice == 14) {
				result = "3/3";
            }
			else if (dice == 15) {
				result = "2/4";
            }
			else if (dice == 16) {
				result = "3/1";
            }
			else if (dice == 21) {
				result = "0/1*";
            }
				
			else if (dice >= 62) {
				result = "DR";
            }

			else if (dice >= 22) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 4) {
			if 		(dice == 11) {
				result = "2*/1";
            }
			else if (dice == 12) {
				result = "1/2";
            }
			else if (dice == 13) {
				result = "0/2";
            }
			else if (dice == 14) {
				result = "0/1*";
            }
			else if (dice == 15) {
				result = "1/1*";
            }
				
			else if (dice >= 56) {
				result = "DR";
            }

			else if (dice >= 16) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 4.5) {
			if 		(dice == 11) {
				result = "3/2";
            }

			else if (dice >= 66) {
				result = "DS";
            }

			else if (dice >= 42) {
				result = "DR";
            }

			else if (dice >= 12) {
				result = "DD";
            }
		}
		else if (odds.getValue() == 5) {
			if (dice >= 62) {
				result = "DS";
            }

			else if (dice >= 33) {
				result = "DR";
            }

			else if (dice >= 11) {
				result = "DD";
            }
		}
		else {
			result = "Blank";
		}
		
	    /*
		if (dice <= 12) {
	        result = "& " + result;
	    } 
		else if (dice >= 64) {
		    result += " &";
	    }
	    */

	    return result;
	}
	
	public LeaderLossResult resolveLeaderLoss(int dice, int lossdie, int durationdie1, int durationdie2) {
		return LeaderLoss.resolve(dice, lossdie, durationdie1, durationdie2, true);
	}
}
