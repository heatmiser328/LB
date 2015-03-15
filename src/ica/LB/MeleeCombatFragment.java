package ica.LB;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.app.Fragment;
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

import ica.LB.MeleeCalcDialog.OnMeleeCalcFinishListener;
import ica.LB.Core.*;
import com.ica.dice.*;

/**
 * Created by jcapuano on 5/22/2014.
 */
public class MeleeCombatFragment extends Fragment {

    private View rootView;
	
	private Button btnMeleeAttackerPrev;
	private Button btnMeleeAttackerNext;
	private EditText editMeleeAttackerValue;
	private Button btnMeleeAttackerReset;
	
	private Button btnMeleeAdd;
    
	private Button btnMeleeDefenderPrev;
	private Button btnMeleeDefenderNext;
	private EditText editMeleeDefenderValue;
	private Button btnMeleeDefenderReset;
	
	private Spinner spinMeleeOdds;
	
	private ImageView imgMeleeDie1;
	private ImageView imgMeleeDie2;
	private ImageView imgMeleeDie3;
	private ImageView imgMeleeDie4;
	private ImageView imgMeleeDie5;
	private Button btnMeleeDiceRoll;
	
	private Button btnMeleeMinus6;
	private Button btnMeleeMinus3;
	private Button btnMeleeMinus1;
	private Button btnMeleePlus1;
	private Button btnMeleePlus3;
	private Button btnMeleePlus6;
    
	private TextView txtMeleeResults;
	private TextView txtMeleeLeaderLoss;
	private ImageView imgMeleeLeaderLossSide;
	private ImageView imgMeleeLeaderLoss;
	
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
		    btnMeleeAttackerReset  = (Button)rootView.findViewById(R.id.btnMeleeAttackerReset	);
		
		    btnMeleeAdd	           = (Button)rootView.findViewById(R.id.btnMeleeAdd		);
        
		    btnMeleeDefenderPrev   = (Button)rootView.findViewById(R.id.btnMeleeDefenderPrev	);
		    btnMeleeDefenderNext   = (Button)rootView.findViewById(R.id.btnMeleeDefenderNext	);
		    editMeleeDefenderValue = (EditText)rootView.findViewById(R.id.editMeleeDefenderValue);
		    btnMeleeDefenderReset  = (Button)rootView.findViewById(R.id.btnMeleeDefenderReset	);
		
		    spinMeleeOdds = (Spinner)rootView.findViewById(R.id.spinMeleeOdds);
		
		    imgMeleeDie1 = (ImageView)rootView.findViewById(R.id.imgMeleeDie1);
		    imgMeleeDie2 = (ImageView)rootView.findViewById(R.id.imgMeleeDie2);
		    imgMeleeDie3 = (ImageView)rootView.findViewById(R.id.imgMeleeDie3);
		    imgMeleeDie4 = (ImageView)rootView.findViewById(R.id.imgMeleeDie4);
		    imgMeleeDie5 = (ImageView)rootView.findViewById(R.id.imgMeleeDie5);
		    btnMeleeDiceRoll = (Button)rootView.findViewById(R.id.btnMeleeDiceRoll);
		
		    btnMeleeMinus6 = (Button)rootView.findViewById(R.id.btnMeleeMinus6);
		    btnMeleeMinus3 = (Button)rootView.findViewById(R.id.btnMeleeMinus3);
		    btnMeleeMinus1 = (Button)rootView.findViewById(R.id.btnMeleeMinus1);
		    btnMeleePlus1 = (Button)rootView.findViewById(R.id.btnMeleePlus1);
		    btnMeleePlus3 = (Button)rootView.findViewById(R.id.btnMeleePlus3);
		    btnMeleePlus6 = (Button)rootView.findViewById(R.id.btnMeleePlus6);
        
		    // results
		    txtMeleeResults = (TextView)rootView.findViewById(R.id.txtMeleeResults);
		    imgMeleeLeaderLossSide = (ImageView)rootView.findViewById(R.id.imgMeleeLeaderLossSide);
		    txtMeleeLeaderLoss = (TextView)rootView.findViewById(R.id.txtMeleeLeaderLoss);
		    imgMeleeLeaderLoss = (ImageView)rootView.findViewById(R.id.imgMeleeLeaderLoss);
        
		    editMeleeAttackerValue.setText("0");
		    editMeleeDefenderValue.setText("0");
        
        
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
            
		    btnMeleeAttackerReset.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        editMeleeAttackerValue.setText("0");
			        calcOdds();
			        updateResults();
			    }
		    });
            
            
		    btnMeleeAdd.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    MeleeCalcDialog dlg = new MeleeCalcDialog();
                    dlg.setFinishListener(new OnMeleeCalcFinishListener() {
                        @Override
                        public void onFinishDialog(Boolean attacker, Double v) {
                            if (attacker) {
                                addToAttacker(v);
                            }
                            else {
                                addToDefender(v);
                            }
                        }
                    });
                    dlg.show(fm, "dlg_melee_calc");
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
            
		    btnMeleeDefenderReset.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        editMeleeDefenderValue.setText("0");
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
                    dice.increment(0);
			        displayDice();
			        updateResults();
			    }
		    });
		    imgMeleeDie2.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
                    if (dice.getDie(1) == 6) {
                        dice.increment(0);
                    }
                    dice.increment(1);
			        displayDice();
			        updateResults();
			    }
		    });
		    imgMeleeDie3.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
                    dice.increment(2);
			        displayDice();
			        updateResults();
			    }
		    });
		    imgMeleeDie4.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
                    dice.increment(3);
			        displayDice();
			        updateResults();
			    }
		    });
		    imgMeleeDie5.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
                    dice.increment(4);
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
		
        
		    btnMeleeMinus6.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        modifyDice(-6);
			        displayDice();
			        updateResults();
			    }
		    });
		    btnMeleeMinus3.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        modifyDice(-3);
			        displayDice();
			        updateResults();
			    }
		    });
		    btnMeleeMinus1.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        modifyDice(-1);
			        displayDice();
			        updateResults();
			    }
		    });
		
		    btnMeleePlus6.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        modifyDice(6);
			        displayDice();
			        updateResults();
			    }
		    });
		    btnMeleePlus3.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        modifyDice(3);
			        displayDice();
			        updateResults();
			    }
		    });
		    btnMeleePlus1.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        modifyDice(1);
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
		odds = mc.calculate(getAttackerValue(), getDefenderValue());
		if (odds == null)
			odds = mc.getDefaultOdds();
		displayOdds();
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
        dice.set(0, DieColor.WHITE_BLACK, imgMeleeDie1);
		dice.set(1, DieColor.RED_WHITE,   imgMeleeDie2);
        dice.set(2, DieColor.BLUE_WHITE,  imgMeleeDie3);
        dice.set(3, DieColor.BLACK_WHITE, imgMeleeDie4);
        dice.set(4, DieColor.BLACK_RED,   imgMeleeDie5);
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
    
	void modifyDice(int mod) {
		Base6Value b6i = new Base6Value((dice.getDie(0)*10) + dice.getDie(1));
		int value = b6i.add(mod);
		dice.setDie(0, value / 10);
		dice.setDie(1, value % 10);
	}
 
    void addToAttacker(double v) {
        // add the value to the appropriate total
		double value = getAttackerValue();
        value += v;
		editMeleeAttackerValue.setText(Double.toString(value));
		calcOdds();
		updateResults();
    }
    
    void addToDefender(double v) {
        // add the value to the appropriate total
		double value = getDefenderValue();
        value += v;
		editMeleeDefenderValue.setText(Double.toString(value));
		calcOdds();
		updateResults();
    }
}