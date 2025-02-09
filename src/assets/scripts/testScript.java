package assets.scripts;

import java.util.List;

import Engine.Entity;
import Engine.Main;
import Engine.Components.Script;
import Engine.Components.Transform;
import Engine.Input.Input;
import Engine.Utils.AssetPool;
import Engine.Utils.Vector2f;

public class testScript extends Script {
    private int iteration = 0;

    @Override
    public void init() {
    }

    @Override
    public void run() {

        if(Input.getAction("Input") == -1){
            List<Entity> entities = Main.currentScene.entities;
            System.out.println();
            for (Entity entity : entities) {
                System.out.println(entity.name);
            }
            iteration++;
            Entity.instantiate(AssetPool.getPreFab("test"), new Transform(new Vector2f(iteration*100, iteration*100)));
        }
    }
}
