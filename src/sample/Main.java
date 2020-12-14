package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class Main extends Application {
    private static App app;

    public static void serialize() throws IOException {
        System.out.println("entered");
        ObjectOutputStream outputStream = null;
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream("savedGame.txt"));
            outputStream.writeObject(app);
        }
        finally {

            if (outputStream!=null) {
                outputStream.close();
            }
            System.out.println("end");
        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new FileInputStream("savedGame.txt"));
            app = (App) inputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            app = new App();
        }
        finally {
            if (inputStream!=null) {
                inputStream.close();
            }
            else {
                app = new App();
            }
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller myCon=(Controller) (loader.getController());
        myCon.init(primaryStage, app);
        primaryStage.setTitle("Color Switch");
        StackPane sp=new StackPane(root);
        Scene main1=new Scene(sp);
        primaryStage.setScene(main1);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserialize();
        launch(args);
    }
}
