package Engine.Utils;

public class Colors {


    public static final int WHITE = 0xffffffff;
    public static final int BLACK = 0xff000000;

    public int alpha = 0;
    public int red = 0;
    public int green = 0;
    public int blue = 0;

    public Colors(int color){
        this.alpha = (color>>24)&0xff;
        this.red = (color>>16)&0xff;
        this.green = (color>>8)&0xff;
        this.blue = (color)&0xff;
    }

    public Colors(int red, int green, int blue){
        this.alpha = 0xff;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Colors(int alpha, int red, int green, int blue){
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Colors setColor(int color){
        this.alpha = (color>>24)&0xff;
        this.red = (color>>16)&0xff;
        this.green = (color>>8)&0xff;
        this.blue = (color)&0xff;
        return this;
    }

    public Colors setColor(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    public Colors setColor(int alpha, int red, int green, int blue){
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    public int getColor(){
        int color = alpha<<24;
            color = color|(red<<16);
            color = color|(green<<8);
            color = color|(blue);
        return color;
    }

    public static int getAlpha(int color){
        int alpha = (color>>24)&0xff;
        return alpha;
    }
}
