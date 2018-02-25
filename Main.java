package MediaPlayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*********************************************************************************
 *
 * Basic Media Player Application
 *
 * *******************************************************************************
 * Version 1
 *
 * 25/2/18:
 *      Basic interface created using scene builder
 *      Program will allow selection of and plays mp4 videos
 *      Allows user to select video from file location on computer
 *      Play, Pause, Stop, Slower, Faster, Open and Quit buttons functional
 *      Clicking play button whilst playing, will also pause video
 *
 *
 * To Add:
 *
 *
 *      P1: volume slider
 *          -Mute button
 *      P2: change the icon of play value once isPaused = true to be pauseButton
 *          -remove pause button
 *      P2: New open file button on screen
 *          -HBOX for buttons: set style hidden until new open file button shows
 ********************************************************************************/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mediaPlayer.fxml"));
        primaryStage.setTitle("Steve's Media Player");

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(975);
        primaryStage.setMaxHeight(690);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
