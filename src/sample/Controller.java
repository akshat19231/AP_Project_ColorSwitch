package sample;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
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
    private Button reverseB;
    @FXML
    private Button leaderBoardB;
    @FXML
    private Button loadGameB;
    private Stage ps;
    private App curApp;
    private ArrayList<RotateTransition> rt;
    private ArrayList<Integer> rtDir;
    String path9 = "src/assets/start.wav";
    AudioClip click = new AudioClip(new File(path9).toURI().toString());

    String path10 = "src/assets/reverse.mp3";
    AudioClip reverse = new AudioClip(new File(path10).toURI().toString());

    String path4 = "src/assets/jump.wav";

    AudioClip jump = new AudioClip(new File(path4).toURI().toString());

    public void rot(ImageView imv, int mul){
        RotateTransition rotate = new RotateTransition(Duration.millis(3000));
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360*mul);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        //rotate.setAutoReverse(true);
        rotate.setNode(imv);
        rt.add(rotate);
        rtDir.add(mul);
        rotate.play();
    }

    public void init(Stage s, App app) throws IOException {
        this.curApp=app;
        this.ps=s;
        rt=new ArrayList<RotateTransition>();
        rtDir=new ArrayList<>();
        rot(sRing1,1);
        rot(sRing2,-1);
        rot(logo2,1);
        rot(logo3,-1);
        gamePlayController.mode=false;
        this.reverseB.setOpacity(1);

    }
    public void showLeaderBoard(){
        click.play();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Leaderboard.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent r=root;
        Leaderboard myCon=(Leaderboard)(loader.getController());
        myCon.init(this.ps, this.ps.getScene().getRoot(), this.curApp);
        StackPane sp= (StackPane) this.ps.getScene().getRoot();
        AnchorPane ap= (AnchorPane)sp.getChildren().get(0);
        sp.getChildren().add(r);
        r.translateXProperty().set(620);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(r.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t->{
            sp.getChildren().remove(ap);
        });
        timeline.play();
    }
    public void handleClick(){
        click.play();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("load.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent r=root;
        loadScreen myCon=(loadScreen)(loader.getController());
        myCon.init(this.ps, this.ps.getScene().getRoot(), this.curApp);
        StackPane sp= (StackPane) this.ps.getScene().getRoot();
        AnchorPane ap= (AnchorPane)sp.getChildren().get(0);
        sp.getChildren().add(r);
        r.translateXProperty().set(620);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(r.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t->{
            sp.getChildren().remove(ap);
        });
        timeline.play();
    }
    public void reDirect(String un) throws IOException {
        reverse.play();
        Game g1=new Game(0);
        Player p1=new Player(un);
        g1.setPlayer(p1);
        p1.setMyGame(g1);
        g1.setApp(this.curApp);
        this.curApp.addGame(g1);
        if(gamePlayController.mode!=g1.getMode()){
            g1.toggleMode();
        }
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
            myCon.init(this.ps, root, loader,g1, this.curApp);
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
        click.play();
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
    public void switchOnReverse(){
        reverse.play();
        if(!gamePlayController.mode){
            gamePlayController.mode=true;
            this.reverseB.setOpacity(0.3);
            for(int i=0;i<rt.size();i++){
                RotateTransition rtx=rt.get(i);
                rtx.stop();
                rtx.setByAngle(rtDir.get(i)*360*(-1));
                rtx.play();
            }
        }else{
            gamePlayController.mode=false;
            this.reverseB.setOpacity(1);
            for(int i=0;i<rt.size();i++){
                RotateTransition rtx=rt.get(i);
                rtx.stop();
                rtx.setByAngle(rtDir.get(i)*360);
                rtx.play();
            }
        }
    }
    public void setKeyFunctions(Scene scene, gamePlayController Con) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE && !gamePlayController.mode) {
                Con.getGame().setGravity(2000);
                jump.play();
                setOnUserInput(scene, Con);
            }
            if(e.getCode() == KeyCode.D && gamePlayController.mode){
                gamePlayController.focusObs.rotateRight();
            }
            if(e.getCode() == KeyCode.A && gamePlayController.mode){
                gamePlayController.focusObs.rotateLeft();
            }
            if(e.getCode() == KeyCode.S && gamePlayController.mode){
                gamePlayController.focusObs.rotateStop();
            }

        });
    }

    public void setOnUserInput(Scene scene, gamePlayController c) {
        c.getGame().getMain_ball().vy=500;
    }
    public void quitGame() throws IOException{
        click.play();
        Platform.exit();
    }
    public void highlightOn_e() throws IOException {
        exitB.setStyle("-fx-background-radius: 100px; -fx-background-color: #D51D0A ;");
    }
    public void highlightOff_e() throws IOException {
        exitB.setStyle("-fx-background-radius: 100px; -fx-background-color:  #D72C16;");
    }
    public void highlightOn_r() throws IOException {
        reverseB.setStyle("-fx-background-radius: 100px; -fx-background-color: #D70026 ;");
    }
    public void highlightOff_r() throws IOException {
        reverseB.setStyle("-fx-background-radius: 100px; -fx-background-color:  #A10115;");
    }
    public void highlightOn_l() throws IOException {
        leaderBoardB.setStyle("-fx-background-radius: 100px; -fx-background-color: #D51D0A ;");
    }
    public void highlightOff_l() throws IOException {
        leaderBoardB.setStyle("-fx-background-radius: 100px; -fx-background-color:  #D72C16;");
    }
    public void highlightOn_n() throws IOException {
        newGameB.setStyle("-fx-background-radius: 100px; -fx-border-width: 2px; -fx-background-color: a0a3e0 ;");
    }
    public void highlightOff_n() throws IOException {
        newGameB.setStyle("-fx-background-radius: 100px; -fx-border-width: 2px; -fx-background-color: white;");
    }
    public void highlightOn_load() throws IOException {
        loadGameB.setStyle("-fx-background-radius: 100px; -fx-background-color: #D51D0A;");
    }
    public void highlightOff_load() throws IOException {
        loadGameB.setStyle("-fx-background-radius: 100px; -fx-background-color:  #D72C16;");
    }
}
