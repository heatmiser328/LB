package ica.LB.Core;

import java.io.*;
import java.util.*;
import org.xmlpull.v1.*;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class BattleRepositoryXML {

    private static List<Battle> battles = new ArrayList<Battle> ();			

	public static String getDatabaseFilePath() {
        return "BattleDB.xml";
	}
	
	public static void load(InputStream strm) {
        BattleRepositoryXML.readXml (strm);
	}
	
	public static Battle getBattle(int id) {
		for (int t = 0; t<battles.size(); t++) {
			if (battles.get(t).getId() == id)
				return battles.get(t);
		}
		return new Battle();
	}
	
	public static List<Battle> getBattles () {
		return battles;
	}


    private static void readXml (InputStream strm) {
		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(strm, null);
            
            parseXML(parser);

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    }
    
	private static void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
        int eventType = parser.getEventType();
        Battle currentBattle = null;
        Scenario currentScenario = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	battles = new ArrayList<Battle>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("Battle")){
                        currentBattle = new Battle();
                        currentScenario = null;
                    }
                    else if (name.equals("Scenario")){
                        currentScenario = new Scenario();
                    }

                    else if (currentBattle != null && currentScenario == null) {
                        if (name.equals("Id")){
                            currentBattle.setId(Integer.parseInt(parser.nextText()));
                        } 
                        else if (name.equals("Name")){
                        	currentBattle.setName(parser.nextText());
                        } 
                        else if (name.equals("Publisher")){
                            currentBattle.setPublisher(parser.nextText());
                        } 
                        else if (name.equals("Sort")){
                            currentBattle.setSort(Integer.parseInt(parser.nextText()));
                        }  
                    }
                    
                    else if (currentBattle != null && currentScenario != null) {
                        if (name.equals("Id")) {
                            currentScenario.setId(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("Name")) {
                            currentScenario.setName(parser.nextText());
                        } else if (name.equals("StartYear")) {
                            currentScenario.setStartYear(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("StartMonth")) {
                            currentScenario.setStartMonth(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("StartDay")) {
                            currentScenario.setStartDay(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("StartHour")) {
                            currentScenario.setStartHour(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("StartMinute")) {
                            currentScenario.setStartMinute(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("EndYear")) {
                            currentScenario.setEndYear(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("EndMonth")) {
                            currentScenario.setEndMonth(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("EndDay")) {
                            currentScenario.setEndDay(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("EndHour")) {
                            currentScenario.setEndHour(Integer.parseInt(parser.nextText()));
                        } else if (name.equals("EndMinute")) {
                            currentScenario.setEndMinute(Integer.parseInt(parser.nextText()));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("Scenario") && currentBattle != null && currentScenario != null){
                        currentBattle.getScenarios().add(currentScenario);
                        currentScenario = null;
                    } 
                    else if (name.equalsIgnoreCase("Battle") && currentBattle != null){
                    	battles.add(currentBattle);
                        currentBattle = null;
                    } 
                    break;
            }
            eventType = parser.next();
        }
	}    
}