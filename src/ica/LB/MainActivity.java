package ica.LB;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.ExpandableListView.OnChildClickListener;

import java.util.*;

public class MainActivity extends Activity {

    private ica.LB.Adapters.BattleListAdapter battleList;
    private List<ica.LB.Core.Battle> battles;
    private ExpandableListView battleListView;
    private Activity me;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        me = this;
        
        super.onCreate(savedInstanceState);

        ica.LB.Core.LbManager.initialize(getApplicationContext());

        setContentView(R.layout.main);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.titleLaBat);
        actionBar.setSubtitle(R.string.titleAsst);
        
		//Find our controls
		battleListView = (ExpandableListView)findViewById(R.id.listBattles);
    }
    
    @Override
	public void onResume() {
        super.onResume ();

		battles = ica.LB.Core.LbManager.getBattles();

		// create our adapter
		battleList = new ica.LB.Adapters.BattleListAdapter(this, battles);
					
		//Hook up our adapter to our ListView
		battleListView.setAdapter(battleList);

		battleListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Intent battleDetail = new Intent (me, BattleActivity.class);
                ica.LB.Core.Battle battle = battles.get(groupPosition);
                ica.LB.Core.Scenario scenario = battle.getScenarios().get(childPosition);
			    battleDetail.putExtra("Battle", battle.getId());
			    battleDetail.putExtra ("Scenario", scenario.getId());

			    startActivity (battleDetail);
        
                return true;
            }
        
        });
	}
}
