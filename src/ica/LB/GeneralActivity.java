package ica.LB;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * Created by jcapuano on 5/24/2014.
 */
public class GeneralActivity extends Activity {
    
	private TextView txtBattleName;
	private TextView txtScenarioName;
	private ImageView imgBack;
	private ImageView imgLb;
	
	private ImageView imgGeneral2Die1;
	private ImageView imgGeneral2Die2;
	private Button btnGeneral2DiceRoll;

	private ImageView imgGeneral1Die1;
	private Button btnGeneral1DiceRoll;
	
	private ica.LB.Core.Game game;
	private ica.LB.Core.Dice dice1;
	private ica.LB.Core.Dice dice2;
	private ica.LB.Helpers.PlayAudio audio;

    public void onCreate(Bundle savedInstanceState) {
		dice1 = new ica.LB.Core.Dice(1, 1, 6);
		dice2 = new ica.LB.Core.Dice(2, 1, 6);
		audio = new ica.LB.Helpers.PlayAudio (this);
	
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        
		game = ica.LB.Core.LbManager.getGame(intent.getIntExtra ("Battle", -1), intent.getIntExtra("Scenario", -1));
        
		// set our layout to be the home screen
        setContentView(R.layout.general);

		imgBack = (ImageView)findViewById(R.id.titleSubLbBack);
		imgLb = (ImageView)findViewById(R.id.titleSubLb);

		// title
		txtBattleName = (TextView)findViewById(R.id.titleSubBattleName);
		txtScenarioName = (TextView)findViewById(R.id.titleSubScenarioName);
		
		imgGeneral2Die1 = (ImageView)findViewById (R.id.imgGeneral2Die1);
		imgGeneral2Die2 = (ImageView)findViewById (R.id.imgGeneral2Die2);
		btnGeneral2DiceRoll = (Button)findViewById(R.id.btnGeneral2DiceRoll);

		imgGeneral1Die1 = (ImageView)findViewById (R.id.imgGeneral1Die1);
		btnGeneral1DiceRoll = (Button)findViewById(R.id.btnGeneral1DiceRoll);
	}

    @Override
    public void onResume () {
		super.onResume ();

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
		
		btnGeneral2DiceRoll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    audio.play();
			    dice2.roll();
			    displayDice();
			}
		});
		
		btnGeneral1DiceRoll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    audio.play();
			    dice1.roll();
			    displayDice();
			}
		});
	}
	
	void displayDice() {
		imgGeneral2Die1.setImageResource(ica.LB.Helpers.DiceResources.getWhiteBlack(dice2.getDie(0)));
		imgGeneral2Die2.setImageResource(ica.LB.Helpers.DiceResources.getRedWhite(dice2.getDie(1)));
		imgGeneral1Die1.setImageResource(ica.LB.Helpers.DiceResources.getBlue(dice1.getDie(0)));
	}

	void navigateUp() {
		Intent battleDetail = new Intent (this, BattleActivity.class);
		battleDetail.putExtra("Battle", game.getBattle().getId());
		battleDetail.putExtra ("Scenario", game.getScenario().getId());

		startActivity (battleDetail);
	}
}