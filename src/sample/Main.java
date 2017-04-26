package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    BorderPane mainpane = new BorderPane();

    Button startbtn = new Button("Start Animation");

    StackPane imagepane = new StackPane();
    Timeline proccedanimation;
    int imagetitlenum = 1;

    GridPane gettinginfopane = new GridPane();
    Label title = new Label("Enter information for animation");
    Label splabel = new Label("Animation speed in milliseconds");
    TextField speed = new TextField();
    Label pflabel = new Label("Image file prefix");
    TextField prefix = new TextField();
    Label imlabel = new Label("umber of images");
    TextField imageNum = new TextField();
    Label urllabel = new Label("Audio file URL");
    TextField URL = new TextField();

    GridPane audiocontrolpane = new GridPane();
    Button play = new Button("Play");
    Button loop = new Button("Loop");
    Button stop = new Button("Stop");

    AudioClip audio;
    Boolean isaudioOn = false;
    Boolean isplaying = false;


    @Override
    public void start(Stage primaryStage) {

        gettinginfopane.setAlignment(Pos.CENTER);
        gettinginfopane.add(title, 0, 0);
        gettinginfopane.add(splabel, 0, 1);
        speed.setPrefColumnCount(30);
        gettinginfopane.add(speed, 1, 1);
        gettinginfopane.add(pflabel, 0, 2);
        prefix.setPrefColumnCount(30);
        gettinginfopane.add(prefix, 1, 2);
        gettinginfopane.add(imlabel, 0, 3);
        imageNum.setPrefColumnCount(30);
        gettinginfopane.add(imageNum, 1, 3);
        gettinginfopane.add(urllabel, 0, 4);
        URL.setPrefColumnCount(30);
        gettinginfopane.add(URL, 1, 4);

        audiocontrolpane.setAlignment(Pos.CENTER);
        audiocontrolpane.add(play, 0, 0);
        audiocontrolpane.add(loop, 1, 0);
        audiocontrolpane.add(stop, 2, 0);
        gettinginfopane.add(audiocontrolpane, 1, 5);

        mainpane.setTop(startbtn);
        mainpane.setAlignment(startbtn, Pos.TOP_RIGHT);
        mainpane.setCenter(imagepane);
        mainpane.setBottom(gettinginfopane);

        startbtn.setOnAction(event -> {
            if (speed.getText().length() > 0) {

                proccedanimation = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        nextImage();
                    }
                }));
                proccedanimation.setCycleCount(Timeline.INDEFINITE);

                proccedanimation.setRate(Integer.parseInt(speed.getText()));
                proccedanimation.play();

                audioOn();
                play();
            }
        });
        play.setOnAction(event -> {
            audioOn();
            play();
        });
        stop.setOnAction(event -> {
            audioOn();
            audio.stop();
        });
        loop.setOnAction(event -> {
            audioOn();
            audio.setCycleCount(AudioClip.INDEFINITE);
        });


        Scene scene = new Scene(mainpane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void audioOn() {
        if (isaudioOn != true) {
            audio = new AudioClip(URL.getText());
            isaudioOn = true;
        }
    }

    private void play() {
        if (isplaying == true) {
            audio.stop();
            isplaying = true;
        }
        audio.play();
        isplaying = true;
    }


    private void nextImage() {
        if (imagetitlenum < Integer.parseInt(imageNum.getText())) {
            imagetitlenum += 1;
        } else
            imagetitlenum = 1;


        imagepane.getChildren().clear();
        imagepane.getChildren().add(new ImageView(new Image("file:D:\\programs\\workplace\\intellijworkspace\\csc201_ch1623_javafx\\image\\" + prefix.getText() + imagetitlenum + ".gif")));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
