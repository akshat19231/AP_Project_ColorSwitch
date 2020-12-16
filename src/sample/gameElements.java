package sample;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class gameElements implements Serializable {
    double posx;
    double posy;
    private ArrayList<String> colors;
    public gameElements(double x, double y){
        this.posx=x;
        this.posy=y;
        this.colors=new ArrayList<>();
        this.colors.add("#f70578");
        this.colors.add("#f0f505");
        this.colors.add("#440580");
        this.colors.add("#00c8ff");
    }
    public String getRandomColor(){
        Random random = new Random();
        int index = random.nextInt(this.colors.size());
        return this.colors.get(index);
    }
    public abstract void refresh();
    public abstract void setUp();
    public abstract double getPosY();
    public void setPosy(double y){
        this.posy=y;
    }
    public double getX(){
        return posx;
    }
    public double getY(){
        return posy;
    }
    public abstract Boolean collisionCheck(Circle c);
    public abstract Group getGroup();
    public abstract void moveDown(double i);

    public abstract void print();
}
