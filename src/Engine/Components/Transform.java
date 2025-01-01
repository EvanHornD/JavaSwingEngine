package Engine.Components;

import Engine.Utils.Vector2f;

public class Transform {
    private Vector2f position;
    private Vector2f scale;

    public Transform() {
        init(new Vector2f(), new Vector2f());
    }

    public Transform(Vector2f position) {
        init(position, new Vector2f());
    }

    public Transform(Vector2f position, Vector2f scale) {
        init(position, scale);
    }

    public void init(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;
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

    public void setPosition(Vector2f newPosition){
        this.position = newPosition;
    }
}
