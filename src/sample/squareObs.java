package sample;

import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class squareObs extends Obstacles{
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private Group rect;
    private double centre;
    private double len1;
    private double len2;

    public squareObs(double x, double y, int a, int b, int c, int d, int type, int size1, int size2) {

        super(x, y, a, b, c, d, type);
        this.line1=new Line();
        this.line2=new Line();
        this.line3=new Line();
        this.line4=new Line();
        this.rect= new Group();
        this.len1=size1;
        this.len2=size2;

    }

//    public double getCentre() {
//        return arc1.getCenterY();
//    }
    @Override
    public void moveDown(double y){
        this.line1.setLayoutY(this.line1.getLayoutY() + y);
        this.line2.setLayoutY(this.line2.getLayoutY() + y);
        this.line3.setLayoutY(this.line3.getLayoutY() + y);
        this.line4.setLayoutY(this.line4.getLayoutY() + y);
        this.centre+=y;
    }

    public void makeObs(){
        getLine(line1, -3, 0, 123, "#f70578", 1, 0, 386,328, 2);
        getLine(line2, 123,-114, 123, "#f0f505",1,0, 240, 324, 3);
        getLine(line3, -100,-0, 98,"#440580",0,1, 340, 328, 1);
        getLine(line4, -100,-21, 98, "#00c8ff",0,1,483, 329, 4);
        rect.getChildren().addAll(line1,line2,line3,line4);
    }
    public ArrayList<Line> getLineforRotation(){
        ArrayList<Line> lineArrayList=new ArrayList<Line>();
        lineArrayList.add(this.line1);
        lineArrayList.add(this.line2);
        lineArrayList.add(this.line3);
        lineArrayList.add(this.line4);
        return lineArrayList;
    }
    public void getLine( Line line, double x, double y, int size, String color, int type1, int type2, double lx, double ly, int angle){
        line.setEndX(x-size*type1);
        line.setEndY(y-size*type2);
        line.setStartX(x);
        line.setStartY(y);
        line.setLayoutX(lx);
        line.setLayoutY(ly);
        if(angle==1){
            line.setRotate(15);
            line.setEndY(line.getEndY()-20);
        }
        else if(angle==2){
            line.setLayoutX(line.getLayoutX()+(Math.tan(15)*(double)49) +10);

        }else if(angle==3){
            line.setLayoutX(line.getLayoutX()-(Math.tan(15)*(double)49) -10);

        }else if(angle==4){
            line.setRotate(15);
            line.setStartY(line.getStartY()+20);
        }
        line.setStyle( "-fx-stroke : "+ color + "; -fx-stroke-width : 20" + "; -fx-stroke-type : CENTERED" + "; -fx-stroke-line-cap : ROUND" + "; -fx-stroke-line-join : MITER");
    }
    public double getPosY(){
        return this.line1.getLayoutY();
    }
    public Group getGroup(){
        return this.rect;
    }
}