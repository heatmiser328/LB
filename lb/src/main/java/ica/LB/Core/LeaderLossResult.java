package ica.LB.Core;

/**
 * Created by jcapuano on 5/16/2014.
 */
public class LeaderLossResult {

	private boolean attacker;
	private boolean defender;
	private boolean wounded;
	private boolean killed;
	private boolean captured;
	private String injury;
	private int duration;

	public LeaderLossResult() {
		attacker = false;
		defender = false;
		wounded = false;
		killed = false;
		captured = false;
		injury = "";
		duration = 0;
    }
	public LeaderLossResult(boolean _attacker, boolean _defender, boolean _wounded, boolean _killed, boolean _captured, String _injury, int _duration) {
		attacker = _attacker;
		defender = _defender;
		wounded = _wounded;
		killed = _killed;
		captured = _captured;
		injury = _injury;
		duration = _duration;
	}
	
	public boolean getAttacker() {
        return attacker;
    }
	public void setAttacker(boolean b) {
        attacker = b;
    }
    
	public boolean getDefender() {
        return defender;
    }
	public void setDefender(boolean b) {
        defender = b;
    }
    
	public boolean getWounded() {
        return wounded;
    }
	public void setWounded(boolean b) {
        wounded = b;
    }
    
	public boolean getKilled() {
        return killed;
    }
	public void setKilled(boolean b) {
        killed = b;
    }
    
	public boolean getCaptured() {
        return captured;
    }
	public void setCaptured(boolean b) {
        captured = b;
    }
    
	public String getInjury() {
        return injury;
    }
	public void setInjury(String s) {
        injury = s;
    }
    
	public int getDuration() {
        return duration;
    }
	public void setDuration(int i) {
        duration = i;
    }

}
