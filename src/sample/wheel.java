package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class wheel extends gameElements{
    private Arc arc1;
    private Arc arc2;
    private Arc arc3;
    private Arc arc4;
    private Group w;
    private boolean collided;

    public wheel(double x, double y) {
        super(x, y);
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        this.w= new Group();
        this.collided=false;
    }
    public void moveDown(double y){
        this.arc1.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc2.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc3.setLayoutY(this.arc1.getLayoutY() + y);
        this.arc4.setLayoutY(this.arc1.getLayoutY() + y);
    }

    public void makeObs(){
        getArc(10,arc1, 0, "#f70578");
        getArc(10,arc2, 90, "#f0f505");
        getArc(10,arc3, 180, "#440580");
        getArc(10,arc4, 270, "#00c8ff");
        this.w.getChildren().addAll(arc1,arc2,arc3,arc4);
    }
    public ArrayList<Arc> getArcforRotation(){
        ArrayList<Arc> arcArrayList=new ArrayList<Arc>();
        return arcArrayList;
    }
    public void getArc(int r, Arc arc, int angle, String color){
        arc.setCenterX(0);
        arc.setCenterY(0);
        arc.setLayoutX(311-this.getX());
        arc.setLayoutY(120-this.getY());
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
                System.out.println("Collision");
                c.setFill(Paint.valueOf(this.getRandomColor()));
                collided=true;
                return true;
            }
        }
        return false;
    }

    public Group getGroup(){
        return this.w;
    }
}