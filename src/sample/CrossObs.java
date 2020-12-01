package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class CrossObs extends Obstacles{
    private Line l1;
    private Line l2;
    private Line l3;
    private Line l4;
    private Circle c;
    private Group cross;
    private boolean collided;
    public CrossObs(double x, double y, int a, int b, int c, int d, int type) {
        super(x, y, a, b, c, d, type);
        this.l1=new Line();
        this.l2=new Line();
        this.l3=new Line();
        this.l4=new Line();
        this.c=new Circle();
        this.cross=new Group();
    }
    public void makeObs(){
        getArc(this.l1, 0, "#f70578",1, 0);
        getArc(this.l2, 90, "#f0f505",0, 1);
        getArc(this.l3, 180, "#440580",1,0);
        getArc(this.l4, 270, "#00c8ff",0, 1);
        c.setCenterX(0);
        c.setCenterY(0);
        c.setLayoutY(331);
        c.setLayoutX(203);
        c.setRadius(11);
        c.setFill(Color.BLACK);
        cross.getChildren().addAll(l1,l2,l3,l4);
    }
    public ArrayList<Line> getLineforRotation(){
        ArrayList<Line> lineArrayList=new ArrayList<Line>();
        lineArrayList.add(this.l1);
        lineArrayList.add(this.l2);
        lineArrayList.add(this.l3);
        lineArrayList.add(this.l4);
        return lineArrayList;
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
                    System.out.println("Collision");
                    System.exit(0);
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
    public void moveDown(double y){
        this.l1.setLayoutY(this.l1.getLayoutY() + y);
        this.l2.setLayoutY(this.l2.getLayoutY() + y);
        this.l3.setLayoutY(this.l3.getLayoutY() + y);
        this.l4.setLayoutY(this.l4.getLayoutY() + y);
        this.c.setLayoutY(this.c.getLayoutY() + y);
    }
}
