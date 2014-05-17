package ica.LB.Core;

import java.text.*;
import java.util.Date;
/**
 * Created by jcapuano on 5/17/2014.
 */
public class Format {
    public static String ScenarioDate(Date st, Date et) {
    
        SimpleDateFormat dft = new SimpleDateFormat ("MMM d, Y");
        SimpleDateFormat tft = new SimpleDateFormat ("H:M");

        return dft.format(st + " " + tft.format(st) + " - " + tft.format(et));
    }        
    
    public static String TurnDate(Date dt) {
        SimpleDateFormat df = new SimpleDateFormat ("MMM d, Y H:M P");

        return df.format(dt);
    }        
}
