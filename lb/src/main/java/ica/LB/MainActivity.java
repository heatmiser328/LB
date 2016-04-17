package ica.LB;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import java.util.*;
import ica.LB.Core.*;
import ica.LB.Adapters.*;


public class MainActivity extends AppCompatActivity {

    private BattleListAdapter mDrawerAdapter;
    private List<Battle> battles;
    private ExpandableListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String mActivitySubTitle;
    private int mActivityIcon;
    private FragmentManager manager;
    private Context context;
    private Battle battle;
    private Scenario scenario;
    private BattleFragment battleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = getApplicationContext();
        Lb.initialize(context);

        mDrawerList = (ExpandableListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mActivitySubTitle = "";
        mActivityIcon = context.getResources().getIdentifier("drawable/logo", null, context.getPackageName());

        addDrawerItems();
        setupDrawer();

        manager = getSupportFragmentManager();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Game saved = Lb.getSaved();
        if (saved != null) {
            battle = saved.getBattle();
            scenario = saved.getScenario();
            showBattle(battle, scenario);
        } else {
            showLanding();
        }
    }

    private void addDrawerItems() {
        battles = Lb.getBattles();
        mDrawerAdapter = new ica.LB.Adapters.BattleListAdapter(this, battles);
        mDrawerList.setAdapter(mDrawerAdapter);

        mDrawerList.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                battle = battles.get(groupPosition);
                scenario = battle.getScenarios().get(childPosition);
                showBattle(battle, scenario);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Select a Battle");
                getSupportActionBar().setSubtitle("");
                getSupportActionBar().setIcon(mActivityIcon);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (battle != null) {
                    getSupportActionBar().setTitle(battle.getName());
                    getSupportActionBar().setIcon(context.getResources().getIdentifier("drawable/" + battle.getImage(), null, context.getPackageName()));
                } else {
                    getSupportActionBar().setTitle(mActivityTitle);
                    getSupportActionBar().setIcon(mActivityIcon);
                }

                if (scenario != null) {
                    getSupportActionBar().setSubtitle(scenario.getName());
                } else {
                    getSupportActionBar().setSubtitle(mActivitySubTitle);
                }

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.battle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.battleReset) {
            if (battleView != null && battle != null && scenario != null) {
                Toast.makeText(MainActivity.this, "Reset " + battle.getName() + " : " + scenario.getName(), Toast.LENGTH_SHORT).show();
                battleView.reset();
            }
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showLanding() {
        Toast.makeText(MainActivity.this, "Select a Battle", Toast.LENGTH_SHORT).show();
        battleView = null;
        changeFragment(new LandingFragment());
    }

    private void showBattle(Battle battle, Scenario scenario) {
        Toast.makeText(MainActivity.this, "Selected " + battle.getName() + " : " + scenario.getName(), Toast.LENGTH_SHORT).show();

        getSupportActionBar().setTitle(battle.getName());
        getSupportActionBar().setIcon(context.getResources().getIdentifier("drawable/" + battle.getImage(), null, context.getPackageName()));
        getSupportActionBar().setSubtitle(scenario.getName());

        battleView = new BattleFragment();
        Bundle args = new Bundle();
        args.putInt("Battle", battle.getId());
        args.putInt("Scenario", scenario.getId());
        battleView.setArguments(args);
        changeFragment(battleView);
    }

    private void changeFragment(Fragment fragment) {//, boolean doAddToBackStack) {

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainViews, fragment);
        /*
        if (doAddToBackStack) {
            transaction.addToBackStack(null);
            activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        } else {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toggle.syncState();
        }
        */
        transaction.commit();

    }
}