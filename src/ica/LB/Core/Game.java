package ica.LB.Core;

import 	java.util.Calendar;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class Game {

    private Battle battle;
    private Scenario scenario;
    private Lb saved;
	private String[] phases = new String[] {
		"Command",
		"Imperial: Charge a Cheval",
		"Imperial: Movement",
		"Imperial: Defensive Fire",
		"Imperial: Offensive Fire",
		"Imperial: Melee Assault",
		"Imperial: Rally",
		"Imperial: Rout",
		"Imperial: Readiness Recovery",
		"Sovereign: Charge a Cheval",
		"Sovereign: Movement",
		"Sovereign: Defensive Fire",
		"Sovereign: Offensive Fire",
		"Sovereign: Melee Assault",
		"Sovereign: Rally",
		"Sovereign: Rout",
		"Sovereign: Readiness Recovery"
	};
	
	public Game(Battle battle, Scenario scenario, Lb saved) {
		battle = battle;
		scenario = scenario;
		saved = saved;
		if (saved.getBattle() != battle.getId() || saved.getScenario() != scenario.getId())
			saved.reset(battle, scenario);
	}
	
	public Battle getBattle() {
        return battle;
    }

	public Scenario getScenario() {
        return scenario; 
    }
	
	public Lb getSaved() {
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
			nextTurn();
			saved.setPhase(0);
		}
	}
	
	public void prevPhase() {
		saved.setPhase(saved.getPhase()-1);
		if (saved.getPhase() < 0) {
			prevTurn();
			saved.setPhase(phases.length - 1);
		}
	}
			
    private int getTurnOffset(int turn) {
        return (turn - 1) * 20;
    }
}


