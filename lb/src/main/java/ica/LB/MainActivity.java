package ica.LB;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.ExpandableListView.OnChildClickListener;

import java.util.*;
import ica.LB.Core.*;
import ica.LB.Adapters.*;

public class MainActivity extends Activity {

    private BattleListAdapter battleList;
    private List<Battle> battles;
    private ExpandableListView battleListView;
    private Activity me;

    private static boolean initial = true;
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        me = this;
        
        super.onCreate(savedInstanceState);

        Lb.initialize(getApplicationContext());

        setContentView(R.layout.main);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.titleLaBat);
        actionBar.setSubtitle(R.string.titleAsst);
        
		//Find our controls
		battleListView = (ExpandableListView)findViewById(R.id.listBattles);
        battles = Lb.getBattles();

        // create our adapter
        battleList = new ica.LB.Adapters.BattleListAdapter(this, battles);

        //Hook up our adapter to our ListView
        battleListView.setAdapter(battleList);

        battleListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Battle battle = battles.get(groupPosition);
                Scenario scenario = battle.getScenarios().get(childPosition);
                showBattle(battle, scenario);

                return true;
            }

        });

        if (initial) {
            Game saved = Lb.getSaved();
            if (saved != null)
                showBattle(saved.getBattle(), saved.getScenario());
        }
        initial = false;
    }
    
    private void showBattle(Battle battle, Scenario scenario) {
        Intent battleDetail = new Intent (me, BattleActivity.class);
        battleDetail.putExtra("Battle", battle.getId());
        battleDetail.putExtra ("Scenario", scenario.getId());

        startActivity (battleDetail);
    }
}
