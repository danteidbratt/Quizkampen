package quizkampen;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Music {
	public static void playSound(){
        Media m = new Media("bensound-epic.mp3");
        MediaPlayer player = new MediaPlayer(m);
        player.play();
    }
 }