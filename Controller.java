package MediaPlayer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    //-------------------------------
    //       Variables
    //-------------------------------


    @FXML
    private MediaView mediaView;         // new mediaView object this allows us to call the mediaView ID we adding in sceneBuilder
    @FXML
    private StackPane centerStack;       // new BorderPane object this allows us to call the BorderPane ID we adding in sceneBuilder


    private String filePath;             // used for openFile button to store file path to open

    private MediaPlayer mediaPlayer;     // create media player object

    private boolean isPaused  = true;    // boolean to check state of video if paused

    private boolean isStopped =false;    // boolean to check state of video if stopped




    //-------------------------------
    //  Open File Button
    //-------------------------------
    @FXML
    private void openFileButton(ActionEvent event){

        FileChooser fileChooser = new FileChooser();               // allow to choose a file and store it

        // filter choices based on being an mp4 file.
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (.mp4)", "*.mp4");

        fileChooser.getExtensionFilters().add(filter);             // adds the filter extension object to the file Chooser object

        File file = fileChooser.showOpenDialog(null); // when it opens window it allows to choose file

        filePath = file.toURI().toString();                        // add filepath from file object to filePath String variable

        if(filePath != null) {                                     // checks that there is variable stored in filePath

            Media media = new Media(filePath);                     // before adding it to a new media object

            mediaPlayer = new MediaPlayer(media);                  // and adding that to the media object

            mediaView.setMediaPlayer(mediaPlayer);                 // adds the mediaPlayer to the mediaView section of layout

            DoubleProperty width = mediaView.fitWidthProperty();   // create width object to create bindings for mediaView object

            DoubleProperty height = mediaView.fitHeightProperty(); // create width object to create bindings for mediaView object

            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),  "Width" )); // binds width of mediaView to the scene

            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "Height")); // binds width of mediaView to the scene

            mediaPlayer.play();                                               // plays media once added

            mediaView.fitHeightProperty().bind(centerStack.heightProperty()); // ensure mediaView inherits size from center stack

            mediaView.fitWidthProperty().bind(centerStack.widthProperty());   // ensure mediaView inherits size from center stack

            mediaView.setPreserveRatio(true);                                 // preserve ratio whilst resizing

            mediaView.setSmooth(true);                                        // smoother resizing


        }
    }

    //-------------------------------
    //  Play Video Button
    //-------------------------------
    @FXML
    private void playVideo(ActionEvent event) {

        centerStack.setStyle("-fx-background-color: aqua");


        if (isPaused || isStopped){     //Scenario 1: Video paused or stopped

            mediaPlayer.setRate(1);     // reset play rate to normal

            mediaPlayer.play();         // plays  video

            isPaused  = false;          // not paused

            isStopped = false;          // not stopped

        } else {                        //Scenario 2: Video playing

            mediaPlayer.pause();        // pauses video

            isPaused = true;            // now paused

        }




    }




    //-------------------------------
    //  Pause Video Button
    //-------------------------------
    @FXML
    private void pauseVideo(ActionEvent event) {

        mediaPlayer.pause();        // pauses video

        isPaused = true;            // now paused


    }

    //-------------------------------
    //  Stop Video Button
    //-------------------------------
    @FXML
    private void stopVideo(ActionEvent event) {

        mediaPlayer.stop();         // stops video

        isStopped = true;           // is stopped

    }

    //-------------------------------
    //  FastForward Video Button >>
    //-------------------------------
    @FXML
    private void fastForwardVideo(ActionEvent event) {

        mediaPlayer.setRate(2.5);   // changes playback rate to speed video to 1.5x

    }

    //-------------------------------
    //  Rewind Video Button <<
    //-------------------------------
    @FXML
    private void slowVideo(ActionEvent event) {

        mediaPlayer.setRate(0.75);  // changes playback rate to speed video to 0.75x

    }



    //-------------------------------
    //  Exit Button
    //-------------------------------
    @FXML // tag event to FXML document
    private void exitButton(ActionEvent event) {

        alertBox();                 // displays alert box to user
    }


    /**
     * alertBox
     *
     * shows alert box to confirm user choice
     *
     */
    public void alertBox() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);           // create alert box

        alert.setTitle("Alert");                                         // set title

        alert.setHeaderText("Press 'OK' to quit Program or 'Cancel' to resume");  // add header

        Optional<ButtonType> result = alert.showAndWait();               // makes user select option before can return

        if (result.get() == ButtonType.OK) {                             // if statement for if user selects yes or now

            System.exit(0);                                       // closes window

        } else {
            alert.close();                                               // quit the alert box
        }
    }


    public void initialize(URL url, ResourceBundle rb) {}


} // close class