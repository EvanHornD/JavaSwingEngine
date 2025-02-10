package Engine.Rendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Engine.Utils.Colors;
import Engine.Utils.Vector2f;

public class Shape2D {

    private Vector2f dimensions = new Vector2f(100,100);
    private int color = Colors.GREY;

    private transient BufferedImage image = null;

    public Shape2D setDimensions(Vector2f dimensions){
        this.dimensions = dimensions;
        return this;
    }

    public Shape2D setColor(int color){
        this.color = color;
        return this;
    }

    public Shape2D createImage(){
        this.image = new BufferedImage((int)dimensions.x, (int)dimensions.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(color));
        g2d.fillRect(0,0,(int)dimensions.x,(int)dimensions.y);
        g2d.dispose();
        return this;
    }

    public BufferedImage getImage(){
        if(this.image == null){
            createImage();
        }
        return this.image;
    }

    public Vector2f dimensions(){
        if(image==null){
            createImage();
        }
        return this.dimensions;
    }
}
