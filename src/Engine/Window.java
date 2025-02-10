package Engine;

import Engine.Input.KeyBoardListener;
import Engine.Input.MouseButtonListener;
import Engine.Input.MouseMovementListener;
import Engine.Utils.Vector2f;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

    public JFrame frame;
    public JPanel panel;
    private Rectangle bounds;
    private Vector2f windowSizeRatio;
    public BufferedImage buffer;
    public Graphics2D graphics;

    public Window(){
    }

    public void init(){
        this.frame = new JFrame();

        // initialize the frame
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.bounds = new Rectangle(1920, 1080);
        this.windowSizeRatio = new Vector2f(1, 1);

        this.frame.setResizable(true);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // listener for resizing the window
        this.frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                bounds = new Rectangle(frame.getWidth(), frame.getHeight());
                windowSizeRatio = new Vector2f(frame.getWidth()/1920f, frame.getHeight()/1080f);
                System.out.println(frame.getHeight());
                buffer = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_4BYTE_ABGR);
                graphics = buffer.createGraphics();
            }
        });

        // initialize the panel to draw the buffer image whenever it gets painted
        this.panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                if (buffer!=null) {
                    g.drawImage(buffer, 0, 0, null);
                }
            }
        };
        this.panel.setIgnoreRepaint(true);
        this.panel.setBounds(bounds);
        this.panel.setFocusable(true);
        this.panel.requestFocus();

        // add the mouse and keyboard listeners
        KeyBoardListener keyListener = new KeyBoardListener();
        MouseButtonListener mouseListener = new MouseButtonListener();
        MouseMovementListener mouseMovementListener = new MouseMovementListener();

        this.panel.addKeyListener(keyListener);
        this.panel.addMouseListener(mouseListener);
        this.panel.addMouseMotionListener(mouseMovementListener);

        frame.add(panel);

        // create buffer image
        buffer = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_4BYTE_ABGR);
        graphics = buffer.createGraphics();

        Main main = new Main(this);
        main.init();
        main.run();
    }

    public Vector2f toScreenCoords(Vector2f vector2f){
        return Vector2f.multiply(vector2f, windowSizeRatio);
    }

    public void render(BufferedImage image){
        graphics.drawImage(image,0,0,null);
        this.panel.repaint();
        //this.buffer = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void dispose(){
        this.frame.dispose();
    }
}
