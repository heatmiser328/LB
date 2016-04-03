package ica.LB.Core;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.*;
import java.util.*;

public class BattleRepository {

    public static ArrayList<Battle> read(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readBattles(reader);
        }
        finally {
            reader.close();
        }
    }
    private static ArrayList<Battle> readBattles(JsonReader reader) throws IOException {
        ArrayList<Battle> battles = new ArrayList<Battle>();

        reader.beginArray();
        while (reader.hasNext()) {
            battles.add(readBattle(reader));
        }
        reader.endArray();
        return battles;
    }

    private static Battle readBattle(JsonReader reader) throws IOException {
        Battle battle = new Battle();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                battle.setId(reader.nextInt());
            } 
            else if (name.equals("name")) {
                battle.setName(reader.nextString());
            }                
            else if (name.equals("publisher")) {
                battle.setPublisher(reader.nextString());
            }                
            else if (name.equals("image")) {
                battle.setImage(reader.nextString());
            }                
            else if (name.equals("scenarios") && reader.peek() != JsonToken.NULL) {
                battle.setScenarios(readScenarios(reader));
            } 
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return battle;
    }
    
    
    private static ArrayList<Scenario> readScenarios(JsonReader reader) throws IOException {
        ArrayList<Scenario> l = new ArrayList<Scenario>();
        
        reader.beginArray();
        while (reader.hasNext()) {
            l.add(readScenario(reader));
        }            
        reader.endArray();
        return l;
    }
    private static Scenario readScenario(JsonReader reader) throws IOException {
        Scenario s = new Scenario();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                s.setId(reader.nextInt());
            }
            else if (name.equals("name")) {
                s.setName(reader.nextString());
            }
            else if (name.equals("start") && reader.peek() != JsonToken.NULL) {
                readStartDate(reader, s);
            } 
            else if (name.equals("end") && reader.peek() != JsonToken.NULL) {
                readEndDate(reader, s);
            } 
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return s;
    }
    
    private static void readStartDate(JsonReader reader, Scenario s) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("year")) {
                s.setStartYear(reader.nextInt());
            }
            else if (name.equals("month")) {
                s.setStartMonth(reader.nextInt());
            }
            else if (name.equals("day")) {
                s.setStartDay(reader.nextInt());
            }
            else if (name.equals("hour")) {
                s.setStartHour(reader.nextInt());
            }
            else if (name.equals("minute")) {
                s.setStartMinute(reader.nextInt());
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }
    
    private static void readEndDate(JsonReader reader, Scenario s) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("year")) {
                s.setEndYear(reader.nextInt());
            }
            else if (name.equals("month")) {
                s.setEndMonth(reader.nextInt());
            }
            else if (name.equals("day")) {
                s.setEndDay(reader.nextInt());
            }
            else if (name.equals("hour")) {
                s.setEndHour(reader.nextInt());
            }
            else if (name.equals("minute")) {
                s.setEndMinute(reader.nextInt());
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }
}
