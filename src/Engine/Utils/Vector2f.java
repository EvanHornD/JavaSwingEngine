package Engine.Utils;

public class Vector2f {
    public float x;
    public float y;

    public Vector2f(){
        this.x=0;
        this.y=0;
    }

    public Vector2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public static Vector2f add(Vector2f vec1, Vector2f vec2){
        return new Vector2f(vec1.x+vec2.x,  // x
                            vec1.y+vec2.y); // y
    }

    public static Vector2f subtract(Vector2f vec1, Vector2f vec2){
        return new Vector2f(vec1.x-vec2.x,  // x
                            vec1.y-vec2.y); // y
    }

    public static Vector2f multiply(Vector2f vec1, float num){
        return new Vector2f(vec1.x*num,  // x
                            vec1.y*num); // y
    }

    public static Vector2f multiply(Vector2f vec1, Vector2f vec2){
        return new Vector2f(vec1.x*vec2.x,  // x
                            vec1.y*vec2.y); // y
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "["+x+","+y+"]";
    }
}
