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
 * Created by jcapuano on 5/22/2014.
 */
public class MeleeCombatActivity extends Activity {
    
	private TextView txtBattleName;
	private TextView txtScenarioName;
	private ImageView imgBack;
	private ImageView imgLb;
	
	private Button btnMeleeAttackerPrev;
	private Button btnMeleeAttackerNext;
	private EditText editMeleeAttackerValue;
	private Button btnMeleeAttackerAdd;
	private Button btnMeleeAttackerReset;
	
	private Button btnMeleeDefenderPrev;
	private Button btnMeleeDefenderNext;
	private EditText editMeleeDefenderValue;
	private Button btnMeleeDefenderAdd;
	private Button btnMeleeDefenderReset;
	
	private Spinner spinMeleeOdds;
	
	private ImageView imgMeleeDie1;
	private ImageView imgMeleeDie2;
	private ImageView imgMeleeDie3;
	private ImageView imgMeleeDie4;
	private ImageView imgMeleeDie5;
	private Button btnMeleeDiceRoll;
	
	private TextView txtMeleeResults;
	private TextView txtMeleeLeaderLoss;
	private ImageView imgMeleeLeaderLossSide;
	private ImageView imgMeleeLeaderLoss;
	
	private Button btnMeleeIncrPrev;
	private Button btnMeleeIncrNext;
	private EditText editMeleeIncrValue;
	
	private Button btnMeleeLossPrev;
	private Button btnMeleeLossNext;
	private EditText editMeleeLossValue;
	
	private Button btnMeleeValuePrev;
	private Button btnMeleeValueNext;
	private EditText editMeleeValueValue;
	
	private Button btnMeleeLancePrev;
	private Button btnMeleeLanceNext;
	private EditText editMeleeLanceValue;
	
	private Button btnMeleeTotalPrev;
	private Button btnMeleeTotalNext;
	private EditText editMeleeTotalValue;
	
	private ToggleButton btnMeleeMods13;
	private ToggleButton btnMeleeMods12;
	private ToggleButton btnMeleeMods32;
	private ToggleButton btnMeleeMods2;
	private ToggleButton btnMeleeModsLnc;
	
	private ica.LB.Core.Game game;
	private ica.LB.Core.Dice dice;
	private ica.LB.Core.MeleeCombat mc;
	private ica.LB.Core.Odds odds;
	private ica.LB.Helpers.PlayAudio audio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
		dice = new ica.LB.Core.Dice(5, 1, 6);
		mc = new ica.LB.Core.MeleeCombat();
		odds = mc.getDefaultOdds();
		audio = new ica.LB.Helpers.PlayAudio (this);
	
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        
		game = ica.LB.Core.LbManager.getGame(intent.getIntExtra ("Battle", -1), intent.getIntExtra("Scenario", -1));

		// set our layout to be the home screen
        setContentView(R.layout.meleecombat);

		imgBack = (ImageView)findViewById(R.id.titleSubLbBack);
		imgLb = (ImageView)findViewById(R.id.titleSubLb);

		// title
		txtBattleName = (TextView)findViewById(R.id.titleSubBattleName);
		txtScenarioName = (TextView)findViewById(R.id.titleSubScenarioName);

		
		btnMeleeAttackerPrev   = (Button)findViewById(R.id.btnMeleeAttackerPrev	);
		btnMeleeAttackerNext   = (Button)findViewById(R.id.btnMeleeAttackerNext	);
		editMeleeAttackerValue = (EditText)findViewById(R.id.editMeleeAttackerValue);
		btnMeleeAttackerAdd	   = (Button)findViewById(R.id.btnMeleeAttackerAdd		);
		btnMeleeAttackerReset  = (Button)findViewById(R.id.btnMeleeAttackerReset	);
		
		btnMeleeDefenderPrev   = (Button)findViewById(R.id.btnMeleeDefenderPrev	);
		btnMeleeAttackerNext   = (Button)findViewById(R.id.btnMeleeAttackerNext	);
		editMeleeDefenderValue = (EditText)findViewById(R.id.editMeleeDefenderValue);
		btnMeleeDefenderAdd	   = (Button)findViewById(R.id.btnMeleeDefenderAdd		);
		btnMeleeDefenderReset  = (Button)findViewById(R.id.btnMeleeDefenderReset	);
		
		spinMeleeOdds = (Spinner)findViewById(R.id.spinMeleeOdds);
		
		imgMeleeDie1 = (ImageView)findViewById(R.id.imgMeleeDie1);
		imgMeleeDie2 = (ImageView)findViewById(R.id.imgMeleeDie2);
		imgMeleeDie3 = (ImageView)findViewById(R.id.imgMeleeDie3);
		imgMeleeDie4 = (ImageView)findViewById(R.id.imgMeleeDie4);
		imgMeleeDie5 = (ImageView)findViewById(R.id.imgMeleeDie5);
		btnMeleeDiceRoll = (Button)findViewById(R.id.btnMeleeDiceRoll);
		
		// results
		txtMeleeResults = (TextView)findViewById(R.id.txtMeleeResults);
		imgMeleeLeaderLossSide = (ImageView)findViewById(R.id.imgMeleeLeaderLossSide);
		txtMeleeLeaderLoss = (TextView)findViewById(R.id.txtMeleeLeaderLoss);
		imgMeleeLeaderLoss = (ImageView)findViewById(R.id.imgMeleeLeaderLoss);
        
        // calc
		btnMeleeIncrPrev	 = (Button)findViewById(R.id.btnMeleeIncrPrev	);
		btnMeleeIncrNext	 = (Button)findViewById(R.id.btnMeleeIncrNext	);
		editMeleeIncrValue	 = (EditText)findViewById(R.id.editMeleeIncrValue);
							
		btnMeleeLossPrev	 = (Button)findViewById(R.id.btnMeleeLossPrev	);
		btnMeleeLossNext	 = (Button)findViewById(R.id.btnMeleeLossNext	);
		editMeleeLossValue	 = (EditText)findViewById(R.id.editMeleeLossValue);
		
		btnMeleeValuePrev	 = (Button)findViewById(R.id.btnMeleeValuePrev	);
		btnMeleeValueNext	 = (Button)findViewById(R.id.btnMeleeValueNext	);
		editMeleeValueValue	 = (EditText)findViewById(R.id.editMeleeValueValue);
		
		btnMeleeLancePrev	 = (Button)findViewById(R.id.btnMeleeLancePrev	);
		btnMeleeLanceNext	 = (Button)findViewById(R.id.btnMeleeLanceNext	);
		editMeleeLanceValue	 = (EditText)findViewById(R.id.editMeleeLanceValue);
		
		btnMeleeTotalPrev	 = (Button)findViewById(R.id.btnMeleeTotalPrev	);
		btnMeleeTotalNext	 = (Button)findViewById(R.id.btnMeleeTotalNext	);
		editMeleeTotalValue	 = (EditText)findViewById(R.id.editMeleeTotalValue);
		
		btnMeleeMods13  = (ToggleButton)findViewById(R.id.btnMeleeMods13 );
		btnMeleeMods12  = (ToggleButton)findViewById(R.id.btnMeleeMods12 );
		btnMeleeMods32  = (ToggleButton)findViewById(R.id.btnMeleeMods32 );
		btnMeleeMods2   = (ToggleButton)findViewById(R.id.btnMeleeMods2  );
		btnMeleeModsLnc = (ToggleButton)findViewById(R.id.btnMeleeModsLnc);
		
		editMeleeAttackerValue.setText("1");
		editMeleeDefenderValue.setText("1");
		editMeleeIncrValue.setText("1");
		editMeleeLossValue.setText("1");
		editMeleeValueValue.setText("1");
		editMeleeLanceValue.setText("1");
		editMeleeTotalValue.setText("1");
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
		btnMeleeAttackerPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getAttackerValue();
			    if (--value < 1) value = 1;
			    editMeleeAttackerValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
		btnMeleeAttackerNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getAttackerValue();
			    editMeleeAttackerValue.setText(Double.toString(++value));
			    calcOdds();
			    updateResults();
			}
		});
		editMeleeAttackerValue.addTextChangedListener(new TextWatcher() {
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
		// defender
		btnMeleeDefenderPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getDefenderValue();
			    if (--value < 1) value = 1;
			    editMeleeDefenderValue.setText(Double.toString(value));
			    calcOdds();
			    updateResults();
			}
		});
		btnMeleeDefenderNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getDefenderValue();
			    editMeleeDefenderValue.setText(Double.toString(++value));
			    calcOdds();
			    updateResults();
			}
		});
		editMeleeDefenderValue.addTextChangedListener(new TextWatcher() {
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
		
		spinMeleeOdds.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			    odds = mc.getOddsItem(pos);
			    updateResults();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
			
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, mc.getOddsList());
		adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
		spinMeleeOdds.setAdapter(adapter);
		
		imgMeleeDie1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(1);
			    displayDice();
			    updateResults();
			}
		});
		imgMeleeDie2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(2);
			    displayDice();
			    updateResults();
			}
		});
		imgMeleeDie3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(3);
			    displayDice();
			    updateResults();
			}
		});
		imgMeleeDie4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(4);
			    displayDice();
			    updateResults();
			}
		});
		imgMeleeDie5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    incrementDie(5);
			    displayDice();
			    updateResults();
			}
		});
		
		btnMeleeDiceRoll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    audio.play();
			    dice.roll();
			    displayDice();
			    updateResults();
			}
		});
            
		
		btnMeleeIncrPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeIncrements();
			    if (--value < 1) value = 1;
			    editMeleeIncrValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeIncrNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeIncrements();
			    editMeleeIncrValue.setText(Integer.toString(++value));
                updateUnit();
			}
		});
		editMeleeIncrValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
            public void afterTextChanged(Editable s) {
			    updateUnit();
            }
        });
		
		btnMeleeLossPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeLoss();
			    if (--value < 1) value = 1;
			    editMeleeLossValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeLossNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeLoss();
			    editMeleeLossValue.setText(Integer.toString(++value));
                updateUnit();
			}
		});
		editMeleeLossValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
            public void afterTextChanged(Editable s) {
			    updateUnit();
            }
        });
		
		btnMeleeValuePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeValue();
			    if (--value < 1) value = 1;
			    editMeleeValueValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeValueNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeValue();
			    editMeleeValueValue.setText(Integer.toString(++value));
                updateUnit();
			}
		});
		editMeleeValueValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
            public void afterTextChanged(Editable s) {
			    updateUnit();
            }
        });
		
		btnMeleeLancePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeLance();
			    if (--value < 1) value = 1;
			    editMeleeLanceValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeLanceNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeLance();
			    editMeleeLanceValue.setText(Integer.toString(++value));
                updateUnit();
			}
		});
		editMeleeLanceValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
            public void afterTextChanged(Editable s) {
			    updateUnit();
            }
        });
		
		btnMeleeTotalPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    if (--value < 1) value = 1;
			    editMeleeTotalValue.setText(Double.toString(value));
			}
		});
		btnMeleeTotalNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    editMeleeTotalValue.setText(Double.toString(++value));
			}
		});
        /*
		editMeleeTotalValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
		*/
		
		btnMeleeMods13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    if (btnMeleeMods13.isChecked())  {
				    value /= 3;
			    }
			    else {
				    value *= 3;
			    }
			    editMeleeTotalValue.setText(Double.toString(value));
			}
		});
		btnMeleeMods12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    if (btnMeleeMods12.isChecked()) {
				    value /= 2;
			    }
			    else {
				    value *= 2;
			    }
			    editMeleeTotalValue.setText(Double.toString(value));
			}
		});
		btnMeleeMods32.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    if (btnMeleeMods32.isChecked()) {
				    value *= 1.5;
			    }
			    else {
				    value /= 1.5;
			    }
			    editMeleeTotalValue.setText(Double.toString(value));
			}
		});
		btnMeleeMods2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    if (btnMeleeMods2.isChecked()) {
				    value *= 2;
			    }
			    else {
				    value /= 2;
			    }
			    editMeleeTotalValue.setText(Double.toString(value));
			}
		});
		
		btnMeleeModsLnc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeLance();
			    if (btnMeleeModsLnc.isChecked()) {
				    value *= 2;
			    }
			    else {
				    value /= 2;
			    }
			    editMeleeLanceValue.setText(Integer.toString(value));
			    updateUnit();
			}
		});

		displayOdds();
		displayDice();
	}
	
	void calcOdds() {
		odds = mc.calculate(getAttackerValue(), getDefenderValue());
		if (odds == null)
			odds = mc.getDefaultOdds();
		displayOdds();
	}
	
	void updateUnit() {
        int incr = getMeleeIncrements();
	    int loss = getMeleeLoss();
		double melee = getMeleeValue();
		double lance = getMeleeLance();
        
		double val = melee * ((double)(incr - loss) / (double)incr);
		if (btnMeleeMods13.isChecked()) 
	        val /= 3.0;
		if (btnMeleeMods12.isChecked()) 
	        val /= 2.0;
		if (btnMeleeMods32.isChecked()) 
	        val *= 1.5;
		if (btnMeleeMods2.isChecked()) 
	        val *= 2.0;
        
		if (btnMeleeModsLnc.isChecked()) 
            lance *= 2.0;
        
		editMeleeTotalValue.setText(Double.toString(val + lance));
	}
	
	void updateResults() {
		int d = (dice.getDie(0)*10) + dice.getDie(1);
		String result = mc.resolve(odds, d);
		txtMeleeResults.setText(result, TextView.BufferType.NORMAL);
					
		ica.LB.Core.LeaderLossResult ll = mc.resolveLeaderLoss(d, dice.getDie(2), dice.getDie(3), dice.getDie(4));
		imgMeleeLeaderLossSide.setVisibility((ll.getKilled() || ll.getWounded() || ll.getCaptured()) ? View.VISIBLE : View.INVISIBLE);
		imgMeleeLeaderLossSide.setImageResource (ll.getAttacker() ? R.drawable.attacker : R.drawable.defender);
		txtMeleeLeaderLoss.setVisibility((ll.getKilled() || ll.getWounded() || ll.getCaptured()) ? View.VISIBLE : View.INVISIBLE);
		String loss = ll.getInjury() + (ll.getWounded() ? (" " + Integer.toString(ll.getDuration()) + " hours") : "");
		txtMeleeLeaderLoss.setText(loss, TextView.BufferType.NORMAL);

		imgMeleeLeaderLoss.setVisibility((ll.getKilled() || ll.getWounded() || ll.getCaptured()) ? View.VISIBLE : View.INVISIBLE);
		imgMeleeLeaderLoss.setImageResource(ll.getCaptured() ? R.drawable.capture : (ll.getWounded() ? R.drawable.ambulance : R.drawable.tombstone));
	}
	
	void displayOdds() {
		spinMeleeOdds.setSelection(mc.getOddsIndex(odds.getName()));
	}

	void displayDice() {
		imgMeleeDie1.setImageResource(ica.LB.Helpers.DiceResources.getWhiteBlack(dice.getDie(0)));
		imgMeleeDie2.setImageResource(ica.LB.Helpers.DiceResources.getRedWhite(dice.getDie(1)));
		imgMeleeDie3.setImageResource(ica.LB.Helpers.DiceResources.getBlue(dice.getDie(2)));
		imgMeleeDie4.setImageResource(ica.LB.Helpers.DiceResources.getBlackWhite(dice.getDie(3)));
		imgMeleeDie5.setImageResource(ica.LB.Helpers.DiceResources.getBlackRed(dice.getDie(4)));
	}

	double getAttackerValue() {
        String v = editMeleeAttackerValue.getText().toString();
        if (!v.isEmpty())
            return Double.parseDouble(v);
        return 1;           
	}
	double getDefenderValue() {
        String v = editMeleeDefenderValue.getText().toString();
        if (!v.isEmpty())
            return Double.parseDouble(v);
        return 1;           
	}
	int getMeleeIncrements() {
        String v = editMeleeIncrValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	int getMeleeLoss() {
        String v = editMeleeLossValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	int getMeleeValue() {
        String v = editMeleeValueValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	int getMeleeLance() {
        String v = editMeleeLanceValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	double getMeleeTotal() {
        String v = editMeleeTotalValue.getText().toString();
        if (!v.isEmpty())
            return Double.parseDouble(v);
        return 1;           
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