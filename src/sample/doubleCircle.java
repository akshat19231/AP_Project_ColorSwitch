package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class doubleCircle extends Obstacles {
    private transient Arc arc1;
    private transient Arc arc2;
    private transient Arc arc3;
    private transient Arc arc4;
    private transient Arc arc11;
    private transient Arc arc21;
    private transient Arc arc31;
    private transient Arc arc41;
    private transient Group ring;
    private transient Group ring1;
    private double centre;
    private boolean collided;
    private int radius;
    private int radius1;
    private int outside;
    private double keep_track;


    public doubleCircle(double x, double y, int a, int b, int c, int d, int type, int outside) {

        super(x, y, a, b, c, d, type);
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.centre=275-this.getY();
        this.keep_track=0-this.getY();
        this.ring= new Group();
        this.arc11=new Arc();
        this.arc21=new Arc();
        this.arc31=new Arc();
        this.arc41=new Arc();
        this.ring1= new Group();
        this.outside=outside;

    }
    public double getCentre() {
        return arc1.getCenterY();
    }
    public void moveDown(double y){
        this.arc1.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc2.setLayoutY(this.arc2.getLayoutY() + y);
        this.arc3.setLayoutY(this.arc3.getLayoutY() + y);
        this.arc4.setLayoutY(this.arc4.getLayoutY() + y);
        this.arc11.setLayoutY(this.arc11.getLayoutY() + y);
        this.arc21.setLayoutY(this.arc21.getLayoutY() + y);
        this.arc31.setLayoutY(this.arc31.getLayoutY() + y);
        this.arc41.setLayoutY(this.arc41.getLayoutY() + y);
        this.centre+=y;
        this.keep_track=this.arc1.getLayoutY();
    }

    public void makeObs(int r){
        this.radius=r;
        getArc(r,this.arc1, 0, "#f70578");
        getArc(r,this.arc2, 90, "#f0f505");
        getArc(r,this.arc3, 180, "#440580");
        getArc(r,this.arc4, 270, "#00c8ff");
        this.ring.getChildren().addAll(this.arc1,this.arc2,this.arc3,this.arc4);
        if(this.outside==0) {
            getArc(r - 20, this.arc11, 0, "#00c8ff");
            getArc(r - 20, this.arc21, 90, "#f70578");
            getArc(r - 20, this.arc31, 180, "#f0f505");
            getArc(r - 20, this.arc41, 270, "#440580");
        }else{
            getArc(r , this.arc11, 0, "#f0f505");
            getArc(r , this.arc21, 90, "#f70578");
            getArc(r , this.arc31, 180, "#00c8ff");
            getArc(r , this.arc41, 270, "#440580");
            this.arc11.setLayoutX(this.arc11.getLayoutX()+r+5);
            this.arc21.setLayoutX(this.arc21.getLayoutX()+r+5);
            this.arc31.setLayoutX(this.arc31.getLayoutX()+r+5);
            this.arc41.setLayoutX(this.arc41.getLayoutX()+r+5);
            this.arc1.setLayoutX(this.arc1.getLayoutX()-r-5);
            this.arc2.setLayoutX(this.arc2.getLayoutX()-r-5);
            this.arc3.setLayoutX(this.arc3.getLayoutX()-r-5);
            this.arc4.setLayoutX(this.arc4.getLayoutX()-r-5);

        }
        this.ring1.getChildren().addAll(this.arc11,this.arc21,this.arc31,this.arc41);
    }
    public void rotateOn(){
        this.rotate(this.arc1, 1);
        this.rotate(this.arc2, 1);
        this.rotate(this.arc3, 1);
        this.rotate(this.arc4, 1);
        this.rotate(this.arc11, -1);
        this.rotate(this.arc21, -1);
        this.rotate(this.arc31, -1);
        this.rotate(this.arc41, -1);
    }
    public void rotate(Arc arc, int multi){
        Timeline animation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(arc.startAngleProperty(), arc.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(5), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360*multi, Interpolator.LINEAR))
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }
    public void setObs(int r){
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.ring= new Group();
        this.arc11=new Arc();
        this.arc21=new Arc();
        this.arc31=new Arc();
        this.arc41=new Arc();
        this.ring1= new Group();
        setArc(r,arc1, 0, "#f70578");
        setArc(r,arc2, 90, "#f0f505");
        setArc(r,arc3, 180, "#440580");
        setArc(r,arc4, 270, "#00c8ff");
        ring.getChildren().addAll(arc1,arc2,arc3,arc4);
        if(this.outside==0) {
            setArc(r - 20, this.arc11, 0, "#00c8ff");
            setArc(r - 20, this.arc21, 90, "#f70578");
            setArc(r - 20, this.arc31, 180, "#f0f505");
            setArc(r - 20, this.arc41, 270, "#440580");
        }else{
            setArc(r , this.arc11, 0, "#f0f505");
            setArc(r , this.arc21, 90, "#f70578");
            setArc(r , this.arc31, 180, "#00c8ff");
            setArc(r , this.arc41, 270, "#440580");
            this.arc11.setLayoutX(this.arc11.getLayoutX()+r+5);
            this.arc21.setLayoutX(this.arc21.getLayoutX()+r+5);
            this.arc31.setLayoutX(this.arc31.getLayoutX()+r+5);
            this.arc41.setLayoutX(this.arc41.getLayoutX()+r+5);
            this.arc1.setLayoutX(this.arc1.getLayoutX()-r-5);
            this.arc2.setLayoutX(this.arc2.getLayoutX()-r-5);
            this.arc3.setLayoutX(this.arc3.getLayoutX()-r-5);
            this.arc4.setLayoutX(this.arc4.getLayoutX()-r-5);
        }
        this.ring1.getChildren().addAll(this.arc11,this.arc21,this.arc31,this.arc41);
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
    public void setArc(int r, Arc arc, int angle, String color){
        arc.setCenterX(312);
        arc.setCenterY(275);
        arc.setLayoutX(0-this.getX());
        arc.setLayoutY(this.keep_track);
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
    public void print(){
        System.out.println(this.arc1.getLayoutY());
        System.out.println(this.arc2.getLayoutY());
        System.out.println(this.arc3.getLayoutY());
        System.out.println(this.arc4.getLayoutY());
    }
    @Override
    public Boolean collisionCheck(Circle c) {

        ArrayList<Arc> arcArrayList=new ArrayList<Arc>();
        arcArrayList.add(this.arc1);
        arcArrayList.add(this.arc2);
        arcArrayList.add(this.arc3);
        arcArrayList.add(this.arc4);
        arcArrayList.add(this.arc11);
        arcArrayList.add(this.arc21);
        arcArrayList.add(this.arc31);
        arcArrayList.add(this.arc41);
        for(int i=0;i<8;i++){
            Shape intersect= Shape.intersect(c,arcArrayList.get(i));
            if ( intersect.getBoundsInLocal().getWidth() != -1) {
                if(!(c.getFill().equals(arcArrayList.get(i).getStroke()))) {
//                    System.out.println("Collision");
                    collided = true;
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void refresh() {
        this.setObs(this.radius);
    }

    @Override
    public void setUp() {

    }

    public Group getGroup(){
        return this.ring;
    }

    @Override
    public double getWheelY() {
        return this.centre - this.radius - 60;
    }

    @Override
    public double getStarY() {
        if(this.outside==0){
            return this.centre;
        }else{
            return this.centre -90;
        }
    }

    public Group getGroup1(){
        return this.ring1;
    }
}