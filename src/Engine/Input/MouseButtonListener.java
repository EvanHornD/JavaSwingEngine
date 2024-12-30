package Engine.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseButtonListener implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
        int key = e.getButton();
        if(KeyBinds.mouseBindings.containsKey(key)){
            KeyBinds.updateMouseAction(key, true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int key = e.getButton();
        if(KeyBinds.mouseBindings.containsKey(key)){
            KeyBinds.updateMouseAction(key, false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
