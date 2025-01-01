package Engine.Components;

import Engine.Rendering.Sprite;

public class SpriteRenderer extends Component {
    private Sprite sprite;

    public SpriteRenderer(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite sprite(){
        return this.sprite;
    }
}
