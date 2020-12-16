package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class newGameController {
    @FXML
    private AnchorPane p1;
    @FXML
    private Button cross;
    @FXML
    private TextField textInput;
    private String username;
    private boolean exited;

    String path7 = "src/assets/start.wav";
    AudioClip click = new AudioClip(new File(path7).toURI().toString());
    String path = "src/assets/be.mp3";


    public void init(){
        p1.setStyle("-fx-background-color: rgb(0,0,0,0.8);");
        this.exited=false;
    }
    public void Enter(){
        click.play();
        this.username = this.textInput.getText();
        if (this.username == null || this.username.length() == 0) {
            System.out.println(this.getClass().toString() + "No Input!");
            return;
        }

        Stage s=(Stage)p1.getScene().getWindow();
        s.close();
        this.exited=true;
    }
    public void exit(){
        click.play();
        Stage s=(Stage)p1.getScene().getWindow();

        s.close();
    }
    public boolean giveState() {
        return this.exited;
    }
    public String getUsername(){
        return this.username;
    }
}
