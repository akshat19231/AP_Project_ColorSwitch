package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class TriangleObs extends Obstacles{
    private transient Line line1;
    private transient Line line2;
    private transient Line line3;
    private transient Group triangle;
    private double centre;
    private double len1;
    private double len2;
    private boolean collided;
    private double offset;
    private double y1,y2,y3,y4;
    private double translationalY1;
    private double translationalY2;
    private transient RotateTransition rotate;
    public TriangleObs(double x, double y, int a, int b, int c, int d, int type, int size1, int size2, double offset) {
        super(x, y, a, b, c, d, type);
        this.line1=new Line();
        this.line2=new Line();
        this.line3=new Line();
        this.triangle= new Group();
        this.len1=size1;
        this.len2=size2;
        this.offset=offset;
        this.y1=330-this.offset;
        this.y2=322-this.offset;
        this.y3=322-this.offset;
        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setDuration(Duration.millis(5000));
        rotate.setNode(this.triangle);
    }

    @Override
    public void moveDown(double y){
        this.line1.setLayoutY(this.line1.getLayoutY() + y);
        this.line2.setLayoutY(this.line2.getLayoutY() + y);
        this.line3.setLayoutY(this.line3.getLayoutY() + y);
        this.centre+=y;
        this.y1=this.line1.getLayoutY();
        this.y2=this.line2.getLayoutY();
        this.y3=this.line3.getLayoutY();
        this.translationalY1+=y;
        this.translationalY2+=y;
        this.setTopY(this.getTopY()+y);
        this.setBottomY(this.getBottomY()+y);
    }
    public void print(){
        System.out.println(this.line1.getLayoutY());
        System.out.println(this.line2.getLayoutY());
        System.out.println(this.line3.getLayoutY());
    }
    public void makeObs(){
        getLine(line1, -159, 0, 171, "#f70578", 1, 0, 383,330, 1);
        getLine(line2, -159,0, 178, "#f0f505",0,1, 412, 322, 2);
        getLine(line3, -159,-0, 171,"#00c8ff",0,1, 528, 322, 3);
        this.translationalY1=this.line1.getLayoutY();
        this.translationalY2=this.line2.getLayoutY();
        triangle.getChildren().addAll(line1,line2,line3);
        this.setTopY(this.line2.getLayoutY() - 10 - 180);
        this.setBottomY(this.line2.getLayoutY() + 10);
    }
    public void setObs(){

        this.line1=new Line();
        this.line2=new Line();
        this.line3=new Line();
        this.triangle= new Group();
        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setDuration(Duration.millis(5000));
        rotate.setNode(this.triangle);
        this.setLine(line1, -159, 0, 178, "#f70578", 1, 0, 383,330, 1, this.y1);
        this.setLine(line2, -159,0, 171, "#f0f505",0,1, 412, 322, 2, this.y2);
        this.setLine(line3, -159,-0, 171,"#00c8ff",0,1, 528, 322, 3, this.y2);
        triangle.getChildren().addAll(line1,line2,line3);
    }
    public void getLine( Line line, double x, double y, int size, String color, int type1, int type2, double lx, double ly, int angle){
        line.setEndX(x+size*type1);
        line.setEndY(y-size*type2);
        line.setStartX(x);
        line.setStartY(y);
        line.setLayoutX(lx);
        line.setLayoutY(ly-this.offset);
        if(angle==1){
        }
        else if(angle==2){
            line.setRotate(32);

        }else {
            line.setRotate(-32);
        }
//        line.setLayoutY(line.getLayoutY()-this.offset1);
        line.setFill(Paint.valueOf(color));
        line.setStyle("-fx-stroke : " + color + "; -fx-stroke-width : 20" + "; -fx-stroke-type : CENTERED" + "; -fx-stroke-line-cap : ROUND" + "; -fx-stroke-line-join : MITER");

    }
    public void setLine(Line line, double x, double y, int size, String color, int type1, int type2, double lx, double ly, int angle, double cur){
        line.setEndX(x+size*type1);
        line.setEndY(y-size*type2);
        line.setStartX(x);
        line.setStartY(y);
        line.setLayoutX(lx);
        line.setLayoutY(ly-this.offset);
        line.setLayoutY(cur);
        if(angle==1){
        }
        else if(angle==2){
            line.setRotate(32);

        }else {
            line.setRotate(-32);
        }
        line.setFill(Paint.valueOf(color));
        line.setStyle( "-fx-stroke : "+ color + "; -fx-stroke-width : 20" + "; -fx-stroke-type : CENTERED" + "; -fx-stroke-line-cap : ROUND" + "; -fx-stroke-line-join : MITER");
    }
    public double getPosY(){
        return this.line1.getLayoutY();
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
        arcArrayList.add(this.line1);
        arcArrayList.add(this.line2);
        arcArrayList.add(this.line3);
        for(int i=0;i<3;i++){
            Shape intersect= Shape.intersect(c,arcArrayList.get(i));
            if (intersect.getBoundsInLocal().getWidth() != -1 ) {
                if(!(c.getFill().toString().equals(arcArrayList.get(i).getFill().toString()))) {
//                    System.out.println("Collision");
                    collided = true;
                    return true;
                }
            }
        }
        return false;
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
        this.rotate(this.triangle,1);
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
        this.rotate(this.triangle,-1);
    }

    @Override
    public void rotateStop() {
        isLeft=false;
        isRight=false;
        this.rotate.stop();
        this.rotate.setDuration(Duration.millis(5000));
    }
    public Group getGroup(){
        return this.triangle;
    }

    @Override
    public double getWheelY() {
        return this.topy - 40;
    }

    @Override
    public double getStarY() {
        return this.bottomy - 85;
    }

    @Override
    public void rotateOn(){
        this.rotate(this.triangle, 1);
    }
    public void rotate(Group g, int mul){
        this.rotate.stop();
        this.rotate.setByAngle(360*mul);
        rotate.play();
    }
}
