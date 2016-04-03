package com.ica.dice;

public class DiceResource {

	public DiceResource() {
	}
		
    // too slow?
	/*
	public static int getDieResource(int die, String basename) {
        String name = "drawable/" + basename + "_" + Integer.toString(die);
        return getResources().getIdentifier(name, null, getPackageName());
	}
	*/

	public static int getDieResource(DieColor color, int die) {
        switch (color) {
            case WHITE_BLACK:
                return getWhiteBlackDieResource(die);
            case RED_WHITE:
                return getRedWhiteDieResource(die);
            case BLACK_WHITE:
                return getBlackWhiteDieResource(die);
            case BLACK_RED:
                return getBlackRedDieResource(die);
            case BLUE_WHITE:
                return getBlueWhiteDieResource(die);
            case GREEN_WHITE:
                return getGreenWhiteDieResource(die);
            case YELLOW_BLACK:
                return getYellowBlackDieResource(die);
        }
        return -1;
    }
    
	public static int getWhiteBlackDieResource(int die) {
		switch(die) {
			case 1: 
				return R.drawable.die6_whiteblack_1;
			case 2: 
				return R.drawable.die6_whiteblack_2;
			case 3: 
				return R.drawable.die6_whiteblack_3;
			case 4: 
				return R.drawable.die6_whiteblack_4;
			case 5: 
				return R.drawable.die6_whiteblack_5;
			case 6: 
				return R.drawable.die6_whiteblack_6;
		}
		return R.drawable.die6_whiteblack_1;
	}

    public static int getRedWhiteDieResource(int die) {
        switch(die) {
            case 1:
                return R.drawable.die6_redwhite_1;
            case 2:
                return R.drawable.die6_redwhite_2;
            case 3:
                return R.drawable.die6_redwhite_3;
            case 4:
                return R.drawable.die6_redwhite_4;
            case 5:
                return R.drawable.die6_redwhite_5;
            case 6:
                return R.drawable.die6_redwhite_6;
        }
        return R.drawable.die6_redwhite_1;
    }

    public static int getBlackWhiteDieResource(int die) {
        switch(die) {
            case 1:
                return R.drawable.die6_blackwhite_1;
            case 2:
                return R.drawable.die6_blackwhite_2;
            case 3:
                return R.drawable.die6_blackwhite_3;
            case 4:
                return R.drawable.die6_blackwhite_4;
            case 5:
                return R.drawable.die6_blackwhite_5;
            case 6:
                return R.drawable.die6_blackwhite_6;
        }
        return R.drawable.die6_blackwhite_1;
    }

    public static int getBlackRedDieResource(int die) {
        switch(die) {
            case 1:
                return R.drawable.die6_blackred_1;
            case 2:
                return R.drawable.die6_blackred_2;
            case 3:
                return R.drawable.die6_blackred_3;
            case 4:
                return R.drawable.die6_blackred_4;
            case 5:
                return R.drawable.die6_blackred_5;
            case 6:
                return R.drawable.die6_blackred_6;
        }
        return R.drawable.die6_blackred_1;
    }
    
    public static int getBlueWhiteDieResource(int die) {
        switch(die) {
            case 1:
                return R.drawable.die6_bluewhite_1;
            case 2:
                return R.drawable.die6_bluewhite_2;
            case 3:
                return R.drawable.die6_bluewhite_3;
            case 4:
                return R.drawable.die6_bluewhite_4;
            case 5:
                return R.drawable.die6_bluewhite_5;
            case 6:
                return R.drawable.die6_bluewhite_6;
        }
        return R.drawable.die6_bluewhite_1;
    }

    public static int getGreenWhiteDieResource(int die) {
        switch(die) {
            case 1:
                return R.drawable.die6_greenwhite_1;
            case 2:
                return R.drawable.die6_greenwhite_2;
            case 3:
                return R.drawable.die6_greenwhite_3;
            case 4:
                return R.drawable.die6_greenwhite_4;
            case 5:
                return R.drawable.die6_greenwhite_5;
            case 6:
                return R.drawable.die6_greenwhite_6;
        }
        return R.drawable.die6_greenwhite_1;
    }

    public static int getYellowBlackDieResource(int die) {
        switch(die) {
            case 1:
                return R.drawable.die6_yellowblack_1;
            case 2:
                return R.drawable.die6_yellowblack_2;
            case 3:
                return R.drawable.die6_yellowblack_3;
            case 4:
                return R.drawable.die6_yellowblack_4;
            case 5:
                return R.drawable.die6_yellowblack_5;
            case 6:
                return R.drawable.die6_yellowblack_6;
        }
        return R.drawable.die6_yellowblack_1;
    }
    
}
