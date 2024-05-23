package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * This class is to enable key events.
 *
 */
public abstract class ListenerPanel extends JPanel {
    public ListenerPanel() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        this.setFocusable(true);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> {
                    try {
                        doMoveRight();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                //todo: complete other move event
                case KeyEvent.VK_LEFT -> {
                    try {
                        doMoveLeft();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case KeyEvent.VK_UP -> {
                    try {
                        doMoveUp();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    try {
                        doMoveDown();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }


    public abstract void doMoveRight() throws IOException;
    public abstract void doMoveLeft() throws IOException;
    public abstract void doMoveUp() throws IOException;
    public abstract void doMoveDown() throws IOException;


}
