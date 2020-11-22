package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
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
    private Ball main_ball;
    public void init() throws IOException {
        //ball.setLayoutY(ball.getLayoutY() - 3);
        this.main_ball=new Ball(0,ball.getLayoutX(),ball.getLayoutY());
        setRotate(arc1);
        setRotate(arc2);
        setRotate(arc3);
        setRotate(arc4);
    }
    public void handleClick(){
        this.main_ball.vy=500;
        Circle b=this.ball;
        Ball ba=this.main_ball;

        AnimationTimer timer = new AnimationTimer() {
            long old_time=-1;
            @Override
            public void handle(long now) {
                if(ba.vy<=0 && (ba.posy<=ba.floor+5 && ba.posy>=ba.floor-5)){
                    System.out.println("returned!");
                    ba.vy=0;

                    System.out.println(b.getLayoutY());
                    //this.timer.stop();
                    this.stop();
                }
                if(old_time == -1){
                    old_time = now;
                    return;
                }
                double tDiff=(double)(now - old_time)/1000000000;
                old_time=now;
                b.setTranslateY(b.getTranslateY() - (ba.vy*tDiff - (1500*tDiff*tDiff)/2));
                b.setLayoutY(b.getLayoutY() - ((ba.vy * tDiff) - ((1500 * tDiff * tDiff) / 2)));
                ba.posy=(int)b.getLayoutY();
                System.out.println(ba.posy);
                ba.vy= (int) (ba.vy - 1500*tDiff);
            }
        };
        timer.start();
    }

   public void setRotate(Arc arc) throws IOException{
       Timeline animation = new Timeline(
               new KeyFrame(Duration.ZERO, new KeyValue(arc.startAngleProperty(), arc.getStartAngle(), Interpolator.LINEAR)),
               new KeyFrame(Duration.seconds(2), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360, Interpolator.LINEAR))
       );
       animation.setCycleCount(Animation.INDEFINITE);
       animation.play();
   }


}
