package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        textWrite.setOnAction(event -> inputTextLayout());
        Button drawing = new Button("Create from a drawing");
        root.getChildren().addAll(fileChoice,textWrite,drawing);
        Scene scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.show();
    }

    public void inputTextLayout() {
        Stage stage = new Stage();
        stage.setTitle("Input text");

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(50);
        root.setVgap(50);

        Text fontSizeText = new Text("Font size");
        TextField fontSizeTextField = new TextField();

        Text text = new Text("Text");
        TextField textField = new TextField();

        Text fontText = new Text("Font");
        ComboBox<String> fontComboBox = new ComboBox<String>(FXCollections.observableList(Font.getFontNames()));
        System.out.println(Font.getFontNames());

        Button button = new Button("Ok");
        button.setOnAction(event -> {

        });

        root.add(fontSizeText, 0, 0);
        root.add(fontSizeTextField, 1, 0);
        root.add(text, 0, 1);
        root.add(textField, 1, 1);
        root.add(fontText, 0, 2);
        root.add(fontComboBox, 1, 2);

        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    public Color[][] getColorsFromInputtedText() {
        Color[][] colors = new Color[5][5];

        return colors;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
