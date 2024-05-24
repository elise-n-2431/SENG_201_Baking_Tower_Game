package seng201.team48.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

/**Controls the startup of windows and transitions of screens
 * Controls audio
 * */
public class FXWindow extends Application {
    private MediaPlayer mediaPlayer;

    /**Sets the Stages
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException if invalid
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/fx_wrapper.fxml"));
        Parent root = baseLoader.load();
        FXWrapper fxWrapper = baseLoader.getController();

        // Setup background audio
        String audioFilePath = getClass().getResource("/audio/background_audio.mp3").toExternalForm();
        Media backgroundAudio = new Media(audioFilePath);
        mediaPlayer = new MediaPlayer(backgroundAudio);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(-1); // To loop indefinitely
        mediaPlayer.play();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("FX Wrapper");
        primaryStage.setScene(scene);
        primaryStage.show();
        fxWrapper.init(primaryStage);
    }


    public static void launchWrapper(String[] args) {
        launch(args);
    }

}
