package Engine.Rendering;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import Engine.Entity;
import Engine.Window;
import Engine.Components.SpriteRenderer;
import Engine.Utils.Vector2f;

public class Renderer {

    Camera camera;
    Window window;
    String name;
    LayerMap layerMap;
    BufferedImage buffer;
    boolean isScreenScalable;

    public Renderer(String name, Window window, boolean isScreenScalable){
        this.camera = new Camera();
        this.name = name;
        this.window = window;
        this.buffer = window.buffer;
        this.layerMap = new LayerMap();
        this.isScreenScalable = isScreenScalable;
    }

    public Renderer(String name, Window window, Vector2f posInWindow, Vector2f windowDimensions, boolean isScreenScalable) {
        this.camera = new Camera(posInWindow, windowDimensions);
        this.name = name;
        this.window = window;
        this.buffer = window.buffer;
        this.layerMap = new LayerMap();
        this.isScreenScalable = isScreenScalable;
    }

    public void add(Entity entity){
        layerMap.addToLayers(entity);
    }

    public void draw(){
        this.buffer = window.buffer;
        Graphics2D graphics = buffer.createGraphics();
        clipCameraToWindow(graphics);
        List<EntityLayer> layers = layerMap.getLayers();
        for (EntityLayer layer : layers) {
            SpriteRenderer[] sprites = layer.getSprites().toArray(new SpriteRenderer[0]);
            for (SpriteRenderer spriteRenderer : sprites) {
                drawSprite(spriteRenderer, graphics);
            }
        }
        graphics.setClip(null);
    }

    private void clipCameraToWindow(Graphics2D graphics){
        Vector2f cameraScreenPos = camera.windowPosition();
        Vector2f cameraScreenDimensions = camera.dimensions();
        if(isScreenScalable){
            cameraScreenPos = window.toScreenCoords(cameraScreenPos);
            cameraScreenDimensions = window.toScreenCoords(cameraScreenDimensions);
            //System.out.println(cameraScreenPos+" "+cameraScreenDimensions);
        }

        graphics.clipRect((int)cameraScreenPos.x,
                          (int)cameraScreenPos.y,
                          (int)cameraScreenDimensions.x,
                          (int)cameraScreenDimensions.y);
    }

    private void drawSprite(SpriteRenderer spriteRenderer, Graphics2D graphics){
        Sprite sprite = spriteRenderer.sprite();
        Vector2f spritePos = spriteRenderer.entity.transform.getPosition();
        Vector2f spriteDimensions = sprite.dimensions();

        Vector2f posInCamera = camera.applyCameraZoom(camera.toCameraCoords(spritePos));
        Vector2f dimensionsInCamera = camera.applyCameraZoom(spriteDimensions);

        if(!camera.checkCoordsInCamera(posInCamera, dimensionsInCamera)){
            return;
        }

        Vector2f posInWindow = camera.toWindowCoords(posInCamera);
        
        // apply scaling appropriate to the JFrames size ratio to (1920,1080) if needed
        Vector2f posInScreen = posInWindow;
        Vector2f dimensionsInScreen = dimensionsInCamera;
        if(isScreenScalable){
            posInScreen = window.toScreenCoords(posInWindow);
            dimensionsInScreen = window.toScreenCoords(dimensionsInCamera);
        }

        graphics.drawImage(sprite.getImage(),(int)posInScreen.x,(int)posInScreen.y,(int)dimensionsInScreen.x,(int)dimensionsInScreen.y,null);
    }

}
