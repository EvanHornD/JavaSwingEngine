package Engine.Components;

import Engine.Utils.Vector2f;

public class Transform {
    private Vector2f position = new Vector2f();
    private Vector2f scale = new Vector2f(1, 1);

    public Transform() {}

    public Transform(Vector2f position) {
        this.position = position;
    }

    public Transform(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;
    }

    public void setPosition(Vector2f position){
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    public Vector2f getPosition(){
        return this.position;
    }

    public float x(){
        return this.position.x;
    }

    public float y(){
        return this.position.y;
    }

    public float xScale(){
        return this.scale.x;
    }

    public float yScale(){
        return this.scale.y;
    }

    public void move(Vector2f movement){
        this.position = Vector2f.add(this.position, movement);
    }
}
