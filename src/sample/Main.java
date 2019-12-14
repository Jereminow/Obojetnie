package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception{
      stage = new Stage();
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        Button fileChoice = new Button("Create from chosen file");
        Button textWrite = new Button("Create from input text");
        Button drawing = new Button("Create from a drawing");
        root.getChildren().addAll(fileChoice,textWrite,drawing);
        Scene scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
