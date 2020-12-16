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
    public int hello = 0;
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
        int index = random.nextInt(6);
//        index=0;
        if(index==0){
            CircleObs crO=new CircleObs(0,pos,0,0,0,0,1);
            crO.makeObs(75);
            obsQ.add(crO);
            return 1;
        }else if(index==1){
            squareObs sq1=new squareObs(-80,-21,0,0,0,0,2,123, 98, 0, pos);
            sq1.makeObs();
            obsQ.add(sq1);
            return 1;
        }else if(index==2){
            CrossObs co1=new CrossObs(0,pos,0,0,0,0,0);
            co1.makeObs();
            obsQ.add(co1);
            return 1;
        }else if(index==3){
            doubleCross dco1=new doubleCross(0,pos,0,0,0,0,0);
            dco1.makeObs();
            obsQ.add(dco1);
            return 1;
        }else if(index==4){
            doubleCircle dcr1=new doubleCircle(0,pos,0,0,0,0,1, 1);
            dcr1.makeObs(100);
            obsQ.add(dcr1);
            return 1;
        }else{
            doubleCircle dcr1=new doubleCircle(0,pos,0,0,0,0,1, 0);
            dcr1.makeObs(100);
            obsQ.add(dcr1);
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
    public void initialiseObs(){
        Random random = new Random();
        double offset=0;
        for(int i=0;i<3;i++){
            int index = random.nextInt(6);
//            index=0;
            double starY;
            double wheelY;
            if(index==0){
                CircleObs crO=new CircleObs(0,offset,0,0,0,0,1);
                crO.makeObs(75);
                obsQ.add(crO);
                starY=crO.getStarY();
                wheelY=crO.getWheelY();
            }else if(index==1){
                squareObs sq1=new squareObs(-80,-21,0,0,0,0,2,123, 98, 0, offset);
                sq1.makeObs();
                obsQ.add(sq1);
                starY=sq1.getStarY();
                wheelY=sq1.getWheelY();
            }else if(index==2){
                CrossObs co1=new CrossObs(0,offset,0,0,0,0,0);
                co1.makeObs();
                obsQ.add(co1);
                starY=co1.getStarY();
                wheelY=co1.getWheelY();
            }else if(index==3){
                doubleCross dco1=new doubleCross(0,offset,0,0,0,0,0);
                dco1.makeObs();
                obsQ.add(dco1);
                starY=dco1.getStarY();
                wheelY=dco1.getWheelY();
            }else if(index==4){
                doubleCircle dcr1=new doubleCircle(0,offset,0,0,0,0,1, 1);
                dcr1.makeObs(100);
                obsQ.add(dcr1);
                starY=dcr1.getStarY();
                wheelY=dcr1.getWheelY();
            }else{
                doubleCircle dcr1=new doubleCircle(0,offset,0,0,0,0,1, 0);
                dcr1.makeObs(100);
                obsQ.add(dcr1);
                starY=dcr1.getStarY();
                wheelY=dcr1.getWheelY();
            }
            wheel w=new wheel(311, wheelY);
            w.makeObs();
            stars s=new stars(302, starY);
            s.makeObs();
            this.ar.add(w);
            this.ar.add(s);
            offset+=450;
        }

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