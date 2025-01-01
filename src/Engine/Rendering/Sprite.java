package Engine.Rendering;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Engine.Utils.Vector2f;

public class Sprite {
    private String filePath;
    private BufferedImage image;
    private Vector2f dimensions;
    
    public Sprite(String filePath){
        this.filePath = filePath;

        try {
            this.image = ImageIO.read(new File(filePath));
            this.dimensions = new Vector2f(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            this.image = new BufferedImage(0, 0, BufferedImage.TYPE_4BYTE_ABGR);
            this.dimensions = new Vector2f();
            System.out.println("There was an error loading file:'"+filePath+"'.");
        }
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
 