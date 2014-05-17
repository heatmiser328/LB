package ica.LB.Core;

import java.util.*;
/**
 * Created by jcapuano on 5/15/2014.
 */
public class Scenario {

    private int id;
	private String name;
	private int startYear;
	private int startMonth;
	private int startDay;
	private int startHour;
	private int startMinute;
	private int endYear;
	private int endMonth;
	private int endDay;
	private int endHour;
	private int endMinute;
    
    public Scenario() {
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
	public void setName(String n) {
        name = n;
    }
    
	public int getStartYear() {
        return startYear;
    }
	public void setStartYear(int v) {
        startYear = v;
    }
    
	public int getStartMonth() {
        return startMonth;
    }
	public void setStartMonth(int v) {
        startMonth = v;
    }
    
	public int getStartDay() {
        return startDay;
    }
	public void setStartDay(int v) {
        startDay = v;
    }
    
	public int getStartHour() {
        return startHour;
    }
	public void setStartHour(int v) {
        startHour = v;
    }
    
	public int getStartMinute() {
        return startMinute;
    }
	public void setStartMinute(int v) {
        startMinute = v;
    }
    
	public int getEndYear() {
        return endYear;
    }
	public void setEndYear(int v) {
        endYear = v;
    }
    
	public int getEndMonth() {
        return endMonth;
    }
	public void setEndMonth(int v) {
        endMonth = v;
    }
    
	public int getEndDay() {
        return endDay;
    }
	public void setEndDay(int v) {
        endDay = v;
    }
    
	public int getEndHour() {
        return endHour;
    }
	public void setEndHour(int v) {
        endHour = v;
    }
    
	public int getEndMinute() {
        return endMinute;
    }
	public void setEndMinute(int v) {
        endMinute = v;
    }
    
    @Override
    public String toString() {
        Calendar sc = Calendar.getInstance();
        sc.set(startYear, startMonth - 1, startDay, startHour, startMinute);
        
        Calendar ec = Calendar.getInstance();
        ec.set(endYear, endMonth - 1, endDay, endHour, endMinute);
        
        return Format.ScenarioDate(sc.getTime(), ec.getTime());
    }
    
}
