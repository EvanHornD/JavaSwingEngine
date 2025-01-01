package Engine.Scenes;

import java.util.ArrayList;

import Engine.Entity;
import Engine.Rendering.Renderer;

public class Scene {

    ArrayList<Renderer> renderers;
    ArrayList<Entity> entities;

    SceneInitializer sceneInitializer;

    public Scene(SceneInitializer sceneInitializer) {
        this.renderers = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.sceneInitializer = sceneInitializer;
    }

    public void init(){
        sceneInitializer.loadResources(this);
        sceneInitializer.init(this);
    }

    public void update(float dt){
        for (Renderer renderer: renderers) {
            renderer.draw();
        }
    }
}
