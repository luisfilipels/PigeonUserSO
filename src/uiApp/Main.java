package uiApp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Pombo Correio");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.hide();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
