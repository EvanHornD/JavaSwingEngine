package assets.scripts;

import Engine.Components.Script;
import Engine.Input.*;
import Engine.Utils.Vector2f;

public class testScript extends Script {
    // entity instantiation code
    //Entity.instantiate(AssetPool.getPreFab("test"), new Transform(new Vector2f(100, 100)));

    @Override
    public void init() {
    }

    @Override
    public void run() {

        if(Input.getAction("Input") != 0){
            System.out.println(Input.getAction("Input"));
        }
    }
}
