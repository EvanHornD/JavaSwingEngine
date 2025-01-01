package Engine.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import Engine.Rendering.Sprite;

public class AssetPool {
    private static Map<String, Sprite> sprites = new HashMap<>();

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
}
