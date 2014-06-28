package ica.LB;

import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.content.*;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import ica.LB.Core.*;
import ica.LB.Helpers.*;

/**
 * Created by jcapuano on 5/22/2014.
 */
public class MeleeCombatFragment extends Fragment {

    private View rootView;
	
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
	
	private Dice dice;
	private MeleeCombat mc;
	private Odds odds;
	private PlayAudio audio;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            final ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup)
                ((ViewGroup) parent).removeView(rootView);  
        }
        else {
            dice = new Dice(5, 1, 6);
            mc = new MeleeCombat();
            odds = mc.getDefaultOdds();
            audio = new PlayAudio (getActivity());

            rootView = inflater.inflate(R.layout.meleecombat, container, false);
    
		    btnMeleeAttackerPrev   = (Button)rootView.findViewById(R.id.btnMeleeAttackerPrev	);
		    btnMeleeAttackerNext   = (Button)rootView.findViewById(R.id.btnMeleeAttackerNext	);
		    editMeleeAttackerValue = (EditText)rootView.findViewById(R.id.editMeleeAttackerValue);
		    btnMeleeAttackerAdd	   = (Button)rootView.findViewById(R.id.btnMeleeAttackerAdd		);
		    btnMeleeAttackerReset  = (Button)rootView.findViewById(R.id.btnMeleeAttackerReset	);
		
		    btnMeleeDefenderPrev   = (Button)rootView.findViewById(R.id.btnMeleeDefenderPrev	);
		    btnMeleeDefenderNext   = (Button)rootView.findViewById(R.id.btnMeleeDefenderNext	);
		    editMeleeDefenderValue = (EditText)rootView.findViewById(R.id.editMeleeDefenderValue);
		    btnMeleeDefenderAdd	   = (Button)rootView.findViewById(R.id.btnMeleeDefenderAdd		);
		    btnMeleeDefenderReset  = (Button)rootView.findViewById(R.id.btnMeleeDefenderReset	);
		
		    spinMeleeOdds = (Spinner)rootView.findViewById(R.id.spinMeleeOdds);
		
		    imgMeleeDie1 = (ImageView)rootView.findViewById(R.id.imgMeleeDie1);
		    imgMeleeDie2 = (ImageView)rootView.findViewById(R.id.imgMeleeDie2);
		    imgMeleeDie3 = (ImageView)rootView.findViewById(R.id.imgMeleeDie3);
		    imgMeleeDie4 = (ImageView)rootView.findViewById(R.id.imgMeleeDie4);
		    imgMeleeDie5 = (ImageView)rootView.findViewById(R.id.imgMeleeDie5);
		    btnMeleeDiceRoll = (Button)rootView.findViewById(R.id.btnMeleeDiceRoll);
		
		    // results
		    txtMeleeResults = (TextView)rootView.findViewById(R.id.txtMeleeResults);
		    imgMeleeLeaderLossSide = (ImageView)rootView.findViewById(R.id.imgMeleeLeaderLossSide);
		    txtMeleeLeaderLoss = (TextView)rootView.findViewById(R.id.txtMeleeLeaderLoss);
		    imgMeleeLeaderLoss = (ImageView)rootView.findViewById(R.id.imgMeleeLeaderLoss);
        
            // calc
		    btnMeleeIncrPrev	 = (Button)rootView.findViewById(R.id.btnMeleeIncrPrev	);
		    btnMeleeIncrNext	 = (Button)rootView.findViewById(R.id.btnMeleeIncrNext	);
		    editMeleeIncrValue	 = (EditText)rootView.findViewById(R.id.editMeleeIncrValue);
							
		    btnMeleeLossPrev	 = (Button)rootView.findViewById(R.id.btnMeleeLossPrev	);
		    btnMeleeLossNext	 = (Button)rootView.findViewById(R.id.btnMeleeLossNext	);
		    editMeleeLossValue	 = (EditText)rootView.findViewById(R.id.editMeleeLossValue);
		
		    btnMeleeValuePrev	 = (Button)rootView.findViewById(R.id.btnMeleeValuePrev	);
		    btnMeleeValueNext	 = (Button)rootView.findViewById(R.id.btnMeleeValueNext	);
		    editMeleeValueValue	 = (EditText)rootView.findViewById(R.id.editMeleeValueValue);
		
		    btnMeleeLancePrev	 = (Button)rootView.findViewById(R.id.btnMeleeLancePrev	);
		    btnMeleeLanceNext	 = (Button)rootView.findViewById(R.id.btnMeleeLanceNext	);
		    editMeleeLanceValue	 = (EditText)rootView.findViewById(R.id.editMeleeLanceValue);
		
		    btnMeleeTotalPrev	 = (Button)rootView.findViewById(R.id.btnMeleeTotalPrev	);
		    btnMeleeTotalNext	 = (Button)rootView.findViewById(R.id.btnMeleeTotalNext	);
		    editMeleeTotalValue	 = (EditText)rootView.findViewById(R.id.editMeleeTotalValue);
		
		    btnMeleeMods13  = (ToggleButton)rootView.findViewById(R.id.btnMeleeMods13 );
		    btnMeleeMods12  = (ToggleButton)rootView.findViewById(R.id.btnMeleeMods12 );
		    btnMeleeMods32  = (ToggleButton)rootView.findViewById(R.id.btnMeleeMods32 );
		    btnMeleeMods2   = (ToggleButton)rootView.findViewById(R.id.btnMeleeMods2  );
		    btnMeleeModsLnc = (ToggleButton)rootView.findViewById(R.id.btnMeleeModsLnc);
		
		    editMeleeAttackerValue.setText("1");
		    editMeleeDefenderValue.setText("1");
		    editMeleeIncrValue.setText("1");
		    editMeleeLossValue.setText("1");
		    editMeleeValueValue.setText("1");
		    editMeleeLanceValue.setText("1");
		    editMeleeTotalValue.setText("1");
        
        
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
			
		    ArrayAdapter<String> adapter = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_dropdown_item, mc.getOddsList());
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

        return rootView;
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
					
		LeaderLossResult ll = mc.resolveLeaderLoss(d, dice.getDie(2), dice.getDie(3), dice.getDie(4));
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
		imgMeleeDie1.setImageResource(DiceResources.getWhiteBlack(dice.getDie(0)));
		imgMeleeDie2.setImageResource(DiceResources.getRedWhite(dice.getDie(1)));
		imgMeleeDie3.setImageResource(DiceResources.getBlue(dice.getDie(2)));
		imgMeleeDie4.setImageResource(DiceResources.getBlackWhite(dice.getDie(3)));
		imgMeleeDie5.setImageResource(DiceResources.getBlackRed(dice.getDie(4)));
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
}