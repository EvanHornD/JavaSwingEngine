package Engine.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import Engine.Entity;
import Engine.Rendering.Sprite;

public class AssetPool {
    private static Map<String, Sprite> sprites = new HashMap<>();
    private static Map<String, Entity> preFabs = new HashMap<>();

    public static Sprite getSprite(String filePath){
        File file = new File(filePath);
        if(!AssetPool.sprites.containsKey(file.getAbsolutePath())){
            System.out.println("Tried to get Sprite'"+filePath+"' which hasn't been added to the asset pool");
            return null;
        }

        return AssetPool.sprites.get(file.getAbsolutePath());
    }

    public static void addSprite(Sprite sprite){
        File file = new File(sprite.filePath());
        if(AssetPool.sprites.containsKey(file.getAbsolutePath())){
            System.out.println("Sprite '"+file.getAbsolutePath()+"' already exists in the assetpool");
            return;
        }

        AssetPool.sprites.put(file.getAbsolutePath(), sprite);
    }

    public static Entity getPreFab(String name){
        if(!AssetPool.preFabs.containsKey(name)){
            System.out.println("Tried to get PreFab'"+name+"' which hasn't been added to the asset pool");
            return null;
        }
        return AssetPool.preFabs.get(name);
    }

    public static void addPreFab(Entity entity){
        String name = entity.name;
        if(AssetPool.preFabs.containsKey(name)){
            System.out.println("PreFab '"+name+"' already exists in the assetpool");
            return;
        }

        AssetPool.preFabs.put(name, entity);
    }

}
