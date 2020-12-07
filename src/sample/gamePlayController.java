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
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class gamePlayController {
//    @FXML
//    public Circle ball;
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
    @FXML
    private Text scoreBoard;
    private Game game;
    public void init(Stage s, Parent p, FXMLLoader fml, Game g) throws IOException {
        this.game=g;
        this.game.initialiseObs();
        this.game.setPs(s);
        this.game.setRoot(p);
        this.game.setLoader(fml);
        this.game.setTimer(null);
        this.scoreBoard.setText(Integer.toString(this.game.getScore()));
        this.game.setMain_ball(new Ball(0, 310,600));
        this.game.getMain_ball().getCircle().setLayoutY(this.game.getMain_ball().getCircle().getLayoutY() - 3);
        ArrayList <Node> toBeAdded= new ArrayList<Node>();
        toBeAdded.add(this.game.getMain_ball().getCircle());
        int mul=1;
        for(int i=0;i<this.game.getSize();i++){
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
        for(int i=0;i<this.game.getSizeQ();i++){
            toBeAdded.add(this.game.getObsQ(i).getGroup());
            if(this.game.getObsQ(i) instanceof CircleObs) {
                ArrayList<Arc> getArcs = ((CircleObs) this.game.getObsQ(i)).getArcforRotation();
                for (int j = 0; j < 4; j++) {
                    setRotate(getArcs.get(j), mul);
                }
                if (mul == 1) {
                    mul = -1;
                } else {
                    mul = 1;
                }
            }else{
                if(this.game.getObsQ(i) instanceof stars)
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

//            if((this.game.getObs(i) instanceof wheel) ||this.game.getObs(i) instanceof stars){
//                if(this.game.getObs(i).collisionCheck(this.ball)){
//                    if(this.game.getObs(i) instanceof stars) {
//                        this.updateScore();
//                        this.scoreBoard.setText(Integer.toString(this.game.getScore()));
//                    }
//                    tobeRemoved.add(this.game.getObs(i));
//                }
//            }else{
        for(int i=0;i<this.game.getSizeQ();i++) {
            if(this.game.getObsQ(i).collisionCheck(this.game.getMain_ball().getCircle())){
                this.GameOver();
            }
        }

        for(int i=0;i<this.game.getSize();i++) {
            if ((this.game.getObs(i) instanceof wheel) || this.game.getObs(i) instanceof stars) {
                if (this.game.getObs(i).collisionCheck(this.game.getMain_ball().getCircle())) {
                    if (this.game.getObs(i) instanceof stars) {
                        this.updateScore();
                        this.scoreBoard.setText(Integer.toString(this.game.getScore()));
                    }
                    tobeRemoved.add(this.game.getObs(i));

                }
            }
        }
        for(int i=0;i<tobeRemoved.size();i++) {
            for (int j = 0; j < ((StackPane) this.game.getRoot()).getChildren().size(); j++) {
                if (((StackPane) this.game.getRoot()).getChildren().get(j) instanceof Pane) {
                    ((Pane) ((StackPane) this.game.getRoot()).getChildren().get(j)).getChildren().remove(tobeRemoved.get(i).getGroup());
                    this.game.remove(tobeRemoved.get(i));
                }
            }
        }
    }
    public void RefreshObs(int idx){
//        if(this.game.getObs(idx).getPosY()>=799){
//            this.game.getObs(idx).moveDown(-1600.0);
//            if((this.game.getObs(idx) instanceof wheel) ||this.game.getObs(idx) instanceof stars){
//                this.game.getObs(idx).refresh();
//                for (int j = 0; j < ((StackPane) this.game.getRoot()).getChildren().size(); j++) {
//                    if (((StackPane) this.game.getRoot()).getChildren().get(j) instanceof Pane) {
//                        ((Pane) ((StackPane) this.game.getRoot()).getChildren().get(j)).getChildren().add(this.game.getObs(idx).getGroup());
//                    }
//                }
//            }
//        }

    }

    public void updateScore(){
        this.game.levelUp();
    }
    public void fun(Game g){
        double val=0;
        if (g.getObsQ(0) instanceof CircleObs) val=275;
        if( g.getSizeQ()>0 && g.getObsQ(0).getPosY()>=799-val){
            for (int j = 0; j < ((StackPane) this.game.getRoot()).getChildren().size(); j++) {
                if (((StackPane) this.game.getRoot()).getChildren().get(j) instanceof Pane) {
                    ((Pane) ((StackPane) this.game.getRoot()).getChildren().get(j)).getChildren().remove(g.getObsQ(0).getGroup());
                }
            }
            double y=(g.getObsQ(0)).getPosY();
            //g.getObs(i).refresh();
            g.removeQ();
            //System.out.println(y);
            int ret=g.update(1000);
            stars new_s=new stars(0,1000);
            new_s.makeObs();
            wheel new_w=new wheel(0,1000);
            new_w.makeObs();
            this.game.add(new_s);
            this.game.add(new_w);
            System.out.println("updated");
            //System.out.println(g.getSizeQ());
            //System.out.println();
            for (int j = 0; j < ((StackPane) g.getRoot()).getChildren().size(); j++) {
                if (((StackPane) g.getRoot()).getChildren().get(j) instanceof Pane) {
                    ((Pane) ((StackPane) g.getRoot()).getChildren().get(j)).getChildren().addAll(g.getObsQ(g.getSizeQ()-1).getGroup(),new_s.getGroup(),new_w.getGroup());
                    if(g.getObsQ(g.getSizeQ()-1) instanceof  CircleObs){
                        ArrayList<Arc> getArcs = ((CircleObs) g.getObsQ(g.getSizeQ()-1)).getArcforRotation();
                        for (int k = 0; k < 4; k++) {
                            try {
                                setRotate(getArcs.get(k), 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        rotateLine(g.getObsQ(g.getSizeQ()-1).getGroup());
                    }
//                    if(ret==2){
//                        ((Pane) ((StackPane) g.getRoot()).getChildren().get(j)).getChildren().add(g.getObsQ(g.getSizeQ()-2).getGroup());
//                        if(g.getObsQ(g.getSizeQ()-2) instanceof  CircleObs){
//                            ArrayList<Arc> getArcs = ((CircleObs) g.getObsQ(g.getSizeQ()-2)).getArcforRotation();
//                            for (int k = 0; k < 4; k++) {
//                                try {
//                                    setRotate(getArcs.get(k), 1);
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }else{
//                            rotateLine(g.getObsQ(g.getSizeQ()-2).getGroup());
//                        }
//                    }
                }
            }
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
        for(int i=0;i<this.game.getSizeQ();i++) {
            this.game.getObsQ(i).moveDown(dist);
            //this.game.getObsQ(i).print();

        }
        fun(this.game);
    }
    public void animateBall(){
        double curY=this.game.getMain_ball().getCircle().getLayoutY();
        if(curY>=this.game.getMain_ball().floor && this.game.getMain_ball().vy<=0) {
            this.game.getMain_ball().vy=0;
            return;
        }
        double dist=(this.game.getMain_ball().vy * this.game.getDiff()) - ((2000 * this.game.getDiff() * this.game.getDiff()) / 2);
        double ballCurY=this.game.getMain_ball().getCircle().getLayoutY();
        if(ballCurY-dist<340 ){
            this.moveObs(340-ballCurY+dist);
            this.game.getMain_ball().getCircle().setLayoutY(340);
        }else {
            this.game.getMain_ball().getCircle().setLayoutY(this.game.getMain_ball().getCircle().getLayoutY() - ((this.game.getMain_ball().vy * this.game.getDiff()) - ((2000 * this.game.getDiff() * this.game.getDiff()) / 2)));

        }
        this.game.getMain_ball().posy = (int) this.game.getMain_ball().getCircle().getLayoutY();
        this.game.getMain_ball().vy = (int) (this.game.getMain_ball().vy - 2000 * this.game.getDiff());
    }
    public void pauseGame(){
        this.game.getTimer().stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pause.fxml"));
        Parent root1 = null;
        try {
            root1 = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseController myCon=(pauseController) (loader.getController());
        myCon.init(this.game);
        Scene main1=this.game.getPs().getScene();
        main1.setRoot(root1);
    }
    public void GameOver(){
        this.game.getTimer().stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOver.fxml"));
        Parent root1 = null;
        try {
            root1 = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOverController myCon=(gameOverController) (loader.getController());
        myCon.init(this.game);
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
