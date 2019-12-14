package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Obrazki {
    private File file;
    private Image image;
    private ImageView imageview;
    private String name;


    public Obrazki(File file,Image image,ImageView imageview, String name) {
        this.file = file;
        this.imageview = imageview;
        this.name = name;
        this.image = image;
    }

    public ImageView getImageview() {
        return imageview;
    }

    public String getName() {
        return name;
    }

    public void setImageview(ImageView imageview) {
        this.imageview = imageview;
    }

    public void setName(String name) {
        this.name = name;
    }

 public Image getImage() {
      return image;
   }

    public void setImage(Image image) {
       this.image = image;
   }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
