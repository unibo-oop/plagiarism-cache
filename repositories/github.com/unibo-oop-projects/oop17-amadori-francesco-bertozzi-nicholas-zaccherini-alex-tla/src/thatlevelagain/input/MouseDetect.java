package thatlevelagain.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * class that detect mouse click.
 *
 */

public class MouseDetect implements MouseListener {

    private final GamePanel component;

    /**
     * constructor.
     * @param component
     *   where to detect mouse event
     */
    public MouseDetect(final GamePanel component) {
        this.component = component;
    }

    @Override
    public final void mouseClicked(final MouseEvent arg0) {
        component.getManager().mouseClicked(arg0.getX(), arg0.getY());
    }

    @Override
    public final void mouseEntered(final MouseEvent arg0) { }

    @Override
    public final void mouseExited(final MouseEvent arg0) { }

    @Override
    public final void mousePressed(final MouseEvent arg0) {
        component.getManager().mousePressed(arg0.getX(), arg0.getY());
    }

    @Override
    public final void mouseReleased(final MouseEvent arg0) {
        component.getManager().mouseReleased(arg0.getX(), arg0.getY());
    }

}
