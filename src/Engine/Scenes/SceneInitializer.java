package Engine.Scenes;

import Engine.Window;

public abstract class SceneInitializer {
    public Window window;
    public abstract void init(Scene scene);
    public abstract void loadResources(Scene scene);
}
