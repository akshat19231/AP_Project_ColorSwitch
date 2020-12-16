package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Ball implements Serializable {
    public int vy;
    public double posx;
    public double posy;
    public double floor;
    public transient Circle ball;
    private double cury;
    public Ball(int v, double x, double y){
        this.vy=v;
        this.posx=x;
        this.posy=y;
        this.floor=y;
        this.ball=new Circle();
        this.ball.setFill(Paint.valueOf("#00c8ff"));
        this.ball.setRadius(9);
        this.ball.setCenterY(0);
        this.ball.setCenterX(0);
        this.ball.setLayoutY(500);
        this.ball.setLayoutX(312);
        this.cury=569;
    }
    public void setCurY(){
        this.cury=this.ball.getLayoutY();
    }
    public void setUp(){
        this.ball=new Circle();
        this.ball.setFill(Paint.valueOf("#00c8ff"));
        this.ball.setRadius(9);
        this.ball.setCenterY(0);
        this.ball.setCenterX(0);
        this.ball.setLayoutY(this.cury);
        this.ball.setLayoutX(312);
    }
    public void setVy(int vy){
        this.vy=vy;
    }
    public int getVy(){
        return this.vy;
    }
    public Circle getCircle(){
        return this.ball;
    }
    public void update(){

    }
}
