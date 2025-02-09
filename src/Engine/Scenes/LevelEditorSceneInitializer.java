package Engine.Scenes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Engine.ComponentSerializer;
import Engine.Entity;
import Engine.EntityDeserializer;
import Engine.Window;
import Engine.Components.Component;
import Engine.Components.ComponentRenderer;
import Engine.Components.Transform;
import Engine.Rendering.Renderer;
import Engine.Rendering.Sprite;
import Engine.Rendering.Text;
import Engine.Utils.AssetPool;
import Engine.Utils.Vector2f;
import assets.scripts.testScript;

public class LevelEditorSceneInitializer extends SceneInitializer {

    public LevelEditorSceneInitializer(Window window){
        this.window = window;
    }

    @Override
    public void init(Scene scene) {
        Renderer game = new Renderer("Game",this.window,true);

        Entity entity = new Entity("test", new Transform(new Vector2f(100, 100)), 0);

        Sprite sprite = AssetPool.getSprite("src\\assets\\images\\tetrisBlockBlue.png");
        Text text = new Text()
                       .setText("Thank You!!")
                       .setFontSize(12)
                       .setFontName("Arial");
 
        ComponentRenderer spriteRenderer = new ComponentRenderer()
                                               .setSprite(sprite)
                                               .setText(text)
                                               .createImage();
        entity.addComponent(spriteRenderer);

        testScript script = new testScript();
        entity.addComponent(script);

        game.add(entity);
        scene.addEntity(entity);

        scene.renderers.add(game);
        scene.renderers.add(new Renderer("EditorGUI",this.window,false));

        Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Component.class, new ComponentSerializer())
                    .registerTypeAdapter(Entity.class, new EntityDeserializer())
                    .create();

        String serialized = gson.toJson(entity);
        System.out.println(serialized);
        Entity entity2 = gson.fromJson(serialized, Entity.class);
        System.out.println(entity2);
    }

    @Override
    public void loadResources(Scene scene) {
        AssetPool.addSprite(
            new Sprite().setSprite("src\\assets\\images\\tetrisGreyScale2.png")
        );

        AssetPool.addSprite(
            new Sprite().setSprite("src\\\\assets\\images\\tetrisBlockBlue.png")
        );

        AssetPool.addSprite(
            new Sprite().setSprite("src\\\\assets\\images\\TetrisGrid.png")
        );
    }
}
