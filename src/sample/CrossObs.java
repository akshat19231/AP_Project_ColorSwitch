package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class CrossObs extends Obstacles{
    private transient Line l1;
    private transient Line l2;
    private transient Line l3;
    private transient Line l4;
    private transient Group cross;
    private double y1,y2,y3,y4;
    private boolean collided;
    private transient RotateTransition rotate;
    public CrossObs(double x, double y, int a, int b, int c, int d, int type) {
        super(x, y, a, b, c, d, type);
        this.l1=new Line();
        this.l2=new Line();
        this.l3=new Line();
        this.l4=new Line();
        this.y1=331-this.getY();
        this.y2=310-this.getY();
        this.y3=331-this.getY();
        this.y4=433-this.getY();
        this.cross=new Group();
        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setDuration(Duration.millis(5000));
        rotate.setNode(this.cross);
    }
    public void makeObs(){
        getArc(this.l1, 0, "#f70578",1, 0);
        getArc(this.l2, 90, "#f0f505",0, 1);
        getArc(this.l3, 180, "#440580",1,0);
        getArc(this.l4, 270, "#00c8ff",0, 1);
        cross.getChildren().addAll(l1,l2,l3,l4);
        this.setTopY(this.l1.getLayoutY());
        this.setBottomY(this.l1.getLayoutY());
    }
    public void changeSpeed(double y){
        Double x=rotate.getDuration().toMillis();
        rotate.setDuration(Duration.millis(x+y));
    }
    public void setObs(){

        this.l1=new Line();
        this.l2=new Line();
        this.l3=new Line();
        this.l4=new Line();
        this.cross=new Group();
        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setDuration(Duration.millis(5000));
        rotate.setNode(this.cross);
        setArc(this.l1, 0, "#f70578",1, 0, this.y1);
        setArc(this.l2, 90, "#f0f505",0, 1, this.y2);
        setArc(this.l3, 180, "#440580",1,0, this.y3);
        setArc(this.l4, 270, "#00c8ff",0, 1, this.y4);
        cross.getChildren().addAll(l1,l2,l3,l4);
    }
    public void getArc(Line l, int angle, String color, int ori1, int ori2){
        double x1; double y1; double lx; double ly;
        if(angle==0){
            y1=0; x1=-18; lx=323;ly=331;
        }else if(angle==90){
            x1=-100; y1=0; lx=303;ly=310;
        }else if(angle==180){
            x1=-18; y1=0; lx=200;ly=331;
        }else{
            x1=-100; y1=0; lx=303;ly=433;
        }
        l.setLayoutX(lx);
        l.setStartX(x1);l.setEndX(x1-ori1*82);
        l.setStartY(y1);l.setEndY(y1-ori2*82);
        l.setLayoutY(ly-this.getY());
        l.setStyle("-fx-stroke : "+color + "; -fx-stroke-width : 20" + "; -fx-stroke-type : CENTERED" + "; -fx-stroke-line-cap : ROUND" + "; -fx-stroke-line-join : MITER");

    }
    public void setArc(Line l, int angle, String color, int ori1, int ori2, double cur){
        double x1; double y1; double lx; double ly;
        if(angle==0){
            y1=0; x1=-18; lx=323;ly=331;
        }else if(angle==90){
            x1=-100; y1=0; lx=303;ly=310;
        }else if(angle==180){
            x1=-18; y1=0; lx=200;ly=331;
        }else{
            x1=-100; y1=0; lx=303;ly=433;
        }
        l.setLayoutX(lx);
        l.setStartX(x1);l.setEndX(x1-ori1*82);
        l.setStartY(y1);l.setEndY(y1-ori2*82);
        l.setLayoutY(ly-this.getY());
        l.setLayoutY(cur);
        l.setStyle("-fx-stroke : "+color + "; -fx-stroke-width : 20" + "; -fx-stroke-type : CENTERED" + "; -fx-stroke-line-cap : ROUND" + "; -fx-stroke-line-join : MITER");

    }

    @Override
    public void refresh() {
        this.setObs();
    }

    @Override
    public void setUp() {

    }

    @Override
    public double getPosY(){
        return this.l1.getLayoutY();
    }

    @Override
    public Boolean collisionCheck(Circle c) {

        ArrayList<Line> arcArrayList=new ArrayList<Line>();
        arcArrayList.add(this.l1);
        arcArrayList.add(this.l2);
        arcArrayList.add(this.l3);
        arcArrayList.add(this.l4);
        for(int i=0;i<4;i++){
            Shape intersect= Shape.intersect(c,arcArrayList.get(i));
            if (intersect.getBoundsInLocal().getWidth() != -1 ) {
                if(!(c.getFill().toString().equals(arcArrayList.get(i).getStroke().toString()))) {
//                    System.out.println("Collision");
                    collided = true;
                    return true;
                }
            }
        }
        return false;
    }
    public void print(){
        System.out.println(this.l1.getLayoutY());
        System.out.println(this.l2.getLayoutY());
        System.out.println(this.l3.getLayoutY());
        System.out.println(this.l4.getLayoutY());
    }

    @Override
    public Group getGroup() {
        return this.cross;
    }

    @Override
    public double getWheelY() {
        return this.l1.getLayoutY() - 118;
    }

    @Override
    public double getStarY() {
        return this.l1.getLayoutY() - 80;
    }

    @Override
    public void rotateOn(){
        this.rotate(this.cross, 1);
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
        this.rotate(this.cross,1);
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
        this.rotate(this.cross,-1);
    }

    @Override
    public void rotateStop() {
        isLeft=false;
        isRight=false;
        this.rotate.stop();
        this.rotate.setDuration(Duration.millis(5000));
    }

    public void rotate(Group g, int mul){
        this.rotate.stop();
        this.rotate.setByAngle(360*mul);
        rotate.play();
    }

    public void moveDown(double y){
        this.l1.setLayoutY(this.l1.getLayoutY() + y);
        this.l2.setLayoutY(this.l2.getLayoutY() + y);
        this.l3.setLayoutY(this.l3.getLayoutY() + y);
        this.l4.setLayoutY(this.l4.getLayoutY() + y);
        this.y1=this.l1.getLayoutY();
        this.y2=this.l2.getLayoutY();
        this.y3=this.l3.getLayoutY();
        this.y4=this.l4.getLayoutY();
        this.setTopY(this.getTopY()+y);
        this.setBottomY(this.getBottomY()+y);
    }
}
