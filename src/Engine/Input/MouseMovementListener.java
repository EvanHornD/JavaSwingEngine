package Engine.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;



public class MouseMovementListener implements MouseMotionListener {

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Input.updateMousePosition(x, y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Input.updateMousePosition(x, y);
    }
}
