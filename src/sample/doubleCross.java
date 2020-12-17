package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class doubleCross extends Obstacles {
    private transient Line l1;
    private transient Line l2;
    private transient Line l3;
    private transient Line l4;
    private transient Group cross;
    private transient Line l11;
    private transient Line l21;
    private transient Line l31;
    private transient Line l41;
    private transient Group cross1;
    private double y1,y2,y3,y4;
    private boolean collided;
    private transient RotateTransition rotate1;
    private transient RotateTransition rotate2;

    public doubleCross(double x, double y, int a, int b, int c, int d, int type) {
        super(x, y, a, b, c, d, type);
        this.l1=new Line();
        this.l2=new Line();
        this.l3=new Line();
        this.l4=new Line();
        this.l11=new Line();
        this.l21=new Line();
        this.l31=new Line();
        this.l41=new Line();
        this.y1=331-this.getY();
        this.y2=310-this.getY();
        this.y3=331-this.getY();
        this.y4=433-this.getY();
        this.cross=new Group();
        this.cross1=new Group();
        rotate1 = new RotateTransition();
        rotate1.setNode(this.cross);
        rotate1.setAxis(Rotate.Z_AXIS);
        rotate1.setCycleCount(Animation.INDEFINITE);
        rotate1.setInterpolator(Interpolator.LINEAR);
        rotate1.setDuration(Duration.millis(5000));
        rotate2 = new RotateTransition();
        rotate2.setNode(this.cross1);
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setInterpolator(Interpolator.LINEAR);
        rotate2.setDuration(Duration.millis(5000));

    }

    public void makeObs(){
        getArc(this.l1, 0, "#f70578",1, 0);
        getArc(this.l2, 90, "#f0f505",0, 1);
        getArc(this.l3, 180, "#440580",1,0);
        getArc(this.l4, 270, "#00c8ff",0, 1);
        getArc(this.l11, 0, "#440580",1, 0);
        getArc(this.l21, 90, "#f0f505",0, 1);
        getArc(this.l31, 180, "#f70578",1,0);
        getArc(this.l41, 270, "#00c8ff",0, 1);
        this.l11.setLayoutX(this.l11.getLayoutX()+112*2);
        this.l21.setLayoutX(this.l21.getLayoutX()+112*2);
        this.l31.setLayoutX(this.l31.getLayoutX()+112*2);
        this.l41.setLayoutX(this.l41.getLayoutX()+112*2);
        cross.getChildren().addAll(l1,l2,l3,l4);
        cross1.getChildren().addAll(l11,l21,l31,l41);
        this.setTopY(this.l1.getLayoutY());
        this.setBottomY(this.l1.getLayoutY());
    }
    public void setObs(){
        this.l1=new Line();
        this.l2=new Line();
        this.l3=new Line();
        this.l4=new Line();
        this.l11=new Line();
        this.l21=new Line();
        this.l31=new Line();
        this.l41=new Line();
        this.cross=new Group();
        this.cross1=new Group();
        setArc(this.l1, 0, "#f70578",1, 0, this.y1);
        setArc(this.l2, 90, "#f0f505",0, 1, this.y2);
        setArc(this.l3, 180, "#440580",1,0, this.y3);
        setArc(this.l4, 270, "#00c8ff",0, 1, this.y4);
        setArc(this.l11, 0, "#440580",1, 0, this.y1);
        setArc(this.l21, 90, "#f0f505",0, 1, this.y2);
        setArc(this.l31, 180, "#f70578",1,0, this.y3);
        setArc(this.l41, 270, "#00c8ff",0, 1, this.y4);
        this.l11.setLayoutX(this.l11.getLayoutX()+112*2);
        this.l21.setLayoutX(this.l21.getLayoutX()+112*2);
        this.l31.setLayoutX(this.l31.getLayoutX()+112*2);
        this.l41.setLayoutX(this.l41.getLayoutX()+112*2);
        cross.getChildren().addAll(l1,l2,l3,l4);
        cross1.getChildren().addAll(l11,l21,l31,l41);
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
    public double getPosY(){
        return this.l1.getLayoutY();
    }

    public void rotateOn(){
        this.rotate(this.cross, 1);
        this.rotate(this.cross1, -1);
    }
    public void rotate(Group g, int mul){
        this.rotate1.stop();
        this.rotate1.setByAngle(360*mul);
        rotate1.play();
        this.rotate2.stop();
        this.rotate2.setByAngle(360*mul*(-1));
        rotate2.play();
    }
    @Override
    public void refresh() {
        this.setObs();
    }

    @Override
    public void setUp() {

    }


    @Override
    public Boolean collisionCheck(Circle c) {
        ArrayList<Line> arcArrayList=new ArrayList<Line>();
        arcArrayList.add(this.l1);
        arcArrayList.add(this.l2);
        arcArrayList.add(this.l3);
        arcArrayList.add(this.l4);
        ArrayList<Line> arcArrayList1=new ArrayList<Line>();
        arcArrayList1.add(this.l11);
        arcArrayList1.add(this.l21);
        arcArrayList1.add(this.l31);
        arcArrayList1.add(this.l41);
        for(int i=0;i<4;i++){
            Shape intersect= Shape.intersect(c,arcArrayList.get(i));
            if (intersect.getBoundsInLocal().getWidth() != -1 ) {
                if(!(c.getFill().toString().equals(arcArrayList.get(i).getStroke().toString()))) {
//                    System.out.println("Collision");
                    collided = true;
                    return true;
                }
            }
            intersect=Shape.intersect(c,arcArrayList1.get(i));
            if (intersect.getBoundsInLocal().getWidth() != -1 ) {
                if(!(c.getFill().toString().equals(arcArrayList1.get(i).getStroke().toString()))) {
//                    System.out.println("Collision");
                    collided = true;
                    return true;
                }
            }
        }
        return false;
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

    public Group getGroup1() {
        return this.cross1;
    }
    @Override
    public void moveDown(double i) {
        this.l1.setLayoutY(this.l1.getLayoutY() + i);
        this.l2.setLayoutY(this.l2.getLayoutY() + i);
        this.l3.setLayoutY(this.l3.getLayoutY() + i);
        this.l4.setLayoutY(this.l4.getLayoutY() + i);
        this.l11.setLayoutY(this.l11.getLayoutY() + i);
        this.l21.setLayoutY(this.l21.getLayoutY() + i);
        this.l31.setLayoutY(this.l31.getLayoutY() + i);
        this.l41.setLayoutY(this.l41.getLayoutY() + i);
        this.y1=this.l1.getLayoutY();
        this.y2=this.l2.getLayoutY();
        this.y3=this.l3.getLayoutY();
        this.y4=this.l4.getLayoutY();
        this.setTopY(this.getTopY()+i);
        this.setBottomY(this.getBottomY()+i);
    }
    @Override
    public void rotateRight() {
        if(isLeft){
            this.rotate1.stop();
            this.rotate1.setDuration(Duration.millis(5000));
            this.rotate2.stop();
            this.rotate2.setDuration(Duration.millis(5000));
        }else{
            this.rotate1.stop();
            Duration x=this.rotate1.getDuration();
            Double y=x.toMillis()==1250? 1250: x.toMillis()-1250;
            this.rotate1.setDuration(Duration.millis(y));
            this.rotate2.stop();
            Duration x1=this.rotate2.getDuration();
            Double y1=x1.toMillis()==1250? 1250: x.toMillis()-1250;
            this.rotate2.setDuration(Duration.millis(y1));
        }
        isLeft=false;
        isRight=true;
        this.rotate(this.cross,1);
    }

    @Override
    public void rotateLeft() {
        if(isRight){
            this.rotate1.stop();
            this.rotate1.setDuration(Duration.millis(5000));
            this.rotate2.stop();
            this.rotate2.setDuration(Duration.millis(5000));
        }else{
            this.rotate1.stop();
            Duration x=this.rotate1.getDuration();
            Double y=x.toMillis()==1250? 1250: x.toMillis()-1250;
            this.rotate1.setDuration(Duration.millis(y));
            this.rotate2.stop();
            Duration x1=this.rotate2.getDuration();
            Double y1=x1.toMillis()==1250? 1250: x.toMillis()-1250;
            this.rotate2.setDuration(Duration.millis(y1));
        }
        isRight=false;
        isLeft=true;
        this.rotate(this.cross,-1);
    }

    @Override
    public void rotateStop() {
        isLeft=false;
        isRight=false;
        this.rotate1.stop();
        this.rotate1.setDuration(Duration.millis(5000));
        this.rotate2.stop();
        this.rotate2.setDuration(Duration.millis(5000));
    }
    @Override
    public void print() {

    }
}
