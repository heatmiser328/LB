package ica.LB;

import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.content.*;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
 * Created by jcapuano on 5/18/2014.
 */
public class FireCombatFragment extends Fragment {
    
    private View rootView;
    
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
	
	private Dice dice;
	private FireCombat fc;
	private Odds odds;
	private PlayAudio audio;

	private double[] defenseValues = new double[] {4,6,7,8,9,10,12,14,16,18};
	
    public FireCombatFragment() {
		dice = new Dice(5, 1, 6);
		fc = new FireCombat();
		odds = fc.getDefaultOdds();
		audio = new PlayAudio (getActivity());
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            final ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup)
                ((ViewGroup) parent).removeView(rootView);  
        }
        else {
            rootView = inflater.inflate(R.layout.firecombat, container, false);
		
		    // attacker
		    btnAttackerValuePrev = (Button)rootView.findViewById(R.id.btnFireAttackerPrev);
		    btnAttackerValueNext = (Button)rootView.findViewById(R.id.btnFireAttackerNext);
		    editAttackerValue = (EditText)rootView.findViewById(R.id.textFireAttackerValue);
		    btnAttackerMod13 = (ToggleButton)rootView.findViewById(R.id.btnFireAttacker13);
		    btnAttackerMod12 = (ToggleButton)rootView.findViewById(R.id.btnFireAttacker12);
		    btnAttackerMod32 = (ToggleButton)rootView.findViewById(R.id.btnFireAttacker32);
		    btnAttackerModCann = (ToggleButton)rootView.findViewById(R.id.btnFireAttackerCann);
		
		    // defender
		    btnDefenderValuePrev = (Button)rootView.findViewById(R.id.btnFireDefenderPrev);
		    btnDefenderValueNext = (Button)rootView.findViewById(R.id.btnFireDefenderNext);
		    editDefenderValue = (EditText)rootView.findViewById(R.id.textFireDefenderValue);
		
		    btnDefenderIncrPrev = (Button)rootView.findViewById(R.id.btnFireDefenderIncrPrev);
		    btnDefenderIncrNext = (Button)rootView.findViewById(R.id.btnFireDefenderIncrNext);
		    editDefenderIncr = (EditText)rootView.findViewById(R.id.textFireDefenderIncr);
		
		    editAttackerValue.setText("1");
		    editDefenderValue.setText("1");
		    editDefenderIncr.setText("1");
		
		    spinFireOdds = (Spinner)rootView.findViewById(R.id.spinFireOdds);
		
		    imgFireDie1 = (ImageView)rootView.findViewById(R.id.imgFireDie1);
		    imgFireDie2 = (ImageView)rootView.findViewById(R.id.imgFireDie2);
		    imgFireDie3 = (ImageView)rootView.findViewById(R.id.imgFireDie3);
		    imgFireDie4 = (ImageView)rootView.findViewById(R.id.imgFireDie4);
		    imgFireDie5 = (ImageView)rootView.findViewById(R.id.imgFireDie5);
		    btnFireDiceRoll = (Button)rootView.findViewById(R.id.btnFireDiceRoll);
		
		    // results
		    txtFireResults = (TextView)rootView.findViewById(R.id.txtFireResults);
		    imgFireLeaderLossSide = (ImageView)rootView.findViewById(R.id.imgFireLeaderLossSide);
		    txtFireLeaderLoss = (TextView)rootView.findViewById(R.id.txtFireLeaderLoss);
		    imgFireLeaderLoss = (ImageView)rootView.findViewById(R.id.imgFireLeaderLoss);
        
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
        
		    ArrayAdapter<String> adapter = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_dropdown_item, fc.getOddsList());
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
        
		return rootView;        
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
					
		LeaderLossResult ll = fc.resolveLeaderLoss(d, dice.getDie(2), dice.getDie(3), dice.getDie(4));
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
		imgFireDie1.setImageResource(DiceResources.getWhiteBlack(dice.getDie(0)));
		imgFireDie2.setImageResource(DiceResources.getRedWhite(dice.getDie(1)));
		imgFireDie3.setImageResource(DiceResources.getBlue(dice.getDie(2)));
		imgFireDie4.setImageResource(DiceResources.getBlackWhite(dice.getDie(3)));
		imgFireDie5.setImageResource(DiceResources.getBlackRed(dice.getDie(4)));
	}

	double getAttackerValue() {
        String v = editAttackerValue.getText().toString();
        if (!v.isEmpty())
            return Double.parseDouble(v);
        return 1;           
	}
	double getDefenderValue() {
        String v = editDefenderValue.getText().toString();
        if (!v.isEmpty())
            return Double.parseDouble(v);
        return 1;           
	}
	int getDefenderIncrements() {
        String v = editDefenderIncr.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
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
}