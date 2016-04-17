package ica.LB.Core;

import android.util.JsonReader;
import android.util.JsonWriter;
import java.io.*;
/**
 * Created by jcapuano on 5/31/2014.
 */
public class SavedRepository {

    public static Saved read(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readSaved(reader);
        }
        finally {
            reader.close();
        }
    }
    private static Saved readSaved(JsonReader reader) throws IOException {
        Saved saved = new Saved();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("battle")) {
                saved.setBattle(reader.nextInt());
            } 
            else if (name.equals("scenario")) {
                saved.setScenario(reader.nextInt());
            } 
            else if (name.equals("turn")) {
                saved.setTurn(reader.nextInt());
            } 
            else if (name.equals("phase")) {
                saved.setPhase(reader.nextInt());
            }
            else if (name.equals("player")) {
                saved.setPlayer(reader.nextInt());
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return saved;
    }

    public static void write(OutputStream out, Saved saved) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        try {
            writeSaved(writer, saved);
        }
        finally {
            writer.close();
        }
    }
    private static void writeSaved(JsonWriter writer, Saved saved) throws IOException {
        writer.beginObject();
        writer.name("battle").value(saved.getBattle());
        writer.name("scenario").value(saved.getScenario());
        writer.name("turn").value(saved.getTurn());
        writer.name("phase").value(saved.getPhase());
        writer.name("player").value(saved.getPlayer());
        writer.endObject();
    }    
}
