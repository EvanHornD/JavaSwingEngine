package assets.scripts;

import Engine.Components.Script;
import Engine.Input.Input;
import Engine.Utils.Vector2f;

public class testScript extends Script {

    @Override
    public void init() {
    }

    @Override
    public void run() {
        if(Input.getAction("Input") == -1){
            entity.transform.move(new Vector2f(20, 0));
        }
    }
}
