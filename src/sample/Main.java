package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        root.getChildren().addAll(addFile,comboBox,okButt);
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



    public static void main(String[] args) {
        launch(args);
    }
}
