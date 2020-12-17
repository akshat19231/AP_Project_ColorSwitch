package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class loadScreen{
    @FXML
    private Button backB;
    @FXML
    private Button lb1;
    @FXML
    private Button lb2;
    @FXML
    private Button lb3;
    @FXML
    private Button lb4;
    @FXML
    private Button lb5;
    private Stage ps;
    private Parent root;
    private App app;

    String path5 = "src/assets/button.wav";
    AudioClip hover = new AudioClip(new File(path5).toURI().toString());

    String path4 = "src/assets/jump.wav";

    AudioClip jump = new AudioClip(new File(path4).toURI().toString());

    String path6 = "src/assets/start.wav";
    AudioClip click = new AudioClip(new File(path6).toURI().toString());
    public void init(Stage s, Parent p, App app){
        this.ps=s;
        this.root=p;
        this.app=app;
        ArrayList<Button> buttons=new ArrayList<Button>();
        buttons.add(lb1);
        buttons.add(lb2);
        buttons.add(lb3);
        buttons.add(lb4);
        buttons.add(lb5);
        Iterator<HashMap.Entry<String, Game>> itr = this.app.getGameMap().entrySet().iterator();
        ArrayList<Game> arrG=new ArrayList<Game>();
        while(itr.hasNext())
        {
            Map.Entry<String, Game> entry = itr.next();
            if(entry.getValue().getGameId()==1) {
                arrG.add(entry.getValue());
            }
        }
        Collections.sort(arrG, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                 if(o1.getOld_time()<o2.getOld_time()){
                     return 1;
                 }else{
                     return -1;
                 }
            }
        });
        for(int j=0;j<Math.min(5,arrG.size());j++){
            buttons.get(j).setText(arrG.get(j).getPlayer().getUname());
        }
        while(arrG.size()>5){
            this.app.getGameMap().remove(arrG.get(arrG.size()-1).getPlayer().getUname());
            arrG.remove(arrG.size()-1);
        }

    }
    public void loadGame1() throws IOException {
        Game g1=app.getGame(lb1.getText());
        if(g1!=null)
            this.load(g1);
    }
    public void loadGame2() throws IOException {
        Game g1=app.getGame(lb2.getText());
        if(g1!=null)
            this.load(g1);
    }
    public void loadGame3() throws IOException {
        Game g1=app.getGame(lb3.getText());
        if(g1!=null)
            this.load(g1);;
    }
    public void loadGame4() throws IOException {
        Game g1=app.getGame(lb4.getText());
        if(g1!=null)
            this.load(g1);
    }
    public void loadGame5() throws IOException {
        Game g1=app.getGame(lb5.getText());
        if(g1!=null)
            this.load(g1);
    }
    private void load(Game g) throws IOException {
        gamePlayController.mode=g.getMode();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePlay.fxml"));
        StackPane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        gamePlayController myCon=(gamePlayController)(loader.getController());
        myCon.loadGame(this.ps, root, loader,g, this.app);
        this.ps.setTitle("Color Switch");
        Scene main1=new Scene(root);
        setKeyFunctions(main1, myCon);
        this.ps.setScene(main1);
        root.requestFocus();
        myCon.startGame();
    }
    private void setKeyFunctions(Scene scene, gamePlayController Con) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                jump.play();
                setOnUserInput(scene, Con);
            }
            if(e.getCode() == KeyCode.D && gamePlayController.mode){
                System.out.println("ayyo");
                gamePlayController.focusObs.rotateRight();
            }
            if(e.getCode() == KeyCode.A && gamePlayController.mode){
                System.out.println("neyyo");
                gamePlayController.focusObs.rotateLeft();
            }
            if(e.getCode() == KeyCode.S && gamePlayController.mode){
                System.out.println("bruv");
                gamePlayController.focusObs.rotateStop();
            }
        });
    }

    private void setOnUserInput(Scene scene, gamePlayController c) {
        c.getGame().getMain_ball().vy=500;
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
    public void highlightOn_1() throws IOException {
        hover.play();
        lb1.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_1() throws IOException {
        lb1.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_2() throws IOException {
        hover.play();
        lb2.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_2() throws IOException {
        lb2.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_3() throws IOException {
        hover.play();
        lb3.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_3() throws IOException {
        lb3.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_4() throws IOException {
        hover.play();
        lb4.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_4() throws IOException {
        lb4.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }
    public void highlightOn_5() throws IOException {
        hover.play();
        lb5.setStyle("-fx-background-radius: 10px; -fx-background-color: #5B7065, linear-gradient(#5B7065 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #5B7065 45%, #304040 50%);;");
    }
    public void highlightOff_5() throws IOException {
        lb5.setStyle("-fx-background-radius: 10px; -fx-background-color: #2F496E, linear-gradient(#2F496E 50%, #304040 100%), radial-gradient(center 50% -40%, radius 200%, #2F496E 45%, #304040 50%);;");
    }


}
