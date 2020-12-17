package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class wheel extends gameElements{
    private transient Arc arc1;
    private transient Arc arc2;
    private transient Arc arc3;
    private transient Arc arc4;
    private transient Group w;
    private boolean collided;
    private double cur;
    public Boolean isNextRing=false;
    public Boolean isNextTriangle=false;

    public wheel(double x, double y) {
        super(x, y);
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.w= new Group();
        this.collided=false;
        this.cur=this.getY();
    }
    public void moveDown(double y){
        this.arc1.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc2.setLayoutY(this.arc2.getLayoutY() + y);
        this.arc3.setLayoutY(this.arc3.getLayoutY() + y);
        this.arc4.setLayoutY(this.arc4.getLayoutY() + y);
        this.cur+=y;
    }

    @Override
    public void print() {

    }

    public void makeObs(){
        getArc(10,arc1, 0, "#f70578");
        getArc(10,arc2, 90, "#f0f505");
        getArc(10,arc3, 180, "#440580");
        getArc(10,arc4, 270, "#00c8ff");
        this.w.getChildren().addAll(arc1,arc2,arc3,arc4);
    }
    public void setObs(){
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.w= new Group();
        setArc(10,arc1, 0, "#f70578");
        setArc(10,arc2, 90, "#f0f505");
        setArc(10,arc3, 180, "#440580");
        setArc(10,arc4, 270, "#00c8ff");
        this.w.getChildren().addAll(arc1,arc2,arc3,arc4);
    }
    public ArrayList<Arc> getArcforRotation(){
        ArrayList<Arc> arcArrayList=new ArrayList<Arc>();
        return arcArrayList;
    }
    public void getArc(int r, Arc arc, int angle, String color){
        arc.setCenterX(0);
        arc.setCenterY(0);
        arc.setLayoutX(this.getX());
        arc.setLayoutY(this.getY());
        arc.setRadiusX(r);
        arc.setRadiusY(r);
        arc.setStartAngle(angle);
        arc.setLength(90);
        arc.setType(ArcType.ROUND);
        arc.setStyle("-fx-fill: " +color+ "; -fx-stroke : "+color + "; -fx-stroke-width : 1" + "; -fx-stroke-type : INSIDE" + "; -fx-stroke-line-cap : SQUARE" + "; -fx-stroke-line-join : MITER");

    }
    public void setArc(int r, Arc arc, int angle, String color){
        arc.setCenterX(0);
        arc.setCenterY(0);
        arc.setLayoutX(this.getX());
        arc.setLayoutY(this.getY());
        arc.setLayoutY(this.cur);
        arc.setRadiusX(r);
        arc.setRadiusY(r);
        arc.setStartAngle(angle);
        arc.setLength(90);
        arc.setType(ArcType.ROUND);
        arc.setStyle("-fx-fill: " +color+ "; -fx-stroke : "+color + "; -fx-stroke-width : 1" + "; -fx-stroke-type : INSIDE" + "; -fx-stroke-line-cap : SQUARE" + "; -fx-stroke-line-join : MITER");

    }
    public double getPosY(){
        return this.arc1.getLayoutY();
    }

    @Override
    public Boolean collisionCheck(Circle c) {
        if(collided) return false;
        ArrayList<Arc> arcArrayList=new ArrayList<Arc>();
        arcArrayList.add(this.arc1);
        arcArrayList.add(this.arc2);
        arcArrayList.add(this.arc3);
        arcArrayList.add(this.arc4);
        for(int i=0;i<4;i++){
            if (arcArrayList.get(i).getBoundsInParent().intersects(c.getBoundsInParent())) {
                //System.out.println("Collision");
                Paint curColor=c.getFill();
                Paint cr=Paint.valueOf(this.getRandomColor());
                while(cr.equals(curColor)){
                    cr=Paint.valueOf(this.getRandomColor());
                }
                c.setFill(cr);
                collided=true;
                return true;
            }
        }
        return false;
    }
    public void refresh(){
        this.collided=false;
    }

    @Override
    public void setUp() {
        this.setObs();
    }

    public Group getGroup(){
        return this.w;
    }
}
