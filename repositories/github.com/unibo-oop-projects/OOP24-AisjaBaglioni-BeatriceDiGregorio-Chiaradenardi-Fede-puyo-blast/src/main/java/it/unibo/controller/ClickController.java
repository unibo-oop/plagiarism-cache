package it.unibo.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

import it.unibo.model.Point2DI;
import it.unibo.view.interfaces.ClickInterface;

/**
 * This class is a controller that manages mouse click
 * interactions with objects that implement the ClickInterface
 */
public class ClickController implements MouseListener {

    /**
     * A set that holds all objects that can be clicked. These objects must
     * implement the {@link ClickInterface}
     */
    private Set<ClickInterface> clickables;

    /**
     * Constructs a new ClickController,
     * initializing it with a set of clickable objects
     */
    public ClickController(Set<ClickInterface> clickables) {
        this.clickables = clickables;
    }

    /**
     * Adds a new clickable object to the set
     * 
     * @param clickable The object implementing the ClickInterface to be added
     */
    public void addClickable(ClickInterface clickable) {
        clickables.add(clickable);
    }

    /**
     * This method is called when the mouse is clicked. It determines the position
     * of the click and checks which clickable object was clicked, then triggers the
     * corresponding action.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point2DI pos = new Point2DI(x, y);
        for (ClickInterface clickable : clickables) {
            if (clickable.isClicked(pos)) {
                clickable.doAction();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}