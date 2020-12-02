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
    private Game game;

    public void init(Stage s, Parent p, FXMLLoader fml, ArrayList<gameElements> arr, Game g) throws IOException {
        this.game=g;
        this.game.initialiseObs();
        ball.setLayoutY(ball.getLayoutY() - 3);
        this.game.setPs(s);
        this.game.setRoot(p);
        //this.obs1=new ArrayList<gameElements>();
        this.game.setLoader(fml);
        this.game.setTimer(null);
        this.game.setMain_ball(new Ball(0, 310,600));

        System.out.println((int)arr.size());
        ArrayList <Node> toBeAdded= new ArrayList<Node>();
        int mul=1;
//        Group gg=new Group();
//        gg.getChildren().addAll(l1,l2,l3,l4,c);
//        rotateLine(gg);
//        toBeAdded.add(gg);
        for(int i=0;i<this.game.getSize();i++){
//            this.obs1.add(arr.get(i));
            toBeAdded.add(this.game.getObs(i).getGroup());
            if(this.game.getObs(i) instanceof CircleObs) {
                ArrayList<Arc> getArcs = ((CircleObs) this.game.getObs(i)).getArcforRotation();
                for (int j = 0; j < 4; j++) {
                    setRotate(getArcs.get(j), mul);

                }
                if (mul == 1) {
                    mul = -1;
                } else {
                    mul = 1;
                }
            }else{
                if(this.game.getObs(i) instanceof stars)
                    continue;
                rotateLine((Group)toBeAdded.get(toBeAdded.size()-1));
                if (mul == 1) {
                    mul = -1;
                } else {
                    mul = 1;
                }
            }
        }
        assert this.game.getRoot() != null;
        for(int j = 0; j<((StackPane)this.game.getRoot()).getChildren().size(); j++){
            if(((StackPane)this.game.getRoot()).getChildren().get(j) instanceof Pane){
                ((Pane) ((StackPane)this.game.getRoot()).getChildren().get(j)).getChildren().addAll(toBeAdded);
            }
        }

    }
    public Game getGame(){
        return this.game;
    }
    public void regCollisionCheck(){
        ArrayList<gameElements> tobeRemoved=new ArrayList<gameElements>();
        for(int i=0;i<this.game.getSize();i++) {
            if((this.game.getObs(i) instanceof wheel) ||this.game.getObs(i) instanceof stars){
                if(this.game.getObs(i).collisionCheck(this.ball)){
                    tobeRemoved.add(this.game.getObs(i));
                }
            }else{
                this.game.getObs(i).collisionCheck(this.ball);
            }
        }
        for(int i=0;i<tobeRemoved.size();i++) {
            for (int j = 0; j < ((StackPane) this.game.getRoot()).getChildren().size(); j++) {
                if (((StackPane) this.game.getRoot()).getChildren().get(j) instanceof Pane) {
                    ((Pane) ((StackPane) this.game.getRoot()).getChildren().get(j)).getChildren().remove(tobeRemoved.get(i).getGroup());
                }
            }
        }
    }
    public void RefreshObs(int idx){
        if(this.game.getObs(idx).getPosY()>=799){
            this.game.getObs(idx).moveDown(-1600.0);
        }
    }

    public void startGame(){
        this.game.setOld_time(System.nanoTime());
        Game g=this.game;
        this.game.setTimer(new AnimationTimer() {
            @Override
            public void handle(long now){
                g.settDiff((double)(now - g.getOld_time())/1000000000);
                g.setOld_time(now);
                animateBall();
                regCollisionCheck();
            }
        });
        this.game.getTimer().start();
    }
    public void moveObs(double dist){
        for(int i=0;i<this.game.getSize();i++) {
            this.game.getObs(i).moveDown(dist);
            RefreshObs(i);
        }
    }
    public void animateBall(){
        double curY=this.ball.getLayoutY();
        if(curY>=this.game.getMain_ball().floor && this.game.getMain_ball().vy<=0) {
            this.game.getMain_ball().vy=0;
            return;
        }
        double dist=(this.game.getMain_ball().vy * this.game.getDiff()) - ((2000 * this.game.getDiff() * this.game.getDiff()) / 2);
        double ballCurY=this.ball.getLayoutY();
        if(ballCurY-dist<340 ){
            this.moveObs(340-ballCurY+dist);
            this.ball.setLayoutY(340);
        }else {
            this.ball.setLayoutY(this.ball.getLayoutY() - ((this.game.getMain_ball().vy * this.game.getDiff()) - ((2000 * this.game.getDiff() * this.game.getDiff()) / 2)));

        }
        this.game.getMain_ball().posy = (int) this.ball.getLayoutY();
        this.game.getMain_ball().vy = (int) (this.game.getMain_ball().vy - 2000 * this.game.getDiff());
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
        myCon.init(this.game.getPs(), this.game.getRoot(), this.game.getLoader());
        //this.ps.setTitle("Color Switch");
        Scene main1=this.game.getPs().getScene();
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
