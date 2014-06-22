package ica.LB;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.*;

import ica.LB.Adapters.TabsPagerAdapter;

/**
 * Created by jcapuano on 5/18/2014.
 */
public class BattleActivity extends FragmentActivity implements ActionBar.TabListener {
	private TextView txtTurn;
	private Button btnTurnPrev;
	private Button btnTurnNext;
	private TextView txtPhase;
	private Button btnPhasePrev;
	private Button btnPhaseNext;
    
    private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Fire", "Melee", "Morale", "General" };
    
	private ica.LB.Core.Game game;
    private FragmentActivity me;

    @Override
    public void onCreate (Bundle bundle) {
        me = this;
        
        super.onCreate(bundle);

        Intent intent = getIntent();
		game = ica.LB.Core.LbManager.getGame(intent.getIntExtra ("Battle", -1), intent.getIntExtra("Scenario", -1));

        setContentView(R.layout.battle);

		// current turn
		txtTurn = (TextView)findViewById(R.id.textTurn);
		btnTurnPrev = (Button)findViewById(R.id.btnTurnPrev);
		btnTurnNext = (Button)findViewById(R.id.btnTurnNext);

		// current phase
		txtPhase = (TextView)findViewById(R.id.textPhase);
		btnPhasePrev = (Button)findViewById(R.id.btnPhasePrev);
		btnPhaseNext = (Button)findViewById(R.id.btnPhaseNext);

        // tabs
		viewPager = (ViewPager) findViewById(R.id.battleViews);
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);

        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle(game.getBattle().getName());
        actionBar.setSubtitle(game.getScenario().getName());

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}
        
		btnTurnPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    game.prevTurn();
			    update();
			    save(); 
			}
		});        
        
		btnTurnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		    	game.nextTurn();
			    update();
			    save(); 
			}
		});        

		btnPhasePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
    			game.prevPhase();
			    update();
			    save(); 
			}
		});        
        
		btnPhaseNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
    			game.nextPhase();
			    update();
			    save(); 
			}
		});        

		/**
		 * on swiping the viewpager make respective tab selected
         * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		update();
	}

    @Override
    public void onResume () {
        super.onResume();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.battle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.battleReset:
                game.reset();
                update();
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
        
    private void update() {
		txtTurn.setText(game.getCurrentTurn());
		txtPhase.setText(game.getCurrentPhase());
	}
	
    private void save() {
        try {
            ica.LB.Core.LbManager.saveGame(game);
        } catch(Exception e) {
        }
	}
}
