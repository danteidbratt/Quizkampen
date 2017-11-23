package quizkampen;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader implements Serializable{

    int ronds;
    int questionsInRond;
    Properties p;

    public PropertiesReader() {
        p = new Properties();

        try {
            p.load(new FileInputStream("src/quizkampen/info.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRonds() {
        String in = p.getProperty("ronds", "2");
        ronds = Integer.parseInt(in);
        return ronds;
    }

    public int getQuestionsInRond() {
        String in = p.getProperty("questionsInRond", "2");
        questionsInRond = Integer.parseInt(in);
        return questionsInRond;
    }
}
