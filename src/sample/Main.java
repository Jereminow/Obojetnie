package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    Stage stage;
    Obrazki chosenImage;
    Color[][] colorArray;


    @Override
    public void start(Stage primaryStage) throws Exception{
      stage = new Stage();
      stage.setTitle("Choose option");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);


        Button fileChoice = new Button("Create from chosen file");
        Button textWrite = new Button("Create from input text");
        textWrite.setOnAction(event -> inputTextLayout());
        Button drawing = new Button("Create from a drawing");
        fileChoice.setOnAction(event -> {
            try {
                stage.close();
                fromFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().addAll(fileChoice,textWrite,drawing);
        Scene scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.show();
    }

    public void fromFile() throws FileNotFoundException {
        stage = new Stage();
        stage.setTitle("Get image from file");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(60);

        List<Obrazki> obrazkis = new ArrayList<>();

        File mi = new File("Minecraft.png");
        Image min = new Image(new FileInputStream("Minecraft.png"));
        ImageView mine = new ImageView(min);
        Obrazki minecraft = new Obrazki(mi,min,mine,"Minecraft");
        obrazkis.add(minecraft);

        File ny = new File("Nyan.jpg");
        Image nya = new Image(new FileInputStream("Nyan.jpg"));
        ImageView nyan = new ImageView(nya);
        Obrazki nyanCat = new Obrazki(ny,nya,nyan,"Nyan Cat");
        obrazkis.add(nyanCat);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(minecraft.getName(),nyanCat.getName());

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ImageFiles","*.png","*.jpg"));
        Button addFile = new Button("Add files to Combo Box");
        addFile.setOnAction(event -> {
            File selected = fileChooser.showOpenDialog(stage);
            System.out.println(selected.toURI().getPath());
            Image imgg = new Image("file:" + selected.toURI().getPath());

            ImageView view = new ImageView(imgg);
            String string = selected.getName();
            Obrazki obrazek = new Obrazki(selected,imgg,view,string);
            obrazkis.add(obrazek);
            comboBox.getItems().add(obrazek.getName());
        });


        Button okButt = new Button("Ok");
        okButt.setOnAction(event -> {
            for (int i = 0; i < obrazkis.size(); i++) {
                if(obrazkis.get(i).getName().equals(comboBox.getValue())){
                    File chosenfile = obrazkis.get(i).getFile();
                    try {
                        colorArray = colors(chosenfile);
                        System.out.println(Arrays.deepToString(colorArray));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
        Button backton = new Button("Back");
        backton.setOnAction(event -> {
            stage.close();
            try {
                start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        root.getChildren().addAll(addFile,comboBox,okButt,backton);
        Scene scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.show();

    }
    public static Color[][] colors(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        Color[][] colorArray = new Color[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int clr = image.getRGB(x,y);
                int red = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;
                Color cl = new Color(red/255.0,green/255.0,blue/255.0,1);
                colorArray[x][y] = cl;
            }
        }
        return colorArray;
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
        fontSizeTextField.setText("12");

        Text textToDrawText = new Text();

        Text fontText = new Text("Font");
        ComboBox<String> fontComboBox = new ComboBox<String>(FXCollections.observableList(Font.getFontNames()));
        System.out.println(Font.getFontNames());
        fontComboBox.setValue(Font.getFontNames().get(0));

        Text text = new Text("Text");
        TextField textField = new TextField();

        fontComboBox.setOnAction(event -> {
            int fontSize = Integer.parseInt(fontSizeTextField.getText());
            String textToDraw = textField.getText();
            String fontName = fontComboBox.getValue();

            textToDrawText.setText(textToDraw);
            textToDrawText.setFont(Font.font(fontName, fontSize));
        });

        textField.setOnKeyTyped(event -> {
            int fontSize = Integer.parseInt(fontSizeTextField.getText());
            String textToDraw = textField.getText();
            String fontName = fontComboBox.getValue();

            textToDrawText.setText(textToDraw);
            textToDrawText.setFont(Font.font(fontName, fontSize));
        });

        fontSizeTextField.setOnKeyTyped(event -> {
            int fontSize = Integer.parseInt(fontSizeTextField.getText());
            String textToDraw = textField.getText();
            String fontName = fontComboBox.getValue();

            textToDrawText.setText(textToDraw);
            textToDrawText.setFont(Font.font(fontName, fontSize));
        });

        Button button = new Button("Ok");
        button.setOnAction(event -> {
            int fontSize = Integer.parseInt(fontSizeTextField.getText());
            String textToDraw = textField.getText();
            String fontName = fontComboBox.getValue();

            textToDrawText.setText(textToDraw);
            textToDrawText.setText(textToDraw);
            textToDrawText.setFont(Font.font(fontName, fontSize));

            getColorsFromInputtedText(fontSize, textToDraw, fontName);
        });

        root.add(fontSizeText, 0, 0);
        root.add(fontSizeTextField, 1, 0);
        root.add(text, 0, 1);
        root.add(textField, 1, 1);
        root.add(fontText, 0, 2);
        root.add(fontComboBox, 1, 2);
        root.add(button, 1, 3);
        root.add(textToDrawText, 0, 4, 2, 1);

        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    public Color[][] getColorsFromInputtedText(int fontSize, String textToDraw, String fontName) {
        Color[][] colors = new Color[5][5];

        return colors;
    }
    public static void saveCanvasInFile(Canvas canvas) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ImageFiles","*.png","*.jpg"));

        File file = fileChooser.showSaveDialog(new Stage());



        if(file!=null){
            int width = (int) canvas.getWidth();
            int height = (int) canvas.getHeight();
            WritableImage writableImage = new WritableImage(width,height);
            canvas.snapshot(null,writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
            ImageIO.write(renderedImage,"jpg",file);


        }


    }
    public static void main(String[] args) {
        launch(args);
    }
}
