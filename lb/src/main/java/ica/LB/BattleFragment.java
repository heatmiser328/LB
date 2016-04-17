package ica.LB;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.widget.*;

import ica.LB.Adapters.TabsPagerAdapter;
import ica.LB.Core.*;

/**
 * Created by jcapuano on 5/18/2014.
 */
public class BattleFragment extends Fragment {
    private View rootView;
    private ImageView imgGame;
    private TextView txtTurn;
    private Button btnTurnPrev;
    private Button btnTurnNext;
    private TextView txtPhase;
    private Button btnPhasePrev;
    private Button btnPhaseNext;
    private ImageButton btnPlayer;

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;

    private GestureDetector turnGestDetector;
    private GestureDetector phaseGestDetector;

    private Game game;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        load(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        load(savedInstanceState);
        if (rootView != null) {
            final ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup)
                ((ViewGroup) parent).removeView(rootView);
        }
        else {
            rootView = inflater.inflate(R.layout.battle, container, false);
        }

        imgGame = (ImageView)rootView.findViewById(R.id.imageBattle);

        // current turn
        txtTurn = (TextView)rootView.findViewById(R.id.textTurn);
        btnTurnPrev = (Button)rootView.findViewById(R.id.btnTurnPrev);
        btnTurnNext = (Button)rootView.findViewById(R.id.btnTurnNext);

        // current phase
        txtPhase = (TextView)rootView.findViewById(R.id.textPhase);
        btnPhasePrev = (Button)rootView.findViewById(R.id.btnPhasePrev);
        btnPhaseNext = (Button)rootView.findViewById(R.id.btnPhaseNext);

        // current player
        btnPlayer = (ImageButton)rootView.findViewById(R.id.btnCurrentPlayer);

        // tabs
        viewPager = (ViewPager) rootView.findViewById(R.id.battleViews);
        mAdapter = new TabsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);

        btnTurnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                prevTurn();
            }
        });

        btnTurnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                nextTurn();
            }
        });

        btnPhasePrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                prevPhase();
            }
        });

        btnPhaseNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                nextPhase();
            }
        });

        btnPlayer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                nextPlayer();
            }
        });

        // swipe to change turns
        turnGestDetector = new GestureDetector(getContext(), new SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("Turn Gesture", "Scroll");
                return false;
            }
            @Override
            public boolean onDown(MotionEvent e1) {
                Log.d("Turn Gesture", "Down");
                return false;
            }
            @Override
            public void onLongPress(MotionEvent arg0) {
                Log.d("Turn Gesture", "LongPress");
            }
            @Override
            public void onShowPress(MotionEvent arg0) {
                Log.d("Turn Gesture", "ShowPress");
            }
            @Override
            public boolean onSingleTapUp(MotionEvent arg0) {
                Log.d("Turn Gesture", "SingleTapUp");
                return false;
            }

            @Override
            public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX, float velocityY) {
                Log.d("Turn Gesture", "Fling");
                if (start.getRawX() < finish.getRawX()) {
                    Log.d("Turn Fling", "next turn");
                    nextTurn();
                }
                else {
                    Log.d("Turn Fling", "prev turn");
                    prevTurn();
                }

                return true;
            }
        });
        /*
		txtTurn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return turnGestDetector.onTouchEvent(event);
            }
        });
        */

        // swipe to change phases
        phaseGestDetector = new GestureDetector(getContext(), new SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("Phase Gesture", "Scroll");
                return false;
            }
            @Override
            public boolean onDown(MotionEvent e1) {
                Log.d("Phase Gesture", "Down");
                return false;
            }
            @Override
            public void onLongPress(MotionEvent arg0) {
                Log.d("Phase Gesture", "LongPress");
            }
            @Override
            public void onShowPress(MotionEvent arg0) {
                Log.d("Phase Gesture", "ShowPress");
            }
            @Override
            public boolean onSingleTapUp(MotionEvent arg0) {
                Log.d("Phase Gesture", "SingleTapUp");
                return false;
            }

            @Override
            public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX, float velocityY) {
                Log.d("Phase Gesture", "Fling");
                if (start.getRawX() < finish.getRawX()) {
                    Log.d("Phase Fling", "next phase");
                    nextPhase();
                }
                else {
                    Log.d("Phase Fling", "prev phase");
                    prevPhase();
                }

                return true;
            }
        });
        /*
		txtPhase.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return phaseGestDetector.onTouchEvent(event);
            }
        });
        */

        update();
        
        return rootView;
    }

    @Override
    public void onResume () {
        super.onResume();
    }

    private void prevTurn() {
        game.prevTurn();
        update();
        save();
    }
    private void nextTurn() {
        game.nextTurn();
        update();
        save();
    }

    private void prevPhase() {
        game.prevPhase();
        update();
        save();
    }
    private void nextPhase() {
        game.nextPhase();
        update();
        save();
    }

    private void nextPlayer() {
        game.nextPlayer();
        update();
        save();
    }
    private void update() {
        if (game != null) {
            imgGame.setBackgroundResource(getContext().getResources().getIdentifier("drawable/" + game.getBattle().getImage(), null, getContext().getPackageName()));
            txtTurn.setText(game.getCurrentTurn());
            txtPhase.setText(game.getCurrentPhase());
            if (game.getCurrentPlayer() == 0) {
                btnPlayer.setBackgroundResource(R.drawable.imperial);
            } else {
                btnPlayer.setBackgroundResource(R.drawable.coalition);
            }
        }
    }

    private void load(Bundle args) {
        if (args != null) {
            Game g = Lb.getGame(args.getInt("Battle"), args.getInt("Scenario"));
            if (g != null)
                game = g;
        }
    }
    private void save() {
        try {
            Lb.saveSaved(game);
        } catch(Exception e) {
        }
    }

    public void reset() {
        game.reset();
        update();
        save();
    }
}
