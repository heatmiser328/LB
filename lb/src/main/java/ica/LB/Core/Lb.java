package ica.LB.Core;

import android.content.Context;
import android.util.Log;
import java.io.*;
import java.text.*;
import java.util.*;


public class Lb {
    private static Context ctx;
    private static List<Battle> battles;
    private static Saved saved;

    public static void initialize(Context c) {
        ctx = c;
    }
    
    public static List<Battle> getBattles() {
        try {
            if (battles == null) {
                battles = BattleRepository.read(ctx.getAssets().open("battles.json"));
            }
        }
        catch (Exception ex) {
            Log.e("getBattles", "Failed to get battles", ex);
        }
        return battles;
    }
    
    public static Battle getBattle(int id) {
        List<Battle> l = getBattles();
        for (Battle b : l) {
			if (b.getId() == id)
				return b;
		}
		return null;        
    }
    
	public static Game getGame(int battleid, int scenarioid) {
		Battle battle = getBattle(battleid);
		Scenario scenario = battle.getScenario(scenarioid);
		Saved saved = getSaved(battle, scenario);
        if (saved == null || saved.getBattle() != battleid && saved.getScenario() != scenarioid) {
            saved = new Saved(battleid, scenarioid);
        }
		return new Game(battle, scenario, saved);
	}
    
    
    public static Game getSaved() {
        Saved saved = getSaved(null, null);
        if (saved != null) {
		    Battle battle = getBattle(saved.getBattle());
            if (battle != null) {
                Scenario scenario = battle.getScenario(saved.getScenario());
                return new Game(battle, scenario, saved);
            }
        }
        return null;
    }
    
    public static Saved getSaved(Battle battle, Scenario scenario) {
        try {
            if (saved == null) {
                saved = SavedRepository.read(ctx.openFileInput("saved.json"));
            }
        }
        catch (FileNotFoundException fex) {
            saved = new Saved();
            if (battle != null)
                saved.setBattle(battle.getId());
            if (scenario != null)
                saved.setScenario(scenario.getId());
        }
        catch (Exception ex) {
            Log.e("getSaved", "Failed to get saved battle", ex);
        }
        return saved;
    }
    
    public static void saveSaved(Game game) {
        try {
            Saved sv = game.getSaved();
            if (sv != null) {
                SavedRepository.write(ctx.openFileOutput("saved.json", 0), sv);
            }
        }
        catch (Exception ex) {
            Log.e("saveSaved", "Failed to save battle", ex);
        }
    }
}
