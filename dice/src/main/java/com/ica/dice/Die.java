package com.ica.dice;

import java.util.Random;

public class Die {

	private int rangelow;
	private int rangehigh;
	private int value;
    private Random random;

	public Die(int rangelo, int rangehi, Random rnd) {
		rangelow = rangelo;
		rangehigh = rangehi;
		value = rangelow;
        random = rnd;
	}
    public Die(int rangelo, int rangehi)
    {
        this(rangelo, rangehi, new Random());
    }

	public int getRangeLow() {
        return rangelow;
	}
	public void setRangeLow(int i) {
        rangelow = i;
	}

	public int getRangeHigh() {
        return rangehigh;
	}
	public void setRangeHigh(int i) {
        rangehigh = i;
	}

	public int getValue() {
        return value;
	}
	public void setValue(int i) {
        value = i;
	}
	
	public int increment() {
		return increment(true);
	}
	public int increment(boolean rollover) {
        if (++value > rangehigh) {
            value = rollover ? rangelow : rangehigh;
        }
		return value;
	}
	
	public int decrement() {
		return decrement(true);
	}
	public int decrement(boolean rollover) {
        if (--value < rangelow) {
            value = rollover ? rangehigh : rangelow;
        }
		return value;
    }
	
	public int roll() {		        
        value = random.nextInt(rangehigh - rangelow) + rangelow;
		
		return value;
	}

}
