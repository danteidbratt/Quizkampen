package quizkampen;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.swing.GroupLayout.Group;

public class Client extends Application{

	JFXPanel fxPanel = new JFXPanel();
    Window w;
    public Client() {
        Window w = new Window();
        w.setFrame();
//		fxPanel = new JFXPanel();
//		Platform.runLater(new Runnable() {
//			@Override public void run() {  
//				String bip = "fox.mp3";
//				MediaPlayer player;
//				Media audioFile = new Media("file:///Users/David/Desktop/bensound-extremeaction.mp3");
//				player = new MediaPlayer(audioFile);
//				player.play();
//				MediaView mediaView = new MediaView(player);
//        ((Group)scene.getRoot()).getChildren().add(mediaView);
//			}
//		});
    }

	
	  @Override
   public void start(Stage stage) throws Exception {
		Music.playSound();
       Media media = new Media("file:///Users/David/Desktop/bensound-extremeaction.mp3"); //replace /Movies/test.mp3 with your file
       MediaPlayer player = new MediaPlayer(media); 
       player.play();
   } 
    
    public static void main(String[] args) {
		launch();
        Client c = new Client();
    }
}