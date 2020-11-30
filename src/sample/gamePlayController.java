package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class gamePlayController {
    @FXML
    public Circle ball;
    @FXML
    public Circle c;
    @FXML
    public Line l1;
    @FXML
    public Line l2;
    @FXML
    public Line l3;
    @FXML
    public Line l4;
    @FXML
    private Button backB;
    public Ball main_ball;
    private Stage ps;
    private Parent root;
    private FXMLLoader loader;
    private stars Star;
    private AnimationTimer timer;
    private GraphicsContext gc, ballGC;
    private ArrayList<Obstacles> obs1;
    private long old_time;
    private double tDiff;
    public boolean CLICKED;

    public void init(Stage s, Parent p, FXMLLoader fml, ArrayList<Obstacles> arr) throws IOException {
        ball.setLayoutY(ball.getLayoutY() - 3);
        this.ps=s;
        this.root=p;
        this.obs1=new ArrayList<Obstacles>();
        this.loader=fml;
        this.timer=null;
        this.main_ball=new Ball(0, 310,600);
        System.out.println((int)arr.size());
        ArrayList <Node> toBeAdded= new ArrayList<Node>();
        int mul=1;
//        Group gg=new Group();
//        gg.getChildren().addAll(l1,l2,l3,l4,c);
//        rotateLine(gg);
//        toBeAdded.add(gg);
        for(int i=0;i<arr.size();i++){
            this.obs1.add(arr.get(i));
            toBeAdded.add(arr.get(i).getGroup());
            if(obs1.get(i) instanceof CircleObs) {
                ArrayList<Arc> getArcs = ((CircleObs) this.obs1.get(i)).getArcforRotation();
                for (int j = 0; j < 4; j++) {
                    setRotate(getArcs.get(j), mul);

                }
                if (mul == 1) {
                    mul = -1;
                } else {
                    mul = 1;
                }
            }else{
                rotateLine((Group)toBeAdded.get(toBeAdded.size()-1));
                if (mul == 1) {
                    mul = -1;
                } else {
                    mul = 1;
                }
            }
        }
        assert this.root != null;
        for(int j = 0; j<((StackPane)this.root).getChildren().size(); j++){
            if(((StackPane)this.root).getChildren().get(j) instanceof Pane){
                ((Pane) ((StackPane)this.root).getChildren().get(j)).getChildren().addAll(toBeAdded);
            }
        }

    }

    public void RefreshObs(int idx){
        //System.out.println(o.getPosY());
        if(obs1.get(idx).getPosY()>=799){
            System.out.println(obs1.get(idx).getPosY());
            System.out.println("refreshed");
            obs1.get(idx).moveDown(-1300.0);
        }
    }

    public void startGame(){
        old_time=System.nanoTime();;
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now){
                tDiff=(double)(now - old_time)/1000000000;
                old_time=now;
                animateBall();
            }
        };
        timer.start();
    }
    public void moveObs(double dist){
        for(int i=0;i<this.obs1.size();i++) {
            if (obs1.get(i) instanceof CircleObs) {
                ((CircleObs) this.obs1.get(i)).moveDown(dist);
            }else{
                ((squareObs) this.obs1.get(i)).moveDown(dist);
            }
            RefreshObs(i);
        }
    }
    public void animateBall(){
        double curY=this.ball.getLayoutY();
        if(curY>=this.main_ball.floor && this.main_ball.vy<=0) {
            this.main_ball.vy=0;
            return;
        }
        double dist=(this.main_ball.vy * tDiff) - ((2000 * tDiff * tDiff) / 2);
        double ballCurY=this.ball.getLayoutY();
        if(ballCurY-dist<340 ){
            this.moveObs(340-ballCurY+dist);
            //return;
            this.ball.setLayoutY(340);
        }else {
            this.ball.setLayoutY(this.ball.getLayoutY() - ((this.main_ball.vy * tDiff) - ((2000 * tDiff * tDiff) / 2)));

        }
        this.main_ball.posy = (int) this.ball.getLayoutY();
        this.main_ball.vy = (int) (this.main_ball.vy - 2000 * tDiff);
    }
    public void pauseGame(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pause.fxml"));
        Parent root1 = null;
        try {
            root1 = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseController myCon=(pauseController) (loader.getController());
        myCon.init(this.ps, this.root, this.loader);
        //this.ps.setTitle("Color Switch");
        Scene main1=this.ps.getScene();
        main1.setRoot(root1);
    }
   public void setRotate(Arc arc, int multi) throws IOException{
       Timeline animation = new Timeline(
               new KeyFrame(Duration.ZERO, new KeyValue(arc.startAngleProperty(), arc.getStartAngle(), Interpolator.LINEAR)),
               new KeyFrame(Duration.seconds(5), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360*multi, Interpolator.LINEAR))
       );
       animation.setCycleCount(Animation.INDEFINITE);
       animation.play();
   }
   public void rotateLine(Group l){
       RotateTransition rotate = new RotateTransition();
       rotate.setAxis(Rotate.Z_AXIS);
       rotate.setByAngle(360);
       rotate.setCycleCount(Animation.INDEFINITE);
       rotate.setInterpolator(Interpolator.LINEAR);
       rotate.setDuration(Duration.millis(5000));
       rotate.setNode(l);
       rotate.play();
   }
    public void highlight_On() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0 ;");
    }
    public void highlight_Off() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }


}
