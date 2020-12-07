package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
    public int vy;
    public double posx;
    public double posy;
    public double floor;
    public Circle ball;
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
        this.ball.setLayoutY(569);
        this.ball.setLayoutX(312);
    }
    public Circle getCircle(){
        return this.ball;
    }
    public void update(){

    }
}
