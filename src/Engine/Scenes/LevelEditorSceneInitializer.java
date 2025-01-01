package Engine.Scenes;

import Engine.Entity;
import Engine.Window;
import Engine.Components.SpriteRenderer;
import Engine.Components.Transform;
import Engine.Rendering.Renderer;
import Engine.Rendering.Sprite;
import Engine.Utils.AssetPool;
import Engine.Utils.Vector2f;

public class LevelEditorSceneInitializer extends SceneInitializer {

    public LevelEditorSceneInitializer(Window window){
        this.window = window;
    }

    @Override
    public void init(Scene scene) {
        Renderer game = new Renderer("Game",this.window,true);
        Entity entity = new Entity("test", new Transform(new Vector2f(100, 100)), 0);
        entity.addComponent(new SpriteRenderer(AssetPool.getSprite("assets\\images\\tetrisBlockBlue.png")));
        game.add(entity);
        scene.renderers.add(game);
        scene.renderers.add(new Renderer("EditorGUI",this.window,false));
    }

    @Override
    public void loadResources(Scene scene) {
        AssetPool.addSprite(
            new Sprite("assets\\images\\tetrisGreyScale2.png")
        );

        AssetPool.addSprite(
            new Sprite("assets\\images\\tetrisBlockBlue.png")
        );

        AssetPool.addSprite(
            new Sprite("assets\\images\\TetrisGrid.png")
        );
    }
}
