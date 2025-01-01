package Engine.Components;

import Engine.Entity;

public abstract class Component {
    public Entity entity;

    Component(){
        
    }
    public void init(){}
    public void run(){};
}
