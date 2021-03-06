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
import java.util.concurrent.ThreadLocalRandom;

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
    private int gravity;
    private Boolean mode;

    public Game(int idx){
        this.GameId=idx;
        this.level=1;
        this.score=0;
        this.ar=new LinkedList<gameElements>();
        this.obsQ=new LinkedList<gameElements>();
        this.gravity=2000;
        this.mode=false;
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
    public LinkedList<gameElements> getArr(){
        return this.obsQ;
    }
    public void levelUp(){
        this.score++;
        this.level++;
    }
    public Boolean getMode(){
        return this.mode;
    }
    public void toggleMode(){
        if(this.mode){
            this.mode=false;
        }else{
            this.mode=true;
        }
    }
    public void setGravity(int g){
        this.gravity=g;
    }
    public int getGravity(){
        return this.gravity;
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
        int base_line=0; int top_line=7;
        if(this.score<=5){
            base_line=0; top_line=3;
        }else if(this.score>5 && this.score<=10){
            base_line=2; top_line=5;
        }else if(this.score>10 && this.score<=15){
            base_line=4; top_line=7;
        }else{
            base_line=0; top_line=7;
        }
        int index = ThreadLocalRandom.current().nextInt(base_line, 7);
//        index=3;
        if(index==0){
            CircleObs crO=new CircleObs(0,pos,0,0,0,0,1);
            if(this.score>5 && this.score<=7)
                crO.makeObs(100);
            else
                crO.makeObs(75);
            if(this.score>5){
                crO.changeSpeed(-1000);
            }
            if(this.score>8){
                crO.changeSpeed(-500);
            }
            obsQ.add(crO);
            return 1;
        }else if(index==1){
            CrossObs co1=new CrossObs(0,pos,0,0,0,0,0);
            co1.makeObs();
            if(this.score>5){
                co1.changeSpeed(-1000);
            }
            if(this.score>8){
                co1.changeSpeed(-500);
            }
            obsQ.add(co1);

            return 1;
        }else if(index==2){
            squareObs sq1=new squareObs(-80,-21,1,0,0,0,2,123, 98, 0, pos);
            sq1.makeObs();
            if(this.score>5){
                sq1.changeSpeed(-1000);
            }
            if(this.score>8){
                sq1.changeSpeed(-500);
            }
            obsQ.add(sq1);
            return 1;
        }else if(index==3){
            doubleCircle dcr1=new doubleCircle(0,pos,0,0,0,0,1, 1);
            if(this.score>5 && this.score<=7)
                dcr1.makeObs(70);
            else if(this.score>7)
                dcr1.makeObs(85);
            else
                dcr1.makeObs(100);
            if(this.score>5){
                dcr1.changeSpeed(-1000);
            }
            if(this.score>8){
                dcr1.changeSpeed(-500);
            }
            obsQ.add(dcr1);
            return 1;
        }else if(index==4){
            squareObs sq1=new squareObs(-80,-21,0,0,0,0,2,123, 98, 0, pos);
            sq1.makeObs();
            if(this.score>5){
                sq1.changeSpeed(-1000);
            }
            if(this.score>8){
                sq1.changeSpeed(-500);
            }
            obsQ.add(sq1);
            return 1;
        }else if(index==5){
            doubleCircle dcr1=new doubleCircle(0,pos,0,0,0,0,1, 0);
            if(this.score>5)
                dcr1.makeObs(75);
            else
                dcr1.makeObs(100);
            if(this.score>5){
                dcr1.changeSpeed(-1000);
            }
            if(this.score>8){
                dcr1.changeSpeed(-500);
            }
            obsQ.add(dcr1);
            return 1;
        }else{
            doubleCross dco1=new doubleCross(0,pos,0,0,0,0,0);
            dco1.makeObs();
            if(this.score>5){
                dco1.changeSpeed(-1000);
            }
            if(this.score>8){
                dco1.changeSpeed(-500);
            }
            obsQ.add(dco1);
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
            int index = random.nextInt(3);
//            index=5;
            double starY = 0;
            double wheelY = 0;
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
            }else{
                CrossObs co1=new CrossObs(0,offset,0,0,0,0,0);
                co1.makeObs();
                obsQ.add(co1);
                starY=co1.getStarY();
                wheelY=co1.getWheelY();
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