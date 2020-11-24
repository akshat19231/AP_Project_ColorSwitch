package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class gamePlayController {
    @FXML
    private Arc arc1;
    @FXML
    private Arc arc2;
    @FXML
    private Arc arc3;
    @FXML
    private Arc arc4;
    @FXML
    private Circle ball;
    @FXML
    private Button backB;
    private Ball main_ball;
    private Stage ps;
    private Parent root;
    private FXMLLoader loader;
    private stars Star;
    private AnimationTimer timer;
    private GraphicsContext gc, ballGC;

    public void init(Stage s, Parent p, FXMLLoader fml) throws IOException {
        ball.setLayoutY(ball.getLayoutY() - 3);
        this.ps=s;
        this.root=p;

        this.loader=fml;
        this.timer=null;
        this.main_ball=new Ball(0, 310,556);




        setRotate(arc1);
        setRotate(arc2);
        setRotate(arc3);
        setRotate(arc4);
    }

    public void handleClick(){
        this.main_ball.vy=500;
        Circle b=this.ball;
        Ball ba=this.main_ball;
        if(this.timer!=null) this.timer.stop();
        this.timer = new AnimationTimer() {
            long old_time=System.nanoTime();;
            @Override
            public void handle(long now){
                if(ba.vy<=0 && (ba.posy>=ba.floor)){
                    System.out.println("returned!");
                    ba.vy=0;
                    System.out.println(b.getLayoutY());
                    //this.timer.stop();
                    this.stop();
                }
                double tDiff=(double)(now - old_time)/1000000000;
                old_time=now;
                b.setTranslateY(b.getTranslateY() - (ba.vy*tDiff - (2000*tDiff*tDiff)/2));
                b.setLayoutY(b.getLayoutY() - ((ba.vy * tDiff) - ((2000 * tDiff * tDiff) / 2)));
                ba.posy=(int)b.getLayoutY();
                ba.vy= (int) (ba.vy - 2000*tDiff);
                System.out.println(ba.vy);
            }
        };
        timer.start();
    }
    public void pauseGame(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pause.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pauseController myCon=(pauseController) (loader.getController());
        myCon.init(this.ps, this.root, this.loader);
        //this.ps.setTitle("Color Switch");
        Scene main1=this.ps.getScene();
        main1.setRoot(root);
    }
   public void setRotate(Arc arc) throws IOException{
       Timeline animation = new Timeline(
               new KeyFrame(Duration.ZERO, new KeyValue(arc.startAngleProperty(), arc.getStartAngle(), Interpolator.LINEAR)),
               new KeyFrame(Duration.seconds(2), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360, Interpolator.LINEAR))
       );
       animation.setCycleCount(Animation.INDEFINITE);
       animation.play();
   }
    public void highlight_On() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0 ;");
    }
    public void highlight_Off() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }


}
