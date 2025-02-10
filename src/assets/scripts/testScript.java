package assets.scripts;

import Engine.Components.Script;
import Engine.Input.*;

public class testScript extends Script {

    @Override
    public void init() {
    }

    @Override
    public void run() {

        if(Input.getAction("Input") != 0){
            entity.transform.setPosition(Input.mousePosition);
        }
    }
}
