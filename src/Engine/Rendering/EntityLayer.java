package Engine.Rendering;

import java.util.ArrayList;
import java.util.List;

import Engine.Entity;
import Engine.Components.SpriteRenderer;

public class EntityLayer implements Comparable<EntityLayer> {
    private int zIndex;
    private List<SpriteRenderer> sprites;

    public EntityLayer(int zIndex){
        this.zIndex = zIndex;
        this.sprites = new ArrayList<>();
    }

    public void addEntity(Entity entity){
        if(entity.getComponent(SpriteRenderer.class)!=null){
            sprites.add(entity.getComponent(SpriteRenderer.class));
        }
    }

    public void removeEntity(Entity entity){
        if(entity.getComponent(SpriteRenderer.class)!=null){
            sprites.remove(entity.getComponent(SpriteRenderer.class));
        }
    }

    public int zIndex() {
        return zIndex;
    }

    public List<SpriteRenderer> getSprites() {
        return this.sprites;
    }


    @Override
    public int compareTo(EntityLayer o) {
        return Integer.compare(this.zIndex, o.zIndex());
    }
    
}

