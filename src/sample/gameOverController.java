package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class gameOverController {
    @FXML
    private Button restartB;
    @FXML
    private Button resumeB;
    @FXML
    private Button homeB;
    @FXML
    private Text score;
    @FXML
    private Text starCnt;
    private Game g;
    public void init(Game g){
        this.g=g;
        this.score.setText(Integer.toString(this.g.getLevel()-1));
        this.starCnt.setText(Integer.toString(this.g.getScore()));

    }
    public void restart() throws IOException {
//        this.g.setOld_time(System.nanoTime());
//        Scene main1=this.ps.getScene();
//        main1.setRoot(this.root);
//        this.timer.start();
//        this.root.requestFocus();

    }
    public void quitToMain() throws IOException {

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = null;
        try {
            root = loader1.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller myCon=(Controller)(loader1.getController());
        try {
            myCon.init(this.g.getPs());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.ps.setTitle("Color Switch");
        Scene main1=this.g.getPs().getScene();
        main1.setRoot(root);
    }
    public void resume() throws IOException {
        if(this.g.getScore()<5) return;
        this.g.useStars();
        this.g.setOld_time(System.nanoTime());
        Scene main1=this.g.getPs().getScene();
        main1.setRoot(this.g.getRoot());
        this.g.getTimer().start();
        this.g.getRoot().requestFocus();
    }
    public void highlightOn_r() throws IOException {
        restartB.setStyle("-fx-background-radius: 30px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_r() throws IOException {
        restartB.setStyle("-fx-background-radius: 30px; -fx-background-color:  #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_1() throws IOException {
        resumeB.setStyle("-fx-background-radius: 30px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_1() throws IOException {
        resumeB.setStyle("-fx-background-radius: 30px; -fx-background-color:  #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_h() throws IOException {
        homeB.setStyle("-fx-background-radius: 30px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_h() throws IOException {
        homeB.setStyle("-fx-background-radius: 30px; -fx-background-color:  #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }

}
