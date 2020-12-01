package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class CircleObs extends Obstacles {
    private Arc arc1;
    private Arc arc2;
    private Arc arc3;
    private Arc arc4;
    private Group ring;
    private double centre;
    private boolean collided;

    public CircleObs(double x, double y, int a, int b, int c, int d, int type) {

        super(x, y, a, b, c, d, type);
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.ring= new Group();

    }

    public double getCentre() {
        return arc1.getCenterY();
    }
    public void moveDown(double y){
        this.arc1.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc2.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc3.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc4.setLayoutY(this.arc1.getLayoutY() + y);
        this.centre+=y;
    }

    public void makeObs(int r){
        getArc(r,arc1, 0, "#f70578");
        getArc(r,arc2, 90, "#f0f505");
        getArc(r,arc3, 180, "#440580");
        getArc(r,arc4, 270, "#00c8ff");
        ring.getChildren().addAll(arc1,arc2,arc3,arc4);
    }
    public ArrayList<Arc> getArcforRotation(){
        ArrayList<Arc> arcArrayList=new ArrayList<Arc>();
        arcArrayList.add(this.arc1);
        arcArrayList.add(this.arc2);
        arcArrayList.add(this.arc3);
        arcArrayList.add(this.arc4);
        return arcArrayList;
    }
    public void getArc(int r, Arc arc, int angle, String color){
        arc.setCenterX(312);
        arc.setCenterY(275);
        arc.setLayoutX(0-this.getX());
        arc.setLayoutY(0-this.getY());
        arc.setRadiusX(r);
        arc.setRadiusY(r);
        arc.setStartAngle(angle);
        arc.setLength(90);
        arc.setFill(Color.BLUE);
        arc.setStyle("-fx-fill: NULL" + "; -fx-stroke : "+color + "; -fx-stroke-width : 10" + "; -fx-stroke-type : CENTERED" + "; -fx-stroke-line-cap : BUTT" + "; -fx-stroke-line-join : MITER");

    }
    public double getPosY(){
        return this.arc1.getLayoutY();
    }

    @Override
    public Boolean collisionCheck(Circle c) {

        ArrayList<Arc> arcArrayList=new ArrayList<Arc>();
        arcArrayList.add(this.arc1);
        arcArrayList.add(this.arc2);
        arcArrayList.add(this.arc3);
        arcArrayList.add(this.arc4);
        for(int i=0;i<4;i++){
            Shape intersect= Shape.intersect(c,arcArrayList.get(i));
            if ( intersect.getBoundsInLocal().getWidth() != -1) {
                if(!(c.getFill().equals(arcArrayList.get(i).getStroke()))) {
                    System.out.println("Collision");
                    System.exit(0);
                    collided = true;
                    return true;
                }
            }
        }
        return false;
    }

    public Group getGroup(){
        return this.ring;
    }
}
