package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class stars extends gameElements{
    private ImageView img;
    private Group g;
    public stars(int x, int y) {
        super(x, y);
        this.img=new ImageView();
        this.g=new Group();

    }
    public void makeObs(){
        this.img.setImage(new Image(getClass().getResourceAsStream("/assets/star_preview_rev_1.png")));
        this.img.setLayoutX(302);
        this.img.setLayoutY(260+this.getY());
        this.img.setX(0);
        this.img.setY(0);
        this.img.setFitHeight(18);
        this.img.setFitWidth(22);
        this.img.setScaleX(1);
        this.img.setScaleY(1);
        this.img.setScaleZ(1);
        this.img.setOpacity(1);

        this.g.getChildren().add(this.img);
    }
    @Override
    public double getPosY() {
        return this.img.getLayoutY();
    }

    @Override
    public Boolean collisionCheck(Circle c) {
        return false;
    }

    @Override
    public Group getGroup() {
        return this.g;
    }

    @Override
    public void moveDown(double i) {
        this.img.setLayoutY(this.img.getLayoutY() + i);
    }
}
