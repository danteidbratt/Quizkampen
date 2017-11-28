package quizkampen.client;

import java.io.File;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Client extends Application {

    Window w;
    MediaPlayer player;

    @Override
    public void start(Stage stage) throws Exception {
        Window w = new Window();
        w.setFrame();
        File file = new File("bensound-epic.mp3");
        Media media = new Media(file.toURI().toString()); //replace /Movies/test.mp3 with your file
        player = new MediaPlayer(media);
        player.play();
    }

    public static void main(String[] args) {
        Client c = new Client();
        launch();
    }
}