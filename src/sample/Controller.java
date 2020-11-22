package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
public class Controller {
    @FXML
    private ImageView sRing1;
    @FXML
    private ImageView logo2;
    @FXML
    private ImageView logo3;
    @FXML
    private ImageView sRing2;
    @FXML
    private Button exitB;
    @FXML
    private Button newGameB;
    @FXML
    private Button leaderBoardB;
    @FXML
    private Button loadGameB;
    private Stage ps;
    DropShadow shadow = new DropShadow();
    public void rot(ImageView imv, int mul){
        RotateTransition rotate = new RotateTransition(Duration.millis(3000));
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360*mul);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        //rotate.setAutoReverse(true);
        rotate.setNode(imv);
        rotate.play();
    }
    public void init(Stage s) throws IOException {
        this.ps=s;
        rot(sRing1,1);
        rot(sRing2,-1);
        rot(logo2,1);
        rot(logo3,-1);
    }
    public void handleClick(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("load.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadScreen myCon=(loadScreen)(loader.getController());
        myCon.init(this.ps);
        this.ps.setTitle("Color Switch");
        Scene main1=new Scene(root);
        this.ps.setScene(main1);
    }
    public void highlightOn_e() throws IOException {
        exitB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0 ;");
    }
    public void highlightOff_e() throws IOException {
        exitB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }
    public void highlightOn_l() throws IOException {
        leaderBoardB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0 ;");
    }
    public void highlightOff_l() throws IOException {
        leaderBoardB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }
    public void highlightOn_n() throws IOException {
        newGameB.setStyle("-fx-background-radius: 100px; -fx-border-width: 2px; -fx-background-color: a0a3e0 ;");
    }
    public void highlightOff_n() throws IOException {
        newGameB.setStyle("-fx-background-radius: 100px; -fx-border-width: 2px; -fx-background-color: white;");
    }
    public void highlightOn_load() throws IOException {
        loadGameB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0;");
    }
    public void highlightOff_load() throws IOException {
        loadGameB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }
}
