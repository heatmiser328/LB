package ica.LB;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * Created by jcapuano on 5/18/2014.
 */
public class BattleActivity extends Activity {
	private ImageView imgBack;
	private ImageView imgLb;
	private TextView txtBattleName;
	private TextView txtScenarioName;
	private Button btnReset;
	private TextView txtTurn;
	private Button btnTurnPrev;
	private Button btnTurnNext;
	private TextView txtPhase;
	private Button btnPhasePrev;
	private Button btnPhaseNext;
	private ImageButton btnFire;
	private ImageButton btnMelee;
	private ImageButton btnMorale;
	private ImageButton btnGeneral;
	private ica.LB.Core.Game game;
    private Activity me;

    @Override
    public void onCreate (Bundle bundle) {
        me = this;
        
        super.onCreate(bundle);

        Intent intent = getIntent();

		game = ica.LB.Core.LbManager.getGame(intent.getIntExtra ("Battle", -1), intent.getIntExtra("Scenario", -1));

        setContentView(R.layout.battle);		

		imgBack = (ImageView)findViewById(R.id.titleSubLbBack);
		imgLb = (ImageView)findViewById(R.id.titleSubLb);

		// title
		txtBattleName = (TextView)findViewById(R.id.titleSubBattleName);
		txtScenarioName = (TextView)findViewById(R.id.titleSubScenarioName);

		// current turn
		txtTurn = (TextView)findViewById(R.id.textTurn);
		btnTurnPrev = (Button)findViewById(R.id.btnTurnPrev);
		btnTurnNext = (Button)findViewById(R.id.btnTurnNext);

		// current phase
		txtPhase = (TextView)findViewById(R.id.textPhase);
		btnPhasePrev = (Button)findViewById(R.id.btnPhasePrev);
		btnPhaseNext = (Button)findViewById(R.id.btnPhaseNext);

		btnReset = (Button)findViewById(R.id.btnReset);

		btnFire = (ImageButton)findViewById(R.id.btnFire);
		btnMelee = (ImageButton)findViewById(R.id.btnMelee);
		btnMorale = (ImageButton)findViewById(R.id.btnMorale);
		btnGeneral = (ImageButton)findViewById(R.id.btnGeneral);
	}

    @Override
    public void onResume () {
        super.onResume();

        imgBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    navigateUp();
			}
		});        
        
		imgLb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    navigateUp();
			}
		});        
        
		txtBattleName.setText(game.getBattle().getName());
		txtScenarioName.setText(game.getScenario().getName());

		btnReset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    game.reset();
			    update();
			    save();
			}
		});        
			
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

		btnFire.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent fireDetail = new Intent (me, FireCombatActivity.class);
                fireDetail.putExtra("Battle", game.getBattle().getId());
                fireDetail.putExtra ("Scenario", game.getScenario().getId());

                startActivity (fireDetail);
            }
        });

		btnMelee.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
			    Intent meleeDetail = new Intent (me, MeleeCombatActivity.class);
			    meleeDetail.putExtra("Battle", game.getBattle().getId());
			    meleeDetail.putExtra ("Scenario", game.getScenario().getId());

			    startActivity (meleeDetail);
            }
        });
			
		btnMorale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
			    Intent moraleDetail = new Intent (me, MoraleActivity.class);
			    moraleDetail.putExtra("Battle", game.getBattle().getId());
			    moraleDetail.putExtra ("Scenario", game.getScenario().getId());

			    startActivity (moraleDetail);
            }
        });
		
        /*
		btnGeneral.Click += (sender, e) => { 

			var generalDetail = new Intent (this, typeof(GeneralActivity));
			generalDetail.PutExtra("Battle", game.Battle.Id);
			generalDetail.PutExtra ("Scenario", game.Scenario.Id);

			StartActivity (generalDetail);
		};
		*/
        
		update();
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

    private void navigateUp() {
		startActivity(new Intent(this, MainActivity.class));
	}

}
