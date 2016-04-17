package com.ica.dice;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;


public class Dice {
	private ArrayList<Die> dice = new ArrayList<Die>();
    private Random random = new Random();

	public Dice(int numdice, int low, int high) {
		for (int i=0; i<numdice; i++)
			addDie(low, high);
	}

	public int getDie(int die) {
        Die d = getDieItem(die);
        if (d != null) 
            return d.getValue();
		return 0;
	}
	public void setDie(int die, int value) {
        Die d = getDieItem(die);
        if (d != null) 
            d.setValue(value);
	}
	public Die getDieItem(int die) {
		if (die >= 0 && die < dice.size())
			return dice.get(die); 
		return null;
	}
    
	public int getSize() {
        return dice.size();
	}

	public void addDie(int rangelow, int rangehigh)
	{
		dice.add(new Die(rangelow, rangehigh, random));
	}
	public void removeDie(int die)
	{
		if (die >= 0 && die < dice.size())
			dice.remove(die);
	}
    
	public void clear() {
		dice.clear();
	}
    
	public int increment(int die) {
		Die d = getDieItem(die);
		if (d != null)
			return d.increment();
		return -1;		
	}

	public int decrement(int die) {
		Die d = getDieItem(die);
		if (d != null)
			return d.decrement();
		return -1;		
	}
	
	public void roll() {
		for (Die die : dice)
			die.roll();
	}
    
    public void set(int die, DieColor color, ImageView view) {
        int res = DiceResource.getDieResource(color, getDie(die));
        view.setImageResource(res);
    }
    public void set(int die, TextView view) {
        view.setText(Integer.toString(getDie(die)));
    }

    public void set(int die, Polygon view) {
        view.setText(Integer.toString(getDie(die)));
    }
}
