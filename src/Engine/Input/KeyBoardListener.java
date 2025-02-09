package Engine.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener{

    public KeyBoardListener() {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(Input.keyBoardBindings.containsKey(key)){
            Input.updateKeyBoardAction(key, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(Input.keyBoardBindings.containsKey(key)){
            Input.updateKeyBoardAction(key, false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
