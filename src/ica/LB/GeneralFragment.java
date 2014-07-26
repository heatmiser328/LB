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

import ica.LB.Core.*;
import com.ica.dice.*;

/**
 * Created by jcapuano on 5/24/2014.
 */
public class GeneralFragment extends Fragment {

    private View rootView;
    
	private ImageView imgGeneral2Die1;
	private ImageView imgGeneral2Die2;
	private Button btnGeneral2DiceRoll;

	private ImageView imgGeneral1Die1;
	private Button btnGeneral1DiceRoll;
	
	private Dice dice1;
	private Dice dice2;
	private PlayAudio audio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            
        if (rootView != null) {
            final ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup)
                ((ViewGroup) parent).removeView(rootView);  
        }
        else {
            dice1 = new Dice(1, 1, 6);
            dice2 = new Dice(2, 1, 6);
            audio = new PlayAudio (getActivity());

            rootView = inflater.inflate(R.layout.general, container, false);

		    imgGeneral2Die1 = (ImageView)rootView.findViewById (R.id.imgGeneral2Die1);
		    imgGeneral2Die2 = (ImageView)rootView.findViewById (R.id.imgGeneral2Die2);
		    btnGeneral2DiceRoll = (Button)rootView.findViewById(R.id.btnGeneral2DiceRoll);

		    imgGeneral1Die1 = (ImageView)rootView.findViewById (R.id.imgGeneral1Die1);
		    btnGeneral1DiceRoll = (Button)rootView.findViewById(R.id.btnGeneral1DiceRoll);
        
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
	
        return rootView;
	}

	void displayDice() {
        dice1.set(0, DieColor.WHITE_BLACK, imgGeneral2Die1);
		dice1.set(1, DieColor.RED_WHITE,   imgGeneral2Die2);
        dice2.set(2, DieColor.BLUE_WHITE,  imgGeneral1Die1);
	}
}