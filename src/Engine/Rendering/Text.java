package Engine.Rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Engine.Utils.Vector2f;
import Engine.Utils.Colors;

public class Text {
    private String text = "";
    private String fontName = "Ariel";
    private int fontType = Font.PLAIN;
    private int fontSize = 12;
    private int color = Colors.BLACK;

    private transient BufferedImage image = null;
    private transient Vector2f dimensions = new Vector2f(1,1);

    public Text setText(String text){
        this.text = text;
        return this;
    }

    public Text setFontName(String fontName){
        this.fontName = fontName;
        return this;
    }

    public Text setFontType(int fontType){
        this.fontType = fontType;
        return this;
    }

    public Text setFontSize(int fontSize){
        this.fontSize = fontSize;
        return this;
    }

    public Text setColor(int color){
        this.color = color;
        return this;
    }

    public Text createImage(){
        Font font = new Font(fontName, fontType, fontSize);
        createImageDimensions(font, text);
        this.image = new BufferedImage((int)dimensions.x, (int)dimensions.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.setColor(new Color(color));
        int ascent = g2d.getFontMetrics().getAscent();
        g2d.drawString(text,0,ascent);
        g2d.dispose();

        return this;
    }

    public void createImageDimensions(Font font, String text) {
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = tempImage.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int Width = fm.stringWidth(text);
        int height = fm.getHeight();
        this.dimensions = new Vector2f(Width, height);
        g2d.dispose();
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
