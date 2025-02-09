package Engine.Rendering;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Engine.Utils.Vector2f;

public class Sprite {
    private String filePath = "";

    private transient BufferedImage image = null;
    private transient Vector2f dimensions = new Vector2f(1,1);

    public Sprite setSprite(String filePath){
        if(filePath.equals("")) return this;

        this.filePath = filePath;
        try {
            this.image = ImageIO.read(new File(filePath));
            this.dimensions = new Vector2f(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            System.out.println("There was an error loading file:'"+filePath+"'.");
        }
        return this;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Vector2f dimensions(){
        return this.dimensions;
    }

    public String filePath(){
        return this.filePath;
    }
}
 