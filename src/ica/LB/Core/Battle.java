package ica.LB.Core;

import java.util.*;

/**
 * Created by jcapuano on 5/15/2014.
 */
public class Battle {
	private int id;
	private String name;
	private String publisher;
	private String image;
    private ArrayList<Scenario> scenarios;

	public Battle ()
	{
		scenarios = new ArrayList<Scenario>();
	}

	public int getId() {
        return id;
    }
	public void setId(int v) {
        id = v;
    }
    
	public String getName() {
        return name;
    }
	public void setName(String s) {
        name = s;
    }
    
	public String getPublisher() {
        return publisher;
    }
	public void setPublisher(String s) {
        publisher = s;
    }
    
	public String getImage() {
        return image;
    }
	public void setImage(String v) {
        image = v;
    }
    
	public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

	public Scenario getScenario(int id)
	{
		for (Scenario s : scenarios) 
		{
			if (s.getId() == id)
				return s;
		}
		return null;
	}

    public void setScenarios(ArrayList<Scenario> scenarios) {
        this.scenarios = scenarios;
    }
}
