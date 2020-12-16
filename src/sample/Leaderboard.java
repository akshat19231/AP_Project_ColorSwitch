
package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.util.*;
import java.io.*;


public class Leaderboard{
    @FXML
    private Button backB;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Label lb3;
    @FXML
    private Label lb4;
    @FXML
    private Label sc1;
    @FXML
    private Label sc2;
    @FXML
    private Label sc3;
    @FXML
    private Label sc4;
    private Stage ps;
    private Parent root;
    private App app;

    String path9 = "src/assets/start.wav";
    AudioClip click = new AudioClip(new File(path9).toURI().toString());
    public void init(Stage s, Parent p, App app){
        this.ps=s;
        this.root=p;
        this.app=app;
        ArrayList<Label> labels=new ArrayList<Label>();
        ArrayList<Label> slabels=new ArrayList<Label>();
        labels.add(lb1);
        labels.add(lb2);
        labels.add(lb3);
        labels.add(lb4);
        slabels.add(sc1);
        slabels.add(sc2);
        slabels.add(sc3);
        slabels.add(sc4);
        Iterator<HashMap.Entry<String, Game>> itr = this.app.getGameMap().entrySet().iterator();
        ArrayList<Game> arrG=this.app.getLeaderBoard().getBoard();
        for(int j=0;j<Math.min(4,arrG.size());j++){
            String name;
            if(arrG.get(j)!=null){
                labels.get(j).setText(arrG.get(j).getPlayer().getUname());
                slabels.get(j).setText(Integer.toString(arrG.get(j).getScore()));
            }
        }
    }
    public void handleClick() throws IOException {
        click.play();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller myCon=(Controller) (loader.getController());
        myCon.init(this.ps, app);
        StackPane sp=new StackPane(root);
        Scene main1=this.ps.getScene();
        main1.setRoot(sp);
    }
    public void highlightOn_b() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: bda0e0 ;");
    }
    public void highlightOff_b() throws IOException {
        backB.setStyle("-fx-background-radius: 100px; -fx-background-color: purple;");
    }


}