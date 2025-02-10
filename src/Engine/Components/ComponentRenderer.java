package Engine.Components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Engine.Rendering.Shape2D;
import Engine.Rendering.Sprite;
import Engine.Rendering.Text;
import Engine.Utils.Vector2f;

public class ComponentRenderer extends Component {
    private Sprite sprite = null;
    private Text text = null;
    private Shape2D shape = null;

    private transient BufferedImage image = null;
    private transient Vector2f dimensions = new Vector2f(1, 1);

    public BufferedImage getImage() {
        if(image == null){
            createImage();
        }
        return image;
    }

    public ComponentRenderer createImage(){
        createImageDimensions();
        this.image = new BufferedImage((int)dimensions.x, (int)dimensions.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = image.createGraphics();
        if(sprite!=null){
            BufferedImage spriteImage = sprite.getImage();
            Vector2f spriteDimensions = sprite.dimensions();
            graphics.drawImage(spriteImage, (int)((dimensions.x/2)-(spriteDimensions.x/2)), (int)((dimensions.y/2)-(spriteDimensions.y/2)), null);
        }

        if(text!=null){
            BufferedImage textImage = text.getImage();
            Vector2f textDimensions = text.dimensions();
            graphics.drawImage(textImage, (int)((dimensions.x/2)-(textDimensions.x/2)), (int)((dimensions.y/2)-(textDimensions.y/2)), null);
        }

        if(shape!=null){
            BufferedImage shapeImage = shape.getImage();
            Vector2f shapeDimensions = shape.dimensions();
            graphics.drawImage(shapeImage, (int)((dimensions.x/2)-(shapeDimensions.x/2)), (int)((dimensions.y/2)-(shapeDimensions.y/2)), null);
        }
        return this;
    }

    public void createImageDimensions(){
        Vector2f dimensions = new Vector2f(1, 1);
        if(sprite!=null){
            Vector2f spriteDimensions = sprite.dimensions();
            dimensions.x = Math.max(dimensions.x, spriteDimensions.x);
            dimensions.y = Math.max(dimensions.y, spriteDimensions.y);
        }

        if(text!=null){
            Vector2f textDimensions = text.dimensions();
            dimensions.x = Math.max(dimensions.x, textDimensions.x);
            dimensions.y = Math.max(dimensions.y, textDimensions.y);
        }

        if(shape!=null){
            Vector2f shapeDimensions = shape.dimensions();
            dimensions.x = Math.max(dimensions.x, shapeDimensions.x);
            dimensions.y = Math.max(dimensions.y, shapeDimensions.y);
        }
        this.dimensions = dimensions;
    }

    public Vector2f dimensions(){
        return this.dimensions;
    }

    public ComponentRenderer setSprite(Sprite sprite){
        this.sprite = sprite;
        return this;
    }

    public ComponentRenderer setText(Text text){
        this.text = text;
        return this;
    }

    public ComponentRenderer setShape(Shape2D shape){
        this.shape = shape;
        return this;
    }

    public Sprite getSprite(){
        return this.sprite;
    }

    public Text getText(){
        return this.text;
    }

    public Shape2D getShape2d(){
        return this.shape;
    }

    @Override
    public ComponentRenderer clone(){
        ComponentRenderer spriteRenderer = new ComponentRenderer()
        .setSprite(this.sprite)
        .setText(this.text)
        .createImage();
        return spriteRenderer;
    }
}
