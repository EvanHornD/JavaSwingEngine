package Engine.Scenes;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Engine.ComponentSerializer;
import Engine.Entity;
import Engine.EntityDeserializer;
import Engine.Components.Component;
import Engine.Rendering.Renderer;

public class Scene {

    public ArrayList<Renderer> renderers;
    public ArrayList<Entity> entities;
    public boolean isRunning = false; 
    private ArrayList<Entity> queuedEntities;

    SceneInitializer sceneInitializer;

    public Scene(SceneInitializer sceneInitializer) {
        this.renderers = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.queuedEntities = new ArrayList<>();
        this.sceneInitializer = sceneInitializer;
    }

    public void addEntity(Entity entity){
        renderers.get(0).add(entity);
        queuedEntities.add(entity);
    }

    public void init(){
        sceneInitializer.loadResources(this);
        sceneInitializer.init(this);
        for (Entity entity : entities) {
            entity.init();
        }
    }

    public void update(float dt){
        for (Renderer renderer: renderers) {
            renderer.draw();
        }
        for (Entity entity : entities) {
            entity.run();
        }
        for (Entity entity : queuedEntities) {
            entity.init();
            entities.add(entity);
        }
        for (int i = 0; i < queuedEntities.size(); i++) {
            queuedEntities.remove(0);
        }
    }

    public void save(){
        Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(Component.class, new ComponentSerializer())
                        .registerTypeAdapter(Entity.class, new EntityDeserializer())
                        .create();

        try {
            FileWriter writer = new FileWriter("level.txt");
            writer.write(gson.toJson(this.entities));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(Component.class, new ComponentSerializer())
                        .registerTypeAdapter(Entity.class, new EntityDeserializer())
                        .create();

        String inFile = "";
        try {
            inFile = new String(Files.readAllBytes(Paths.get("level.txt")));            
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!inFile.equals("")){
            Entity[] newEntities = gson.fromJson(inFile,Entity[].class);
            for (int i = 0; i < newEntities.length; i++) {
            }
        }
    }


}
