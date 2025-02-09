package Engine;

import Engine.Input.Input;
import Engine.Scenes.LevelEditorSceneInitializer;
import Engine.Scenes.Scene;
import Engine.Utils.Time;

public class Main {

    private Window window;
    public boolean isRunning = true;

    public static Scene currentScene;

    public Main(Window window) {
        this.window = window;
    }

    public void init(){
        currentScene = new Scene(new LevelEditorSceneInitializer(window));
        currentScene.init();
    }

    public Scene getScene(){
        if(currentScene == null){
            currentScene = new Scene(new LevelEditorSceneInitializer(window));
        } 
        return currentScene;
    }

    public void run(){

        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while(isRunning){

            if(dt>=0){
                currentScene.update(dt);
            }

            Input.updateKeyActions();
            window.render();
            
            endTime = Time.getTime();
            dt = endTime-beginTime;
            beginTime = endTime;
        }
        window.dispose();
    }
}
