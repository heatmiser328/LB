package ica.LB.Core;

/**
 * Created by jcapuano on 5/30/2014.
 */
public class Saved {
	private int battle;
	private int scenario;
	private int turn;
	private int phase;
	private int player;

    public Saved() {
		battle = 0;
		scenario = 0;
		turn = 1;
		phase = 0;
		player = 0;
    }

	public Saved(int battleid, int scenarioid) {
        super();
		battle = battleid;
		scenario = scenarioid;
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
    
	public int getPhase() { return phase; }
	public void setPhase(int v) {
        phase = v;
    }

	public int getPlayer() { return player;	}
	public void setPlayer(int v) {
		player = v;
	}

    public boolean isValid() {
        return battle > 0 && scenario > 0;
    }

	public void reset(Battle b, Scenario s) {
		battle = b.getId();
		scenario = s.getId();
		turn = 1;
		phase = 0;
		player = 0;
	}
}
