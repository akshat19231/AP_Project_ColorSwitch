package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

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
    private App curApp;
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

    public void init(Stage s, App app) throws IOException {
        this.curApp=app;
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
        myCon.init(this.ps, this.ps.getScene().getRoot(), this.curApp);
        //this.ps.setTitle("Color Switch");
        Scene main1=this.ps.getScene();
        main1.setRoot(root);
    }
    public void reDirect(String un) throws IOException {
        Game g1=new Game(1);
        Player p1=new Player(un);
        g1.setPlayer(p1);
        p1.setMyGame(g1);
        g1.setApp(this.curApp);
        this.curApp.addGame(g1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePlay.fxml"));
        StackPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        gamePlayController myCon=(gamePlayController)(loader.getController());
        try {
            myCon.init(this.ps, root, loader,g1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ps.setTitle("Color Switch");
        Scene main1=new Scene(root);
        setKeyFunctions(main1, myCon);
        this.ps.setScene(main1);
        root.requestFocus();
        myCon.startGame();
    }
    public void newGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newGame.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newScene = new Scene(root);
        newScene.setFill(Color.TRANSPARENT);
        newGameController myCon=(newGameController)(loader.getController());
        myCon.init();
        Stage newStage=new Stage(StageStyle.TRANSPARENT);
        newStage.initOwner(this.ps);
        newStage.setScene(newScene);
        newStage.showAndWait();
        if(myCon.giveState()) {
            this.reDirect(myCon.getUsername());
        }
    }
    private void setKeyFunctions(Scene scene, gamePlayController Con) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                setOnUserInput(scene, Con);
            }
        });
    }

    private void setOnUserInput(Scene scene, gamePlayController c) {
        c.getGame().getMain_ball().vy=500;
    }
    public void quitGame() throws IOException{
        Platform.exit();
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
