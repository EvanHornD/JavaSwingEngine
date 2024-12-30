package Engine.Input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public final class KeyBinds { 

    static Map<Integer, String> keyBoardBindings = new HashMap<>(Map.of(
        KeyEvent.VK_W, "Up",
        KeyEvent.VK_UP, "Up",
        KeyEvent.VK_DOWN, "Down",
        KeyEvent.VK_S, "Down",
        KeyEvent.VK_LEFT, "Left",
        KeyEvent.VK_A, "Left",
        KeyEvent.VK_RIGHT, "Right",
        KeyEvent.VK_D, "Right",
        KeyEvent.VK_SPACE, "test"
    ));

    static Map<Integer, String> mouseBindings = new HashMap<>(Map.of(
        MouseEvent.BUTTON1, "Input"
    ));

    static Map<String, Integer> Actions = new HashMap<>(Map.of(
        "Up", 0,
        "Down", 0,
        "Left", 0,
        "Right", 0,
        "Input", 0,
        "test",0
    ));

    public KeyBinds() {
    }

    public static boolean containsKeyBind(int key){
        return KeyBinds.keyBoardBindings.containsKey(key);
    }

    public static boolean containsMouseBind(int key){
        return KeyBinds.mouseBindings.containsKey(key);
    }

    public static int getActionFrame(String action){
        return KeyBinds.Actions.get(action);
    }

    public static void addKeyBind(int key, String action){
        KeyBinds.keyBoardBindings.put(key, action);
    }

    public static void addMouseBind(int button, String action){
        KeyBinds.mouseBindings.put(button, action);
    }

    public static void addAction(String action){
        KeyBinds.Actions.put(action,0);
    }

    public static void updateKeyBoardAction(int Key, boolean isPressed){
        if(isPressed){
            KeyBinds.Actions.replace(keyBoardBindings.get(Key), 1);
            return;
        }
        KeyBinds.Actions.replace(keyBoardBindings.get(Key), -1);
    }

    public static void updateMouseAction(int Key, boolean isPressed){
        if(isPressed){
            KeyBinds.Actions.replace(mouseBindings.get(Key), 1);
            return;
        }
        KeyBinds.Actions.replace(mouseBindings.get(Key), -1);
    }

    public static void updateKeyActions(){
        for (Map.Entry<String,Integer> action : KeyBinds.Actions.entrySet()) {
            String actionName = action.getKey();
            int actionFrame = action.getValue();
            if(actionFrame>0){
                KeyBinds.Actions.replace(actionName, actionFrame+1);
            } else if(actionFrame<0){
                KeyBinds.Actions.replace(actionName, 0);
            }
        }
    }
}
