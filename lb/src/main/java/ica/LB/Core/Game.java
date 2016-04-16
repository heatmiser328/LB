package ica.LB.Core;

import 	java.util.Calendar;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class Game {

    private Battle battle;
    private Scenario scenario;
    private Saved saved;
	private String[] phases = new String[] {
		"Command",
		"Charge a Cheval",
		"Movement",
		"Defensive Fire",
		"Offensive Fire",
		"Melee Assault",
		"Rally",
		"Rout",
		"Readiness Recovery"
	};
	
	public Game(Battle b, Scenario s, Saved sv) {
		battle = b;
		scenario = s;
		saved = sv;
		if (saved.getBattle() != battle.getId() || saved.getScenario() != scenario.getId())
			saved.reset(battle, scenario);
	}
	
	public Battle getBattle() {
        return battle;
    }

	public Scenario getScenario() {
        return scenario; 
    }
	
	public Saved getSaved() {
        return saved; 
    }

	public String getCurrentTurn() {
    
        Calendar sd = Calendar.getInstance();
        sd.set(scenario.getStartYear(),scenario.getStartMonth()-1,scenario.getStartDay(),scenario.getStartHour(),scenario.getStartMinute());
        
        Calendar ed = Calendar.getInstance();
        ed.set(scenario.getEndYear(),scenario.getEndMonth()-1,scenario.getEndDay(),scenario.getEndHour(),scenario.getEndMinute());

        Calendar dt = Calendar.getInstance();
        dt.set(scenario.getStartYear(),scenario.getStartMonth()-1,scenario.getStartDay(),scenario.getStartHour(),scenario.getStartMinute());
		int offset = getTurnOffset(saved.getTurn());
		dt.add(Calendar.MINUTE, offset);
		
		if (dt.before(sd)) {
		    dt = sd;
			saved.setTurn(1);
		}
		else if (dt.after(ed)) {
		    dt = ed;
			saved.setTurn(saved.getTurn()-1);
		}
		
        return Format.TurnDate(dt.getTime());
	}

	public String getCurrentPhase() {
		if (saved.getPhase() < 0) {
			saved.setPhase(0);
		}
        else if (saved.getPhase() >= phases.length) {
			saved.setPhase(phases.length - 1);
		}
        return phases[saved.getPhase()];
	}

	public int getCurrentPlayer() {
        return saved.getPlayer();
	}

	public void reset() {
		saved.reset(battle, scenario);
	}
	
	public void nextTurn() {
		saved.setTurn(saved.getTurn()+1);
	}
	
	public void prevTurn() {
		saved.setTurn(saved.getTurn()-1);
	}
	
	public void nextPhase() {
		saved.setPhase(saved.getPhase()+1);
		if (saved.getPhase() >= phases.length) {
            if (saved.getPlayer() > 0) {
                nextTurn();
            } else {
                nextPlayer();
            }
			saved.setPhase(0);
		}
	}
	
	public void prevPhase() {
		saved.setPhase(saved.getPhase()-1);
		if (saved.getPhase() < 0) {
            if (saved.getPlayer() < 1) {
                prevTurn();
            } else {
                prevPlayer();
            }
			saved.setPhase(phases.length - 1);
		}
	}

	public void nextPlayer() {
        if (saved.getPlayer() == 0) {
            saved.setPlayer(1);
        } else {
            saved.setPlayer(0);
        }
	}
    public void prevPlayer() {
        nextPlayer();
    }

    private int getTurnOffset(int turn) {
        return (turn - 1) * 20;
    }
}


