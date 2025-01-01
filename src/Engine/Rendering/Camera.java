package Engine.Rendering;

import Engine.Utils.Vector2f;

public class Camera {

    // defines the position in the scene of the camera
    private Vector2f posInScene;

    // defines the level of zoom of the camera
    private float zoom;

    // defines where images will be drawn in the window
    private Vector2f posInWindow;

    // defines the dimensions of the camera
    private Vector2f cameraDimensions;

    public Camera(){
        this.posInScene = new Vector2f();

        this.zoom = 1;

        this.posInWindow = new Vector2f();

        this.cameraDimensions = new Vector2f(1920, 1080);
    }

    public Camera(Vector2f posInWindow, Vector2f windowDimensions){
        this.posInScene = new Vector2f();

        this.zoom = 1;

        this.posInWindow = posInWindow;

        this.cameraDimensions = windowDimensions;
    }

    public Vector2f position(){
        return posInScene;
    }

    public Vector2f dimensions(){
        return cameraDimensions;
    }

    public Vector2f windowPosition(){
        return this.posInWindow;
    }

    public void setPosition(Vector2f position){
        this.posInScene = position;
    }

    public void move(Vector2f movement){
        this.posInScene = Vector2f.add(posInScene, movement);
    }
    
    public void setDimensions(Vector2f cameraDimensions){
        this.cameraDimensions = cameraDimensions;
    }

    // apply this first
    public Vector2f toCameraCoords(Vector2f vector2f){
        return Vector2f.subtract(vector2f, posInScene);
    }

    // apply second
    public Vector2f applyCameraZoom(Vector2f vector2f){
        return Vector2f.multiply(vector2f, zoom);
    }

    // apply this check after translating the object to camera coordinates
    public boolean checkCoordsInCamera(Vector2f position, Vector2f dimensions){
                // sprite coordinates
                float       spriteX = position.x;
                float       spriteY = position.y;
                float   spriteWidth = dimensions.x;
                float  spriteHeight = dimensions.y;
        
                // camera coordinates
                float      camX = posInScene.x;
                float      camY = posInScene.y;
                float  camWidth = cameraDimensions.x;
                float camHeight = cameraDimensions.y;
        
                // sprite boundaries
                float spriteRight  = spriteX+spriteWidth;
                float spriteLeft   = spriteX;
                float spriteTop    = spriteY;
                float spriteBottom = spriteY+spriteHeight;
        
                // camera boundaries
                float camRight  = camX+camWidth;
                float camLeft   = camX;
                float camTop    = camY;
                float camBottom = camY+camHeight;
        
                return  (spriteRight>=camLeft) &&
                        (spriteLeft<camRight) &&
                        (spriteTop<=camBottom) &&
                        (spriteBottom>camTop) ;
    }

    // apply this after checking the cameras coordinates, during the step of rendering the images
    public Vector2f toWindowCoords(Vector2f vector2f){
        return Vector2f.add(vector2f, posInWindow);
    }
}
