package sample;

import javafx.scene.Group;

import java.util.ArrayList;

public abstract class Obstacles extends gameElements {
    ArrayList <Integer> colors;
    int type;
    double cury;
    public Obstacles(double x, double y, int a, int b, int c, int d, int type) {
        super(x, y);
        this.colors=new ArrayList<Integer>(4);
        this.colors.add(a);
        this.colors.add(b);
        this.colors.add(c);
        this.colors.add(d);
        this.type=type;
    }
    public double getCurY(){
        return cury;
    }
    public abstract Group getGroup();
    public abstract double getWheelY();
    public abstract double getStarY();
    public abstract void rotateOn();
    public void checkGameOver(){

    }

}
