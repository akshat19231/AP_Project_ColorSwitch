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
    public void init(){
        ball.setLayoutY(ball.getLayoutY() - 3);
    }
    public void _play () throws IOException {
        //Group grp=new Group();
        //grp.getChildren().add(arc1);
        setRotate(arc1);
        setRotate(arc2);
        setRotate(arc3);
        setRotate(arc4);
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
