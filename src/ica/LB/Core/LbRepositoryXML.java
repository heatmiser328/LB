package ica.LB.Core;

import android.content.Context;
import android.util.Xml;
import java.io.*;
import org.xmlpull.v1.*;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class LbRepositoryXML {
    private static Context context;
    private static Lb lb = null;

	public static String getDatabaseFile() {
        return "LbDB.xml";
	}
	
	public static void initialize(Context ctx) {
        context = ctx;
	}
    
	public static Lb getLb() {
		if (lb == null) {
			lb = new Lb();
            try {
                LbRepositoryXML.readXml(context.openFileInput(LbRepositoryXML.getDatabaseFile()));
            }
            catch (FileNotFoundException e) {
            }
		}
		return lb;
	}
	
	public static void saveLb(Lb saved) throws FileNotFoundException, IOException {
		if (saved != null)
			lb = saved;
        LbRepositoryXML.writeXml(context.openFileOutput(LbRepositoryXML.getDatabaseFile(), 0));
	}

    private static void readXml(FileInputStream strm) {
        
		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(strm, null);
            
            LbRepositoryXML.parseXML(parser);

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

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	lb = new Lb();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("Battle")){
                        lb.setBattle(Integer.parseInt(parser.nextText()));
                    } 
                    else if (name.equals("Scenario")){
                        lb.setScenario(Integer.parseInt(parser.nextText()));
                    } 
                    else if (name.equals("Turn")){
                        lb.setTurn(Integer.parseInt(parser.nextText()));
                    } 
                    else if (name.equals("Phase")){
                        lb.setPhase(Integer.parseInt(parser.nextText()));
                    } 
                    break;
            }
            eventType = parser.next();
        }
	}    
    
    private static void writeXml(FileOutputStream strm) throws IOException
    {
        String xml = LbRepositoryXML.serializeXml();
        strm.write(xml.getBytes());
        strm.close();
	}
    
    private static String serializeXml() throws IOException
    {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();

        xmlSerializer.setOutput(writer);
    //start Document
        xmlSerializer.startDocument("UTF-8",true);
        xmlSerializer.startTag("", "LB");
            xmlSerializer.startTag("", "Current");

                xmlSerializer.startTag("", "Battle");
                    xmlSerializer.text(Integer.toString(lb.getBattle()));
                xmlSerializer.endTag("", "Battle");

                xmlSerializer.startTag("", "Scenario");
                    xmlSerializer.text(Integer.toString(lb.getScenario()));
                xmlSerializer.endTag("", "Scenario");
                
                xmlSerializer.startTag("", "Turn");
                    xmlSerializer.text(Integer.toString(lb.getTurn()));
                xmlSerializer.endTag("", "Turn");
                
                xmlSerializer.startTag("", "Phase");
                    xmlSerializer.text(Integer.toString(lb.getPhase()));
                xmlSerializer.endTag("", "Phase");
                
            xmlSerializer.endTag("","Current");
        xmlSerializer.endTag("", "LB");

        xmlSerializer.endDocument();
        xmlSerializer.flush();

        return writer.toString();
    }
}
