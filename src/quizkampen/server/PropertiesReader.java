package quizkampen.server;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader implements Serializable{

    int ronds;
    int questionsInRond;
    int timerLength;
    Properties p;

    public PropertiesReader() {
        p = new Properties();

        try {
            p.load(new FileInputStream("src/quizkampen/server/info.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRonds() {
        String in = p.getProperty("ronds", "5");
        ronds = Integer.parseInt(in);
        return ronds;
    }

    public int getQuestionsInRond() {
        String in = p.getProperty("questionsInRond", "3");
        questionsInRond = Integer.parseInt(in);
        return questionsInRond;
    }
    
    public int getTimerLength() {
        String in = p.getProperty("timerLength", "10");
        timerLength = Integer.parseInt(in);
        return timerLength;
    }
}
