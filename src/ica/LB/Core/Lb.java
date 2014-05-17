package ica.LB.Core;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class Lb {
	private int battle;
	private int scenario;
	private int turn;
	private int phase;

	public Lb () {
		battle = 0;
		scenario = 0;
		turn = 1;
		phase = 0;
	}

	public int getBattle() {
        return battle;
    }
	public void setBattle(int v) {
        battle = v;
    }
    
	public int getScenario() {
        return scenario;
    }
	public void setScenario(int v) {
        scenario = v;
    }
    
	public int getTurn() {
        return turn;
    }
	public void setTurn(int v) {
        turn = v;
    }
    
	public int getPhase() {
        return phase;
    }
	public void setPhase(int v) {
        phase = v;
    }
	
	public void reset(Battle b, Scenario s) {
		battle = b.getId();
		scenario = s.getId();
		turn = 1;
		phase = 0;
	}

}
