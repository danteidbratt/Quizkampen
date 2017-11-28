package quizkampen;

import java.io.File;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Client extends Application{
    Window w;
	
    public Client() {
        Window w = new Window();
        w.setFrame();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Music.playSound();
		File file = new File("bensound-epic.mp3");
		Media media = new Media(file.toURI().toString()); //replace /Movies/test.mp3 with your file
		MediaPlayer player = new MediaPlayer(media); 
		player.play();
   } 
    
    public static void main(String[] args) {
        Client c = new Client();
		launch();
    }
}
