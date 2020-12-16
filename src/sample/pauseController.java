package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class pauseController {
    @FXML
    private Button backB;
    @FXML
    private Button lb1;
    @FXML
    private Button lb2;
    @FXML
    private Button lb3;
    @FXML
    private ImageView PauseIcon;
    private Stage ps;
    private Parent root;
    private FXMLLoader loader;
    private AnimationTimer timer;
    private Game g;

    String path8 = "src/assets/start.wav";
    AudioClip click = new AudioClip(new File(path8).toURI().toString());

    public void init( Game g){
        this.g=g;
        this.ps=this.g.getPs();
        this.root=this.g.getRoot();
        this.loader=this.g.getLoader();
        this.timer=this.g.getTimer();
        Timeline t=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(PauseIcon.scaleXProperty(), PauseIcon.getScaleX(), Interpolator.EASE_OUT), new KeyValue(PauseIcon.scaleYProperty(), PauseIcon.getScaleY(), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.seconds(1), new KeyValue(PauseIcon.scaleXProperty(), PauseIcon.getScaleX()+0.1, Interpolator.EASE_OUT), new KeyValue(PauseIcon.scaleYProperty(), PauseIcon.getScaleY()+0.1, Interpolator.EASE_OUT))
        );
        t.setAutoReverse(true);
        t.setCycleCount(-1);
        t.play();
    }
    public void goBack() throws IOException {
        click.play();
        this.g.setOld_time(System.nanoTime());
        Scene main1=this.ps.getScene();
        main1.setRoot(this.root);
        this.timer.start();
        this.root.requestFocus();
        gamePlayController.mediaPlayer.play();

    }
    public void saveGame() throws IOException {
        click.play();
        gamePlayController.mediaPlayer.stop();
        this.g.setGameId();
        Main.serialize();
        quitToMain();
    }
    public void quitToMain() throws IOException {

        click.play();
        gamePlayController.mediaPlayer.stop();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = null;
        try {
            root = loader1.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller myCon=(Controller)(loader1.getController());
        try {
            myCon.init(this.ps, this.g.getApp());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StackPane sp=new StackPane(root);
        Scene main1=this.g.getPs().getScene();
        main1.setRoot(sp);
    }
    public void highlightOn_b() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0 ;");
    }
    public void highlightOff_b() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }
    public void highlightOn_1() throws IOException {
        lb1.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_1() throws IOException {
        lb1.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_2() throws IOException {
        lb2.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_2() throws IOException {
        lb2.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_3() throws IOException {
        lb3.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_3() throws IOException {
        lb3.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
}
