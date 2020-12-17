package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class CircleObs extends Obstacles {
    private transient Arc arc1;
    private transient Arc arc2;
    private transient Arc arc3;
    private transient Arc arc4;
    private transient Group ring;
    private double centre;
    private boolean collided;
    private int radius;
    private double keep_track;
    private transient RotateTransition rotate;

    public CircleObs(double x, double y, int a, int b, int c, int d, int type) {

        super(x, y, a, b, c, d, type);
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.centre=275-this.getY();
        this.ring= new Group();
        this.keep_track=0-this.getY();
        rotate = new RotateTransition();
        rotate.setNode(this.ring);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setDuration(Duration.millis(5000));
    }

    public double getCentre() {
        return arc1.getCenterY();
    }
    public void moveDown(double y){
        this.arc1.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc2.setLayoutY(this.arc2.getLayoutY() + y);
        this.arc3.setLayoutY(this.arc3.getLayoutY() + y);
        this.arc4.setLayoutY(this.arc4.getLayoutY() + y);
        this.centre+=y;
        this.keep_track=this.arc1.getLayoutY();
        this.setTopY(this.getTopY()+y);
        this.setBottomY(this.getBottomY()+y);
    }

    public void makeObs(int r){
        this.radius=r;
        getArc(r,arc1, 0, "#f70578");
        getArc(r,arc2, 90, "#f0f505");
        getArc(r,arc3, 180, "#440580");
        getArc(r,arc4, 270, "#00c8ff");
        ring.getChildren().addAll(arc1,arc2,arc3,arc4);
        this.setTopY(this.centre-this.radius);
        this.setBottomY(this.centre+this.radius);
    }
    public void changeSpeed(double y){
        Double x=rotate.getDuration().toMillis();
        rotate.setDuration(Duration.millis(x+y));
    }
    public void setObs(int r){

        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.ring= new Group();
        rotate = new RotateTransition();
        rotate.setNode(this.ring);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setDuration(Duration.millis(5000));
        setArc(r,arc1, 0, "#f70578");
        setArc(r,arc2, 90, "#f0f505");
        setArc(r,arc3, 180, "#440580");
        setArc(r,arc4, 270, "#00c8ff");
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
        for(int i=0;i<4;i++){
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
        return this.centre;
    }

    @Override
    public void rotateOn() {
//        this.rotate(this.arc1, 1);
//        this.rotate(this.arc2, 1);
//        this.rotate(this.arc3, 1);
//        this.rotate(this.arc4, 1);
        this.rotate(this.ring,1);
    }

    @Override
    public void rotateRight() {
        if(isLeft){
            this.rotate.stop();
            this.rotate.setDuration(Duration.millis(5000));
        }else{
            this.rotate.stop();
            Duration x=this.rotate.getDuration();
            Double y=x.toMillis()==1250? 1250: x.toMillis()-1250;
            this.rotate.setDuration(Duration.millis(y));
        }
        isLeft=false;
        isRight=true;
        this.rotate(this.ring,1);
    }

    @Override
    public void rotateLeft() {
        if(isRight){
            this.rotate.stop();
            this.rotate.setDuration(Duration.millis(5000));
        }else{
            this.rotate.stop();
            Duration x=this.rotate.getDuration();
            Double y=x.toMillis()==1250? 1250: x.toMillis()-1250;
            this.rotate.setDuration(Duration.millis(y));
        }
        isRight=false;
        isLeft=true;
        this.rotate(this.ring,-1);
    }

    @Override
    public void rotateStop() {
        isLeft=false;
        isRight=false;
        this.rotate.stop();
        this.rotate.setDuration(Duration.millis(5000));
    }

    public void rotate(Group g, int multi){
//        Timeline animation = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(arc.startAngleProperty(), arc.getStartAngle(), Interpolator.LINEAR)),
//                new KeyFrame(Duration.seconds(5), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360*multi, Interpolator.LINEAR))
//        );
//        animation.setCycleCount(Animation.INDEFINITE);
//        animation.play();
        //this.rotate.stop();
        this.rotate.setByAngle(360*multi);
        rotate.play();
    }
}
