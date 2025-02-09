package Engine;

import Engine.Components.Component;
import Engine.Components.Transform;
import java.util.ArrayList;
import java.util.List;

public class Entity {
    public String name;
    public Transform transform;
    private int zIndex;
    private List<Component> components;
    
    public Entity(String name){
        this.zIndex = 0;
        this.name = name;
        this.transform = new Transform();
        this.components = new ArrayList<>();
    }

    public Entity(String name, Transform transform, int zIndex){
        this.zIndex = zIndex;
        this.name = name;
        this.transform = transform;
        this.components = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<T> componentClass){
        for (Component component : components) {
            if(componentClass.isAssignableFrom(component.getClass())){
                return (T) component;
            }
        }
        System.out.println("Entity '"+name+"' doesn't Contain Component '" + componentClass);
        return null;
    }

    public void addComponent(Component component){
        this.components.add(component);
        component.entity = this;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for(int i = 0; i < components.size(); i++){
            Component component = components.get(i);
            if(componentClass.isAssignableFrom(component.getClass())){
                components.remove(i);
                return;
            }
        }
    }

    public List<Component> getComponents(){
        return this.components;
    }

    public int zIndex(){
        return this.zIndex;
    }

    public void init(){
        for (int i = 0; i < components.size(); i++) {
            components.get(i).init();
        }
    }

    public void run(){
        for (int i = 0; i < components.size(); i++) {
            components.get(i).run();
        }
    }

    public static Entity instantiate(Entity entity, Transform transform){
        String name = entity.name;
        List<Component> components = entity.getComponents();
        Entity newEntity = new Entity(name, transform, entity.zIndex);
        for (int i = 0; i < components.size(); i++) {
            // add the component cloning here
        }
        return newEntity;
    }
}
