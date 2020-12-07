package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class newGameController {
    @FXML
    private AnchorPane p1;
    @FXML
    private Button cross;
    private boolean exited;
    public void init(){
        p1.setStyle("-fx-background-color: rgb(0,0,0,0.8);");
        this.exited=false;
    }
    public void Enter(){
        Stage s=(Stage)p1.getScene().getWindow();
        s.close();
        this.exited=true;
    }
    public void exit(){
        Stage s=(Stage)p1.getScene().getWindow();
        s.close();
    }
    public boolean giveState() {
        return this.exited;
    }
}
