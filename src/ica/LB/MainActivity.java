package ica.LB;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.ExpandableListView.OnChildClickListener;

import java.io.IOException;
import java.util.*;

public class MainActivity extends Activity {

    private ica.LB.Adapters.BattleListAdapter battleList;
    private List<ica.LB.Core.Battle> battles;
    private ExpandableListView battleListView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ica.LB.Core.LbManager.initialize(getApplicationContext().getAssets().open(ica.LB.Core.LbManager.getBattlesAssetsPath()));
        } catch (IOException e) {
        }

        setContentView(R.layout.main);
        
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

		battleListView.setOnChildClickListener(listItemClicked);
	}
   
    private OnChildClickListener listItemClicked =  new OnChildClickListener() {
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            /*
            Intent battleDetail = new Intent (this, typeof(BattleActivity));
            Battle battle = battles.get(groupPosition);
            Scenario scenario = battle.getScenarios().get(childPosition);
			battleDetail.putExtra("Battle", battle.getId());
			battleDetail.putExtra ("Scenario", scenario.getId());

			startActivity (battleDetail);
			*/
        
            return false;
        }
    };    

}
