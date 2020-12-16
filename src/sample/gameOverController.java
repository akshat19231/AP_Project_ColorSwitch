package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    private App app;
    private Obstacles collidedWith;
    String path6 = "src/assets/start.wav";
    AudioClip click = new AudioClip(new File(path6).toURI().toString());

    public void init(Game g, App app, Obstacles o){
        this.g=g;
        this.app=app;
        this.collidedWith=o;
        this.score.setText(Integer.toString(this.g.getLevel()-1));
        this.starCnt.setText(Integer.toString(this.g.getScore()));
        ArrayList<Node> tobeRemoved=new ArrayList<Node>();
        for (int j = 0; j < ((StackPane) this.g.getRoot()).getChildren().size(); j++) {
            if (((StackPane) this.g.getRoot()).getChildren().get(j) instanceof Pane) {
                for(int i=0;i<((Pane) ((StackPane) this.g.getRoot()).getChildren().get(j)).getChildren().size();i++){
                    if(((Pane) ((StackPane) this.g.getRoot()).getChildren().get(j)).getChildren().get(i) instanceof Circle && ((Circle) ((Pane) ((StackPane) this.g.getRoot()).getChildren().get(j)).getChildren().get(i)).getRadius()==4){
                        tobeRemoved.add(((Pane) ((StackPane) this.g.getRoot()).getChildren().get(j)).getChildren().get(i));
                        System.out.println("bruv");
                    }
                }
            }
        }
        for (int j = 0; j < ((StackPane) this.g.getRoot()).getChildren().size(); j++) {
            if (((StackPane) this.g.getRoot()).getChildren().get(j) instanceof Pane) {
                ((Pane) ((StackPane) this.g.getRoot()).getChildren().get(j)).getChildren().remove(tobeRemoved);
            }
        }
        this.g.getSmallTimer().stop();
        this.g.setSmallTimer(null);
        this.g.getMain_ball().getCircle().setOpacity(1);
    }
    public void restart() throws IOException {
        click.play();
        gamePlayController.mediaPlayer.stop();
        Game g1=new Game(0);
        Player p1=new Player(this.g.getPlayer().getUname());
        g1.setPlayer(p1);
        p1.setMyGame(g1);
        g1.setApp(this.app);
        this.app.addGame(g1);
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
            myCon.init(this.g.getPs(), root, loader,g1, this.app);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene main1=this.g.getPs().getScene();
        main1.setRoot(root);
        setKeyFunctions(main1, myCon);
        g1.getRoot().requestFocus();
        myCon.startGame();

    }
    private void setKeyFunctions(Scene scene, gamePlayController Con) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                String path4 = "src/assets/jump.wav";

                AudioClip jump = new AudioClip(new File(path4).toURI().toString());
//                Media media4 = new Media(new File(path4).toURI().toString());

                jump.play();

                setOnUserInput(scene, Con);
            }
        });
    }
    private void setOnUserInput(Scene scene, gamePlayController c) {
        c.getGame().getMain_ball().vy=500;
    }
    public void quitToMain() throws IOException {
        click.play();
        gamePlayController.mediaPlayer.stop();
        Game saved=this.app.getGame(this.g.getPlayer().getUname());
        if(saved!=null){
            this.app.getGameMap().remove(this.g.getPlayer().getUname());
            //removed from map
            Main.serialize();
        }
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = null;
        try {
            root = loader1.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller myCon=(Controller)(loader1.getController());
        try {
            myCon.init(this.g.getPs(),this.g.getApp());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.ps.setTitle("Color Switch");
        StackPane sp=new StackPane(root);
        Scene main1=this.g.getPs().getScene();
        main1.setRoot(root);
    }
    public void resume() throws IOException {
        //if(this.g.getScore()<5) return;
        click.play();
        gamePlayController.mediaPlayer.play();
        double line=this.collidedWith.getStarY();
        if(line>340){
            for(int i=0;i<this.g.getSize();i++){
                this.g.getObs(i).moveDown(80);
            }
            for(int i=0;i<this.g.getSizeQ();i++){
                this.g.getObsQ(i).moveDown(80);
            }
        }
        this.g.getMain_ball().getCircle().setLayoutY(420);
        this.g.getMain_ball().setCurY();
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
