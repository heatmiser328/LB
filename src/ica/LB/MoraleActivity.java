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
public class MoraleActivity extends Activity {
	private TextView txtBattleName;
	private TextView txtScenarioName;
	private ImageView imgBack;
	private ImageView imgLb;
	
	private Button btnMoralePrev;
	private Button btnMoraleNext;
	private EditText editMoraleValue;
	
	private ImageView imgMoraleDie1;
	private ImageView imgMoraleDie2;
	private Button btnMoraleDiceRoll;

	private Button btnMoraleMinus6;
	private Button btnMoraleMinus3;
	private Button btnMoraleMinus1;
	private Button btnMoralePlus1;
	private Button btnMoralePlus3;
	private Button btnMoralePlus6;
	
	private TextView txtMoraleResults;
	
	private ica.LB.Core.Game game;
	private ica.LB.Core.Dice dice;
	private ica.LB.Core.Morale morale;
	private ica.LB.Helpers.PlayAudio audio;
    
    public void onCreate(Bundle savedInstanceState) {
		dice = new ica.LB.Core.Dice(2, 1, 6);
		morale = new ica.LB.Core.Morale();
		audio = new ica.LB.Helpers.PlayAudio (this);
	
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        
		game = ica.LB.Core.LbManager.getGame(intent.getIntExtra ("Battle", -1), intent.getIntExtra("Scenario", -1));

		// set our layout to be the home screen
        setContentView(R.layout.morale);

		imgBack = (ImageView)findViewById(R.id.titleSubLbBack);
		imgLb = (ImageView)findViewById(R.id.titleSubLb);

		// title
		txtBattleName = (TextView)findViewById(R.id.titleSubBattleName);
		txtScenarioName = (TextView)findViewById(R.id.titleSubScenarioName);
		
		btnMoralePrev = (Button)findViewById(R.id.btnMoralePrev);
		btnMoraleNext = (Button)findViewById(R.id.btnMoraleNext);
		editMoraleValue = (EditText)findViewById(R.id.editMoraleValue);
		
		editMoraleValue.setText("11");
		
		imgMoraleDie1 = (ImageView)findViewById (R.id.imgMoraleDie1);
		imgMoraleDie2 = (ImageView)findViewById (R.id.imgMoraleDie2);
		btnMoraleDiceRoll = (Button)findViewById(R.id.btnMoraleDiceRoll);
		
		btnMoraleMinus6 = (Button)findViewById(R.id.btnMoraleMinus6);
		btnMoraleMinus3 = (Button)findViewById(R.id.btnMoraleMinus3);
		btnMoraleMinus1 = (Button)findViewById(R.id.btnMoraleMinus1);
		btnMoralePlus1 = (Button)findViewById(R.id.btnMoralePlus1);
		btnMoralePlus3 = (Button)findViewById(R.id.btnMoralePlus3);
		btnMoralePlus6 = (Button)findViewById(R.id.btnMoralePlus6);
		
		txtMoraleResults = (TextView)findViewById(R.id.txtMoraleResults);
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
		
		
		btnMoralePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    ica.LB.Core.Base6Value b6i = new ica.LB.Core.Base6Value(getMoraleValue());
			    int value = b6i.subtract(1);
			    editMoraleValue.setText(Integer.toString(value));
                updateResults();
			}
		});
		btnMoraleNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    ica.LB.Core.Base6Value b6i = new ica.LB.Core.Base6Value(getMoraleValue());
			    int value = b6i.add(1);
			    editMoraleValue.setText(Integer.toString(value));
                updateResults();
			}
		});
		editMoraleValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
			@Override
            public void afterTextChanged(Editable s) {
			    updateResults();
            }
        });
		
		imgMoraleDie1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(1);
			    displayDice();
			    updateResults();
			}
		});
		imgMoraleDie2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(2);
			    displayDice();
			    updateResults();
			}
		});
		
		btnMoraleDiceRoll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    audio.play();
			    dice.roll();
			    displayDice();
			    updateResults();
			}
		});
		
		btnMoraleMinus6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    modifyDice(-6);
			    displayDice();
			    updateResults();
			}
		});
		btnMoraleMinus3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    modifyDice(-3);
			    displayDice();
			    updateResults();
			}
		});
		btnMoraleMinus1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    modifyDice(-1);
			    displayDice();
			    updateResults();
			}
		});
		
		btnMoralePlus6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    modifyDice(6);
			    displayDice();
			    updateResults();
			}
		});
		btnMoralePlus3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    modifyDice(3);
			    displayDice();
			    updateResults();
			}
		});
		btnMoralePlus1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    modifyDice(1);
			    displayDice();
			    updateResults();
			}
		});
	}
	
	void updateResults() {
		int d = (dice.getDie(0)*10) + dice.getDie(1);
		String result = morale.resolve(getMoraleValue(), d);
		txtMoraleResults.setText(result, TextView.BufferType.NORMAL);
	}
	
	void displayDice() {
		imgMoraleDie1.setImageResource(ica.LB.Helpers.DiceResources.getWhiteBlack(dice.getDie(0)));
		imgMoraleDie2.setImageResource(ica.LB.Helpers.DiceResources.getRedWhite(dice.getDie(1)));
	}

	int getMoraleValue() {
        return Integer.parseInt(editMoraleValue.getText().toString());
	}
	
	void modifyDice(int mod) {
		ica.LB.Core.Base6Value b6i = new ica.LB.Core.Base6Value((dice.getDie(0)*10) + dice.getDie(1));
		int value = b6i.add(mod);
		dice.setDie(0, value / 10);
		dice.setDie(1, value % 10);
	}
	
	void incrementDie(int die) {
		int value = dice.getDie(die-1);
		if (++value > 6) value = 1;
		dice.setDie(die-1, value);
	}
	
	void navigateUp() {
		Intent battleDetail = new Intent (this, BattleActivity.class);
		battleDetail.putExtra("Battle", game.getBattle().getId());
		battleDetail.putExtra ("Scenario", game.getScenario().getId());

		startActivity (battleDetail);
	}
}