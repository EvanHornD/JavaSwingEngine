package Engine.Rendering;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import Engine.Entity;
import Engine.Window;
import Engine.Components.ComponentRenderer;
import Engine.Utils.Vector2f;

public class Renderer {

    Camera camera;
    Window window;
    String name;
    LayerMap layerMap;
    BufferedImage buffer;
    Graphics2D graphics;
    boolean isScreenScalable;

    public Renderer(String name, Window window, boolean isScreenScalable){
        this.camera = new Camera();
        this.name = name;
        this.window = window;

        int width = window.buffer.getWidth();
        int height = window.buffer.getHeight();
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        this.graphics = buffer.createGraphics();
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
        clipCameraToWindow(graphics);
        List<EntityLayer> layers = layerMap.getLayers();
        for (EntityLayer layer : layers) {
            ComponentRenderer[] sprites = layer.getSprites().toArray(new ComponentRenderer[0]);
            for (ComponentRenderer componentRenderer : sprites) {
                drawSprite(componentRenderer, graphics);
            }
        }
        graphics.setClip(null);
        window.render(buffer);
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

    private void drawSprite(ComponentRenderer componentRenderer, Graphics2D graphics){
        Vector2f spritePos = componentRenderer.entity.transform.getPosition();
        Vector2f spriteDimensions = componentRenderer.dimensions();

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

        graphics.drawImage(componentRenderer.getImage(),(int)posInScreen.x,(int)posInScreen.y,(int)dimensionsInScreen.x,(int)dimensionsInScreen.y,null);
        
    }
}
