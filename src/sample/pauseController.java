package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class pauseController {
    @FXML
    private Button backB;
    @FXML
    private Button lb1;
    @FXML
    private Button lb2;
    @FXML
    private Button lb3;
    private Stage ps;
    public void init(Stage s){
        this.ps=s;
    }
    public void goBack(){

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
