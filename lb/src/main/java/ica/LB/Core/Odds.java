package ica.LB.Core;

import java.util.*;

/**
 * Created by jcapuano on 5/16/2014.
 */
public class Odds {
    private double value;
    private String name;

	public Odds(double _value, String _name) {
		value = _value;
		name = _name;
	}
	
	public double getValue() {
        return value;
    }
	public void setValue(double v) {
        value = v;
    }
    
	public String getName() {
        return name;
    }
	public void setName(String s) {
        name = s;
    }
	
	public static Odds calculate(ArrayList<Odds> table, double att, double def, int shift)  {
		boolean attadv = (att >= def);
		Odds entry = null;
		double odds = (attadv == true) ? (att / def) : (def / att);
		double o = odds * (attadv ? 1 : -1);
		int index = -1;
		for (int i=0; i<table.size(); i++) {
	        Odds tableentry = table.get(i);
	        double value = tableentry.getValue();
	        double nextvalue = (i+1 < table.size()) ? table.get(i+1).getValue() : value;
			
			if ((i+1 == table.size() && o >= value) || 
				(i == 0 && o < value) ||
				(o >= value && o < nextvalue)) {
	            index = i;
				break;
			}
		}
	    
	    if (index > -1) {
	        index += shift;
	        if (index < 0) 
	            index = 0;
	        else if (index >= table.size()) 
	            index = table.size() - 1;
	        entry = table.get(index);
	    }
	    
	    return entry;
	}                

}
