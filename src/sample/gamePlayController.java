package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class gamePlayController {
//    @FXML
//    public Circle ball;
    @FXML
    public Circle c;
    @FXML
    public Line l1;
    @FXML
    public Line l2;
    @FXML
    public Line l3;
    @FXML
    public Line l4;
    @FXML
    private Button backB;
    @FXML
    private Text scoreBoard;
    private Game game;
    public static Boolean mode=false;
    private App app;
    private ArrayList <smallBalls> animationBalls;
    public static Obstacles focusObs;
//    public Media media;

//    public MediaPlayer mediaPlayer;

    public static String path = "src/assets/be.mp3";

    public static transient Media media = new Media(new File(path).toURI().toString());

    public static transient MediaPlayer mediaPlayer = new MediaPlayer(media);

    public void init(Stage s, Parent p, FXMLLoader fml, Game g, App app) throws IOException {
        this.game=g;
        this.animationBalls=new ArrayList<smallBalls>();
        this.game.initialiseObs();
        this.focusObs=(Obstacles) this.game.getObsQ(0);
        this.game.setPs(s);
        this.game.setRoot(p);
//        mediaPlayer.setAutoPlay(true);
        this.game.setLoader(fml);
        //mediaPlayer.play();
        if(mode!=this.game.getMode()){
            mode=this.game.getMode();
        }
        this.game.setTimer(null);
        this.game.setSmallTimer(null);
        this.app=app;
        this.scoreBoard.setText(Integer.toString(this.game.getScore()));
        this.game.setMain_ball(new Ball(0, 312,500));
        this.game.getMain_ball().getCircle().setLayoutY(this.game.getMain_ball().getCircle().getLayoutY() - 3);
        this.game.getMain_ball().setCurY();
        if(mode){
            this.game.setGravity(0);
            this.game.getMain_ball().setVy(95);
        }
        ArrayList <Node> toBeAdded= new ArrayList<Node>();
        toBeAdded.add(this.game.getMain_ball().getCircle());

        for(int i=0;i<this.game.getSize();i++){
            toBeAdded.add(this.game.getObs(i).getGroup());
        }
        for(int i=0;i<this.game.getSizeQ();i++){
            ((Obstacles)this.game.getObsQ(i)).rotateOn();
            if(this.game.getObsQ(i) instanceof CircleObs) {
                toBeAdded.add(this.game.getObsQ(i).getGroup());
            }else if(this.game.getObsQ(i) instanceof doubleCircle){
                toBeAdded.add(((doubleCircle) this.game.getObsQ(i)).getGroup());
                toBeAdded.add(((doubleCircle) this.game.getObsQ(i)).getGroup1());
            }else if(this.game.getObsQ(i) instanceof doubleCross){
                toBeAdded.add(((doubleCross) this.game.getObsQ(i)).getGroup());
                toBeAdded.add(((doubleCross) this.game.getObsQ(i)).getGroup1());
            }else{
                toBeAdded.add(this.game.getObsQ(i).getGroup());
            }
        }
        assert this.game.getRoot() != null;
        for(int j = 0; j<((StackPane)this.game.getRoot()).getChildren().size(); j++){
            if(((StackPane)this.game.getRoot()).getChildren().get(j) instanceof Pane){
                ((Pane) ((StackPane)this.game.getRoot()).getChildren().get(j)).getChildren().addAll(toBeAdded);
            }
        }
        if(checkIfRing() instanceof doubleCircle){
            int choose=new Random().nextInt(2);
            if(choose==0)
                this.game.getMain_ball().getCircle().setFill(Paint.valueOf("#f70578"));
            else
                this.game.getMain_ball().getCircle().setFill(Paint.valueOf("#440580"));
        }
    }
    public void loadGame(Stage s, Parent p, FXMLLoader fml, Game g, App app1) throws IOException {
        this.game=g;
        //mediaPlayer.play();
        this.animationBalls=new ArrayList<smallBalls>();
        this.game.setPs(s);
        this.game.setRoot(p);
        this.game.setLoader(fml);
        this.game.setTimer(null);
        this.game.setSmallTimer(null);
        this.game.main_ball.setUp();
        this.game.setupObs();
        this.app=app1;
        this.scoreBoard.setText(Integer.toString(this.game.getScore()));
        ArrayList <Node> toBeAdded= new ArrayList<Node>();
        toBeAdded.add(this.game.getMain_ball().getCircle());
        for(int i=0;i<this.game.getSize();i++){
            toBeAdded.add(this.game.getObs(i).getGroup());
        }
        for(int i=0;i<this.game.getSizeQ();i++){
            ((Obstacles)this.game.getObsQ(i)).rotateOn();
            if(this.game.getObsQ(i) instanceof CircleObs) {
                toBeAdded.add(this.game.getObsQ(i).getGroup());
            }else if(this.game.getObsQ(i) instanceof doubleCircle){
                toBeAdded.add(((doubleCircle) this.game.getObsQ(i)).getGroup());
                toBeAdded.add(((doubleCircle) this.game.getObsQ(i)).getGroup1());
            }else if(this.game.getObsQ(i) instanceof doubleCross){
                toBeAdded.add(((doubleCross) this.game.getObsQ(i)).getGroup());
                toBeAdded.add(((doubleCross) this.game.getObsQ(i)).getGroup1());
            }else{
                toBeAdded.add(this.game.getObsQ(i).getGroup());
            }
        }
        if(mode!=this.game.getMode()){
            if(mode){
                mode=false;
            }else{
                mode=true;
            }
        }
        if(mode){
            this.game.setGravity(0);
            this.game.getMain_ball().setVy(95);
        }
        assert this.game.getRoot() != null;
        for(int j = 0; j<((StackPane)this.game.getRoot()).getChildren().size(); j++){
            if(((StackPane)this.game.getRoot()).getChildren().get(j) instanceof Pane){
                ((Pane) ((StackPane)this.game.getRoot()).getChildren().get(j)).getChildren().addAll(toBeAdded);
            }
        }
        focusObs=checkIfRing();
        if(checkIfRing() instanceof doubleCircle){
            int choose=new Random().nextInt(2);
            if(choose==0)
                this.game.getMain_ball().getCircle().setFill(Paint.valueOf("#f70578"));
            else
                this.game.getMain_ball().getCircle().setFill(Paint.valueOf("#440580"));
        }
    }
    public Game getGame(){
        return this.game;
    }
    public void regCollisionCheck() throws InterruptedException {
        ArrayList<gameElements> tobeRemoved=new ArrayList<gameElements>();
        for(int i=0;i<this.game.getSizeQ();i++) {
            if(this.game.getObsQ(i).collisionCheck(this.game.getMain_ball().getCircle())){
                String path1 = "src/assets/dead.wav";
                AudioClip dead = new AudioClip(new File(path1).toURI().toString());
                dead.play();
                mediaPlayer.pause();
                this.GameOver((Obstacles) this.game.getObsQ(i));
            }
        }

        for(int i=0;i<this.game.getSize();i++) {
            if ((this.game.getObs(i) instanceof wheel) || this.game.getObs(i) instanceof stars) {
                if (this.game.getObs(i).collisionCheck(this.game.getMain_ball().getCircle())) {
                    if (this.game.getObs(i) instanceof stars) {
                        String path2 = "src/assets/star.wav";
                        AudioClip star = new AudioClip(new File(path2).toURI().toString());
                        star.play();
                        this.updateScore();
                        this.scoreBoard.setText(Integer.toString(this.game.getScore()));
                    }else{
                        String path3 = "src/assets/colorswitch.wav";
                        AudioClip colorwheel = new AudioClip(new File(path3).toURI().toString());
                        colorwheel.play();
                        if(checkIfRing() instanceof doubleCircle){
                            int choose=new Random().nextInt(2);
                            if(choose==0)
                                this.game.getMain_ball().getCircle().setFill(Paint.valueOf("#f70578"));
                            else
                                this.game.getMain_ball().getCircle().setFill(Paint.valueOf("#440580"));
                        }
                    }

                    tobeRemoved.add(this.game.getObs(i));

                }
            }
        }
        for(int i=0;i<tobeRemoved.size();i++) {
            for (int j = 0; j < ((StackPane) this.game.getRoot()).getChildren().size(); j++) {
                if (((StackPane) this.game.getRoot()).getChildren().get(j) instanceof Pane) {
                    ((Pane) ((StackPane) this.game.getRoot()).getChildren().get(j)).getChildren().remove(tobeRemoved.get(i).getGroup());
                    this.game.remove(tobeRemoved.get(i));
                }
            }
        }
    }
    public Obstacles checkIfRing(){
        for(int i=0;i<this.game.getSizeQ();i++){
            if(this.game.getObsQ(i).getBottomY()<this.game.getMain_ball().getCircle().getLayoutY()){
                return (Obstacles) this.game.getObsQ(i);
            }
        }
        return null;
    }
    public void updateScore(){
        this.game.levelUp();
    }
    public void fun(Game g){
        double val=0;
        if (g.getObsQ(0) instanceof CircleObs || g.getObsQ(0) instanceof doubleCircle) val=275;
        if( g.getSizeQ()>0 && g.getObsQ(0).getPosY()>=799-val){
            System.out.println("yes");
            for (int j = 0; j < ((StackPane) this.game.getRoot()).getChildren().size(); j++) {
                if (((StackPane) this.game.getRoot()).getChildren().get(j) instanceof Pane) {
                    ((Pane) ((StackPane) this.game.getRoot()).getChildren().get(j)).getChildren().remove(g.getObsQ(0).getGroup());
                }
            }
            double y=(g.getObsQ(0)).getPosY();
            g.removeQ();
            int ret=g.update(900);
            stars new_s=new stars(302,((Obstacles)(g.getObsQ(g.getSizeQ()-1))).getStarY());
            new_s.makeObs();
            wheel new_w=new wheel(311,((Obstacles)(g.getObsQ(g.getSizeQ()-1))).getWheelY());
            new_w.makeObs();
            this.game.add(new_s);
            this.game.add(new_w);
            System.out.println("updated");
            for (int j = 0; j < ((StackPane) g.getRoot()).getChildren().size(); j++) {
                if (((StackPane) g.getRoot()).getChildren().get(j) instanceof Pane) {
                    ((Pane) ((StackPane) g.getRoot()).getChildren().get(j)).getChildren().addAll(g.getObsQ(g.getSizeQ()-1).getGroup(),new_s.getGroup(),new_w.getGroup());
                    if(this.game.getObsQ(g.getSizeQ()-1) instanceof doubleCircle){
                        ((Pane) ((StackPane) g.getRoot()).getChildren().get(j)).getChildren().addAll(((doubleCircle)g.getObsQ(g.getSizeQ()-1)).getGroup1());
                    }else if(this.game.getObsQ(g.getSizeQ()-1) instanceof doubleCross){
                        ((Pane) ((StackPane) g.getRoot()).getChildren().get(j)).getChildren().addAll(((doubleCross)g.getObsQ(g.getSizeQ()-1)).getGroup1());
                    }
                    ((Obstacles)this.game.getObsQ(g.getSizeQ()-1)).rotateOn();
                }
            }
        }
    }
    public void startGame(){
        this.game.setOld_time(System.nanoTime());
        Game g=this.game;
        this.game.setTimer(new AnimationTimer() {
            @Override
            public void handle(long now){
                g.settDiff((double)(now - g.getOld_time())/1000000000);
                g.setOld_time(now);
                animateBall();
                try {
                    regCollisionCheck();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(focusObs.getTopY() > g.getMain_ball().getCircle().getLayoutY()){
                    int idx=g.getArr().indexOf(focusObs) + 1;
                    focusObs=(Obstacles) g.getObsQ(idx);
                    System.out.println(focusObs.getClass());
                }
            }
        });
        this.game.getTimer().start();
    }
    public void moveObs(double dist){
        for(int i=0;i<this.game.getSize();i++) {
            this.game.getObs(i).moveDown(dist);
        }
        for(int i=0;i<this.game.getSizeQ();i++) {
            this.game.getObsQ(i).moveDown(dist);
        }
        fun(this.game);
    }
    public void animateBall(){
        double curY=this.game.getMain_ball().getCircle().getLayoutY();
        if(curY>=this.game.getMain_ball().floor && this.game.getMain_ball().vy<=0) {
            this.game.getMain_ball().vy=0;
            return;
        }
        double dist=(this.game.getMain_ball().vy * this.game.getDiff()) - ((this.game.getGravity() * this.game.getDiff() * this.game.getDiff()) / 2);
        double ballCurY=this.game.getMain_ball().getCircle().getLayoutY();
        if(ballCurY-dist<340 ){
            this.moveObs(340-ballCurY+dist);
            this.game.getMain_ball().getCircle().setLayoutY(340);
        }else {
            this.game.getMain_ball().getCircle().setLayoutY(this.game.getMain_ball().getCircle().getLayoutY() - ((this.game.getMain_ball().vy * this.game.getDiff()) - ((this.game.getGravity() * this.game.getDiff() * this.game.getDiff()) / 2)));

        }
        this.game.getMain_ball().posy = (int) this.game.getMain_ball().getCircle().getLayoutY();
        this.game.getMain_ball().vy = (int) (this.game.getMain_ball().vy - this.game.getGravity() * this.game.getDiff());
        this.game.getMain_ball().setCurY();
    }

    public void pauseGame(){
        this.game.getTimer().stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pause.fxml"));
        mediaPlayer.pause();
        Parent root1 = null;
        try {
            root1 = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseController myCon=(pauseController) (loader.getController());
        myCon.init(this.game);
        Scene main1=this.game.getPs().getScene();
        main1.setRoot(root1);
    }
    public void GameOver(Obstacles o) throws InterruptedException {
        this.game.getTimer().stop();
        this.game.getMain_ball().getCircle().setOpacity(0);
        this.collisionAnimation();
        this.game.getMain_ball().getCircle().setOpacity(0);
        Group gr=new Group();
        for(int i=0;i<this.animationBalls.size();i++){
            gr.getChildren().add(this.animationBalls.get(i).getCircle());
        }
        for (int j = 0; j < ((StackPane) this.game.getRoot()).getChildren().size(); j++) {
            if (((StackPane) this.game.getRoot()).getChildren().get(j) instanceof Pane) {
                ((Pane) ((StackPane) this.game.getRoot()).getChildren().get(j)).getChildren().add(gr);
            }
        }
        this.game.setOld_time(System.nanoTime());
        ArrayList <smallBalls> sba=this.animationBalls;
        Game g=this.game;
        this.game.setSmallTimer(new AnimationTimer() {
            @Override
            public void handle(long now){
                g.settDiff((double)(now - g.getOld_time())/1000000000);
                g.setOld_time(now);
                for(int i=0;i<sba.size();i++)
                    animateSmallBall(sba.get(i));
                swarmCollision();
            }
        });
        this.game.getSmallTimer().start();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOver.fxml"));
        Parent root1 = null;
        try {
            root1 = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOverController myCon=(gameOverController) (loader.getController());
        Scene main1=this.game.getPs().getScene();
        Parent r=root1;
        PauseTransition pause = new PauseTransition(
                Duration.seconds(1)
                );
        pause.setOnFinished(event -> {
            myCon.init(this.game, this.app, o, gr);
            //this.animationBalls.clear();
            main1.setRoot(r);
        });
        pause.play();
    }
    ///
    ///
    ///
    //bonus animation work
    public void animateSmallBall(smallBalls sb){
        double curY=sb.getCircle().getLayoutY();
        double dist1=(sb.vy * this.game.getDiff()) - ((this.game.getGravity() * this.game.getDiff() * this.game.getDiff()) / 2);
        double dist2=(sb.vx * this.game.getDiff() );
        double ballCurY=sb.getCircle().getLayoutY();
        sb.getCircle().setLayoutY(sb.getCircle().getLayoutY() - dist1);
        sb.getCircle().setLayoutX(sb.getCircle().getLayoutX() - dist2);
        sb.posy = (int)sb.getCircle().getLayoutY();
        sb.posx = (int)sb.getCircle().getLayoutX();
        sb.vy = (int) (sb.vy - this.game.getGravity() * this.game.getDiff());
        sb.setCurY();
    }
    public void swarmCollision(){
        for(int i=0;i<this.animationBalls.size();i++){
            if(this.animationBalls.get(i).getCircle().getLayoutX()<=0 || this.animationBalls.get(i).getCircle().getLayoutX()>=620){
                this.animationBalls.get(i).setVx(-this.animationBalls.get(i).getVx());
            }
            if(this.animationBalls.get(i).getCircle().getLayoutY()>=669){
                this.animationBalls.get(i).setVy(-this.animationBalls.get(i).getVy());
            }

        }
    }
    public void collisionAnimation(){
        this.animationBalls.clear();
        Random r=new Random();
        ArrayList <String> colors=new ArrayList<>();
        colors.add("#f70578");
        colors.add("#f0f505");
        colors.add("#440580");
        colors.add("#00c8ff");
        int[][] velArray= {{0,-600},{400,-600},{400,400},{-400,600},{250,-400},{250,400},{-250,-600},{-250,400},{0,700},{-400,0},{400,0}};
        int[][] velArray1= {{0,-500},{800,-400},{800,500},{-500,500},{850,-500},{250,500},{-250,-500},{-250,500},{0,500},{-500,0},{500,0}};
        for(int i=0;i<10;i++){
            smallBalls sb=new smallBalls(velArray[i][0],this.game.main_ball.getCircle().getLayoutX(),this.game.main_ball.getCircle().getLayoutY(),velArray[i][1]);
            sb.getCircle().setRadius(4);
            sb.getCircle().setLayoutY(this.game.main_ball.getCircle().getLayoutY());
            sb.getCircle().setLayoutX(this.game.main_ball.getCircle().getLayoutX());
            sb.getCircle().setFill(Paint.valueOf(colors.get(r.nextInt(4))));
            this.animationBalls.add(sb);
        }
        for(int i=0;i<10;i++){
            smallBalls sb=new smallBalls(velArray1[i][0],this.game.main_ball.getCircle().getLayoutX(),this.game.main_ball.getCircle().getLayoutY(),velArray1[i][1]);
            sb.getCircle().setRadius(4);
            sb.getCircle().setLayoutY(this.game.main_ball.getCircle().getLayoutY());
            sb.getCircle().setLayoutX(this.game.main_ball.getCircle().getLayoutX());
            sb.getCircle().setFill(Paint.valueOf(colors.get(r.nextInt(4))));
            this.animationBalls.add(sb);
        }
    }
    ////
    ////
    ////

    public void highlight_On() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0 ;");
    }
    public void highlight_Off() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }


}
