package Engine.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener{

    public KeyBoardListener() {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(KeyBinds.keyBoardBindings.containsKey(key)){
            KeyBinds.updateKeyBoardAction(key, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(KeyBinds.keyBoardBindings.containsKey(key)){
            KeyBinds.updateKeyBoardAction(key, false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
