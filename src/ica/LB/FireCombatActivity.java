package ica.LB;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by jcapuano on 5/18/2014.
 */
public class FireCombatActivity extends Activity {
    
	private TextView txtBattleName;
	private TextView txtScenarioName;
	private ImageView imgBack;
	private ImageView imgLb;
	
	private Button btnAttackerValuePrev;
	private Button btnAttackerValueNext;
	private EditText editAttackerValue;
	private ToggleButton btnAttackerMod13;
	private ToggleButton btnAttackerMod12;
	private ToggleButton btnAttackerMod32;
	private ToggleButton btnAttackerModCann;
	
	private Button btnDefenderValuePrev;
	private Button btnDefenderValueNext;
	private EditText editDefenderValue;
	
	private Button btnDefenderIncrPrev;
	private Button btnDefenderIncrNext;
	private EditText editDefenderIncr;
	
	private ImageView imgFireDie1;
	private ImageView imgFireDie2;
	private ImageView imgFireDie3;
	private ImageView imgFireDie4;
	private ImageView imgFireDie5;
	private Button btnFireDiceRoll;
	
	private TextView txtFireResults;
	private TextView txtFireLeaderLoss;
	private ImageView imgFireLeaderLossSide;
	private ImageView imgFireLeaderLoss;
	
	private Spinner spinFireOdds;
	
	private ica.LB.Core.Game game;
	
	private ica.LB.Core.Dice dice;
	private ica.LB.Core.FireCombat fc;
	private ica.LB.Core.Odds odds;
	private ica.LB.Helpers.PlayAudio audio;

	private double[] defenseValues = new double[] {4,6,7,8,9,10,12,14,16,18};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		dice = new ica.LB.Core.Dice(5, 1, 6);
		fc = new ica.LB.Core.FireCombat();
		odds = fc.getDefaultOdds();
		audio = new ica.LB.Helpers.PlayAudio (this);
    
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        
		game = ica.LB.Core.LbManager.getGame(intent.getIntExtra ("Battle", -1), intent.getIntExtra("Scenario", -1));

		// set our layout to be the home screen
		setContentView(R.layout.firecombat);		

		imgBack = (ImageView)findViewById(R.id.titleSubLbBack);
		imgLb = (ImageView)findViewById(R.id.titleSubLb);

		// title
		txtBattleName = (TextView)findViewById(R.id.titleSubBattleName);
		txtScenarioName = (TextView)findViewById(R.id.titleSubScenarioName);
		
		// attacker
		btnAttackerValuePrev = (Button)findViewById(R.id.btnFireAttackerPrev);
		btnAttackerValueNext = (Button)findViewById(R.id.btnFireAttackerNext);
		editAttackerValue = (EditText)findViewById(R.id.textFireAttackerValue);
		btnAttackerMod13 = (ToggleButton)findViewById(R.id.btnFireAttacker13);
		btnAttackerMod12 = (ToggleButton)findViewById(R.id.btnFireAttacker12);
		btnAttackerMod32 = (ToggleButton)findViewById(R.id.btnFireAttacker32);
		btnAttackerModCann = (ToggleButton)findViewById(R.id.btnFireAttackerCann);
		
		// defender
		btnDefenderValuePrev = (Button)findViewById(R.id.btnFireDefenderPrev);
		btnDefenderValueNext = (Button)findViewById(R.id.btnFireDefenderNext);
		editDefenderValue = (EditText)findViewById(R.id.textFireDefenderValue);
		
		btnDefenderIncrPrev = (Button)findViewById(R.id.btnFireDefenderIncrPrev);
		btnDefenderIncrNext = (Button)findViewById(R.id.btnFireDefenderIncrNext);
		editDefenderIncr = (EditText)findViewById(R.id.textFireDefenderIncr);
		
		editAttackerValue.setText("1");
		editDefenderValue.setText("1");
		editDefenderIncr.setText("1");
		
		spinFireOdds = (Spinner)findViewById(R.id.spinFireOdds);
		
		imgFireDie1 = (ImageView)findViewById(R.id.imgFireDie1);
		imgFireDie2 = (ImageView)findViewById(R.id.imgFireDie2);
		imgFireDie3 = (ImageView)findViewById(R.id.imgFireDie3);
		imgFireDie4 = (ImageView)findViewById(R.id.imgFireDie4);
		imgFireDie5 = (ImageView)findViewById(R.id.imgFireDie5);
		btnFireDiceRoll = (Button)findViewById(R.id.btnFireDiceRoll);
		
		// results
		txtFireResults = (TextView)findViewById(R.id.txtFireResults);
		imgFireLeaderLossSide = (ImageView)findViewById(R.id.imgFireLeaderLossSide);
		txtFireLeaderLoss = (TextView)findViewById(R.id.txtFireLeaderLoss);
		imgFireLeaderLoss = (ImageView)findViewById(R.id.imgFireLeaderLoss);
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
		
		// attacker
		btnAttackerValuePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getAttackerValue();
			    if (--value < 1) value = 1;
			    editAttackerValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
        
		btnAttackerValueNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getAttackerValue();
			    editAttackerValue.setText(Double.toString(++value));
			    calcOdds();
			    updateResults();
			}
		});
		editAttackerValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
			@Override
            public void afterTextChanged(Editable s) {
			    calcOdds();
			    updateResults();
            }
        });
        
		btnAttackerMod13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getAttackerValue();
			    if (btnAttackerMod13.isChecked()) {
				    value /= 3;
			    }
			    else {
				    value *= 3;
			    }
			    editAttackerValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
		btnAttackerMod12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getAttackerValue();
			    if (btnAttackerMod12.isChecked()) {
				    value /= 2;
			    }
			    else {
				    value *= 2;
			    }
			    editAttackerValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
		btnAttackerMod32.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getAttackerValue();
			    if (btnAttackerMod32.isChecked()) {
				    value *= 1.5;
			    }
			    else {
				    value /= 1.5;
			    }
			    editAttackerValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
		btnAttackerModCann.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    updateResults();
			}
		});

		// defender
		btnDefenderValuePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getDefenderValue();
			    value = findNearestDefenseValue(value - 1, true);
			    editDefenderValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
		btnDefenderValueNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getDefenderValue();
			    value = findNearestDefenseValue(value + 1, false);
			    editDefenderValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
		editDefenderValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
            public void afterTextChanged(Editable s) {
			    calcOdds();
			    updateResults();
            }
        });
		
		btnDefenderIncrPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getDefenderIncrements();
			    if (--value < 1) value = 1;
			    editDefenderIncr.setText(Integer.toString(value));
			    updateResults();
			}
		});
		btnDefenderIncrNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getDefenderIncrements();
			    editDefenderIncr.setText(Integer.toString(++value));
			    updateResults();
			}
		});
		editDefenderIncr.addTextChangedListener(new TextWatcher() {
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
        
		spinFireOdds.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			    odds = fc.getOddsItem(pos);
			    updateResults();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });            
        
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, fc.getOddsList());
		adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
		spinFireOdds.setAdapter(adapter);
		
		
		imgFireDie1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(1);
			    displayDice();
			    updateResults();
			}
		});
		imgFireDie2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(2);
			    displayDice();
			    updateResults();
			}
		});
		imgFireDie3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(3);
			    displayDice();
			    updateResults();
			}
		});
		imgFireDie4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(4);
			    displayDice();
			    updateResults();
			}
		});
		imgFireDie5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(5);
			    displayDice();
			    updateResults();
			}
		});
		
		btnFireDiceRoll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    audio.play();
			    dice.roll();
			    displayDice();
			    updateResults();
			}
		});
		
		displayOdds();
		displayDice();
	}
	
	void calcOdds() {
		odds = fc.calculate(getAttackerValue(), getDefenderValue(), btnAttackerModCann.isChecked());
		if (odds == null)
			odds = fc.getDefaultOdds();
		displayOdds();
	}
	
	void updateResults() {
		int d = (dice.getDie(0)*10) + dice.getDie(1);
		String result = fc.resolve(odds, getDefenderIncrements(), d);
		txtFireResults.setText(result, TextView.BufferType.NORMAL);
					
		ica.LB.Core.LeaderLossResult ll = fc.resolveLeaderLoss(d, dice.getDie(2), dice.getDie(3), dice.getDie(4));
		imgFireLeaderLossSide.setVisibility((ll.getKilled() || ll.getWounded() || ll.getCaptured()) ? View.VISIBLE : View.INVISIBLE);
		imgFireLeaderLossSide.setImageResource (ll.getAttacker() ? R.drawable.attacker : R.drawable.defender);
		txtFireLeaderLoss.setVisibility((ll.getKilled() || ll.getWounded() || ll.getCaptured()) ? View.VISIBLE : View.INVISIBLE);
		String loss = ll.getInjury() + (ll.getWounded() ? (" " + Integer.toString(ll.getDuration()) + " hours") : "");
		txtFireLeaderLoss.setText(loss, TextView.BufferType.NORMAL);

		imgFireLeaderLoss.setVisibility((ll.getKilled() || ll.getWounded() || ll.getCaptured()) ? View.VISIBLE : View.INVISIBLE);
		imgFireLeaderLoss.setImageResource(ll.getCaptured() ? R.drawable.capture : (ll.getWounded() ? R.drawable.ambulance : R.drawable.tombstone));
	}
	
	void displayOdds() {
		spinFireOdds.setSelection(fc.getOddsIndex(odds.getName()));
	}

	void displayDice() {
		imgFireDie1.setImageResource(ica.LB.Helpers.DiceResources.getWhiteBlack(dice.getDie(0)));
		imgFireDie2.setImageResource(ica.LB.Helpers.DiceResources.getRedWhite(dice.getDie(1)));
		imgFireDie3.setImageResource(ica.LB.Helpers.DiceResources.getBlue(dice.getDie(2)));
		imgFireDie4.setImageResource(ica.LB.Helpers.DiceResources.getBlackWhite(dice.getDie(3)));
		imgFireDie5.setImageResource(ica.LB.Helpers.DiceResources.getBlackRed(dice.getDie(4)));
	}

	double getAttackerValue() {
        return Double.parseDouble(editAttackerValue.getText().toString());
	}
	double getDefenderValue() {
        return Double.parseDouble(editDefenderValue.getText().toString());
	}
	int getDefenderIncrements() {
        return Integer.parseInt(editDefenderIncr.getText().toString());
	}
	
	double findNearestDefenseValue(double value, boolean neg) {
		if (neg)  {
            for (int i=defenseValues.length-1; i>=0; i--) {
                if (defenseValues[i] <= value) {
                    return defenseValues[i];
                }
            }
            return defenseValues[0];
		}
		
        for (int i=0; i<defenseValues.length; i++) {
            if (defenseValues[i] >= value) {
                return defenseValues[i];
            }
        }
        return defenseValues[defenseValues.length-1];
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