package Engine.Rendering;

import java.util.ArrayList;
import java.util.List;

import Engine.Entity;
import Engine.Components.ComponentRenderer;

public class EntityLayer implements Comparable<EntityLayer> {
    private int zIndex;
    private List<ComponentRenderer> sprites;

    public EntityLayer(int zIndex){
        this.zIndex = zIndex;
        this.sprites = new ArrayList<>();
    }

    public void addEntity(Entity entity){
        if(entity.getComponent(ComponentRenderer.class)!=null){
            sprites.add(entity.getComponent(ComponentRenderer.class));
        }
    }

    public void removeEntity(Entity entity){
        if(entity.getComponent(ComponentRenderer.class)!=null){
            sprites.remove(entity.getComponent(ComponentRenderer.class));
        }
    }

    public int zIndex() {
        return zIndex;
    }

    public List<ComponentRenderer> getSprites() {
        return this.sprites;
    }


    @Override
    public int compareTo(EntityLayer o) {
        return Integer.compare(this.zIndex, o.zIndex());
    }
    
}

