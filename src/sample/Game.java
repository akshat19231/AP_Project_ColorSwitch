package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game {
    private Player player;
    private int level;
    private int GameId;
    private int score;
    public Ball main_ball;
    private Stage ps;
    private Parent root;
    private FXMLLoader loader;
    private stars Star;
    private AnimationTimer timer;
    private long old_time;
    private double tDiff;
    public boolean CLICKED;
    private ArrayList<gameElements> ar;

    public Game(int idx){
        this.GameId=idx;
        this.level=1;
        this.score=0;
        this.ar=new ArrayList<gameElements>();
    }

    public void setMain_ball(Ball main_ball) {
        this.main_ball = main_ball;
    }
    public void setPs(Stage ps){
        this.ps=ps;
    }
    public void setLoader(FXMLLoader loader){
        this.loader=loader;
    }
    public void setRoot(Parent root){
        this.root=root;
    }
    public void setTimer(AnimationTimer timer){
        this.timer=timer;
    }
    public void setOld_time(long old_time){
        this.old_time=old_time;
    }
    public void settDiff(Double tDiff){
        this.tDiff=tDiff;
    }
    public void setPlayer(Player p){
        this.player=p;
    }
    public Ball getMain_ball() {
        return this.main_ball;
    }
    public Stage getPs(){
        return this.ps;
    }
    public FXMLLoader getLoader(){
        return this.loader;
    }
    public Parent getRoot(){
        return this.root;
    }
    public AnimationTimer getTimer(){
        return this.timer;
    }
    public long getOld_time(){
        return this.old_time;
    }
    public Double getDiff(){
        return this.tDiff;
    }
    public int getSize(){
        return this.ar.size();
    }
    public gameElements getObs(int idx){
        return this.ar.get(idx);
    }
    public Player getPlayer(){
        return this.player;
    }
    public void levelUp(){
        this.score++;
        this.level++;
    }
    public void initialiseObs(){
        CircleObs crO=new CircleObs(0,800,0,0,0,0,1);
        crO.makeObs(75);
        this.ar.add(crO);
        CircleObs crO1=new CircleObs(0,400,0,0,0,0,1);
        crO1.makeObs(100);
        this.ar.add(crO1);
//        CircleObs crO2=new CircleObs(0,400,0,0,0,0,1);
//        crO2.makeObs(75);
//        ar.add(crO2);
        squareObs sq1=new squareObs(-80,-21,0,0,0,0,2,123, 98);
        sq1.makeObs();
        this.ar.add(sq1);
        CrossObs co1=new CrossObs(0,1200,0,0,0,0,0);
        co1.makeObs();
        this.ar.add(co1);
        int offset=0;
        for(int i=0;i<4;i++){
            wheel w=new wheel(0,offset);
            w.makeObs();
            this.ar.add(w);
            stars s=new stars(0,offset);
            s.makeObs();
            this.ar.add(s);
            offset+=400;
        }
    }
}
