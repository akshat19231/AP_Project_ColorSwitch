package sample;

import javafx.scene.Group;

import java.util.ArrayList;

public abstract class Obstacles extends gameElements {
    ArrayList <Integer> colors;
    int type;
    public Obstacles(double x, double y, int a, int b, int c, int d, int type) {
        super(x, y);
        this.colors=new ArrayList<Integer>(4);
        this.colors.add(a);
        this.colors.add(b);
        this.colors.add(c);
        this.colors.add(d);
        this.type=type;
    }
    public abstract double getPosY();

    public abstract Group getGroup();

    public void checkGameOver(){

    }


    public abstract void moveDown(double i);
}
