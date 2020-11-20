package sample;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.IOException;
public class Controller {
    @FXML
    private ImageView sRing1;
    @FXML
    private ImageView logo2;
    @FXML
    private ImageView logo3;
    @FXML
    private ImageView sRing2;
    public void rotate() throws IOException {
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(500);
        rotate.setDuration(Duration.millis(1000));
        rotate.setAutoReverse(true);
        rotate.setNode(sRing1);
        rotate.play();
        RotateTransition rotate1 = new RotateTransition();
        rotate1.setAxis(Rotate.Z_AXIS);
        rotate1.setByAngle(-360);
        rotate1.setCycleCount(500);
        rotate1.setDuration(Duration.millis(1000));
        rotate1.setAutoReverse(true);
        rotate1.setNode(sRing2);
        rotate1.play();
        RotateTransition rotate2 = new RotateTransition();
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(500);
        rotate2.setDuration(Duration.millis(1000));
        rotate2.setAutoReverse(true);
        rotate2.setNode(logo2);
        rotate2.play();
        RotateTransition rotate3 = new RotateTransition();
        rotate3.setAxis(Rotate.Z_AXIS);
        rotate3.setByAngle(-360);
        rotate3.setCycleCount(500);
        rotate3.setDuration(Duration.millis(1000));
        rotate3.setAutoReverse(true);
        rotate3.setNode(logo3);
        rotate3.play();
    }
}
