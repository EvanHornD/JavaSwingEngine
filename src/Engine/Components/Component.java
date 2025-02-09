package Engine.Components;

import Engine.Entity;

public abstract class Component {
    public transient Entity entity;

    Component(){}

    public void init(){}
    public void run(){} 
    public abstract Component clone();
}
