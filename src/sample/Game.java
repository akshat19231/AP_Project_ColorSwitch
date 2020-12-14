package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Game implements Comparable<Game> , Serializable {
    private Player player;
    private int level;
    private int GameId;
    private int score;
    public Ball main_ball;
    private transient Stage ps;
    private transient Parent root;
    private transient FXMLLoader loader;
    private stars Star;
    private transient AnimationTimer timer;
    private transient AnimationTimer smallTimer;
    private long old_time;
    private double tDiff;
    public boolean CLICKED;
    private LinkedList<gameElements> ar;
    public LinkedList <gameElements> obsQ;
    private App app;

    public Game(int idx){
        this.GameId=idx;
        this.level=1;
        this.score=0;
        this.ar=new LinkedList<gameElements>();
        this.obsQ=new LinkedList<gameElements>();
    }
    public void setGameId(){
        this.GameId=1;
    }
    public int getGameId(){
        return this.GameId;
    }
    public void setApp(App a){
        this.app=a;
    }
    public App getApp(){
        return this.app;
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
    public void setSmallTimer(AnimationTimer stimer){
        this.smallTimer=stimer;
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
    public AnimationTimer getSmallTimer(){
        return this.smallTimer;
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
    public gameElements getObsQ(int idx){
        return this.obsQ.get(idx);
    }
    public Player getPlayer(){
        return this.player;
    }
    public void levelUp(){
        this.score++;
        this.level++;
    }
    public void removeQ(){
        this.obsQ.remove(0);
    }
    public void remove(gameElements g){
        this.ar.remove(g);
    }
    public void add(gameElements g){
        this.ar.add(g);
    }
    public int getScore(){
        return this.score;
    }
    public int getLevel(){
        return this.level;
    }
    public int getSizeQ(){
        return this.obsQ.size();
    }
    public void useStars(){
        this.score = this.score - 5;
    }
    public int update(double pos){
        Random random = new Random();
        int index = random.nextInt(4);

        if(index==0){
            CircleObs crO=new CircleObs(0,pos,0,0,0,0,1);
            crO.makeObs(75);
            obsQ.add(crO);
            return 1;
        }else if(index==1){
            squareObs sq1=new squareObs(-80,-21,0,0,0,0,2,123, 98, pos);
            sq1.makeObs();
            obsQ.add(sq1);
            return 1;
        }else if(index==2){
            CrossObs co1=new CrossObs(0,pos,0,0,0,0,0);
            co1.makeObs();
            obsQ.add(co1);
            return 1;
        }else{
            squareObs sq1=new squareObs(-80,-21,0,0,0,0,2,123, 98, pos);
            sq1.makeObs();
            obsQ.add(sq1);
            return 1;
        }
    }
    public void setupObs(){
        for(int i=0;i<ar.size();i++){
            this.ar.get(i).setUp();
        }
        for(int i=0;i<obsQ.size();i++){
            this.obsQ.get(i).refresh();
        }
    }
    public void reset(){
        this.obsQ.clear();
        this.ar.clear();
        this.initialiseObs();
        this.main_ball.getCircle().setLayoutY(569);
    }
    public void initialiseObs(){
        CircleObs crO=new CircleObs(0,1000,0,0,0,0,1);
        crO.makeObs(75);
        wheel w1=new wheel(0,1000);
        w1.makeObs();
        stars s1=new stars(0,1000);
        s1.makeObs();
        squareObs sq1=new squareObs(-80,-21,0,0,0,0,2,123, 98, 0);
        sq1.makeObs();
        wheel w2=new wheel(0,0);
        w2.makeObs();
        stars s2=new stars(0,0);
        s2.makeObs();
        CrossObs co1=new CrossObs(0,500,0,0,0,0,0);
        co1.makeObs();
        wheel w3=new wheel(0,500);
        w3.makeObs();
        stars s3=new stars(0,500);
        s3.makeObs();
        this.obsQ.add(sq1);
        this.ar.add(w2);
        this.ar.add(s2);
        this.obsQ.add(co1);
        this.ar.add(w3);
        this.ar.add(s3);
        this.obsQ.add(crO);
        this.ar.add(w1);
        this.ar.add(s1);
    }
    @Override
    public int compareTo(sample.Game game){
        if(this.getScore() > game.getScore()){
            return -1;
        }
        if(this.getScore() == game.getScore()){
            return 0;
        }
        return 1;
    }
}