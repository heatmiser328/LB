package ica.LB.Core;

import java.text.*;
import java.util.Date;
/**
 * Created by jcapuano on 5/17/2014.
 */
public class Format {
    public static String ScenarioDate(Date st, Date et) {
    
        SimpleDateFormat dft = new SimpleDateFormat ("MMMM d, yyyy");
        SimpleDateFormat tft = new SimpleDateFormat ("kk:mm");

        return dft.format(st) + " " + tft.format(st) + " - " + tft.format(et);
    }        
    
    public static String TurnDate(Date dt) {
        SimpleDateFormat df = new SimpleDateFormat ("MMMM d, yyyy HH:mm");

        return df.format(dt);
    }        
}
