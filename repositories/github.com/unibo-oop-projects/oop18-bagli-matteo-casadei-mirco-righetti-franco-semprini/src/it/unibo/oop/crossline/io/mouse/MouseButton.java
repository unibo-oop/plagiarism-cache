package it.unibo.oop.crossline.io.mouse;

import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.crossline.debug.Debugger;
import it.unibo.oop.crossline.io.Button;

/**
 * The class of MouseButton.
 */
public class MouseButton extends Button implements MouseObserver {

    /**
     * The dragged boolean.
     */
    private boolean dragged;
    /**
     * The scrolled amount.
     */
    private int scrolledAmount;
    /**
     * The mouse location in (x,y) mouse location vector 2d.
     */
    private Vector2 mouseLocation = new Vector2(0, 0);

    /**
     * Constructor of mouse button.
     * 
     * @param keyCode the keyCode
     * @param name    the name
     * @param action  the action
     */
    public MouseButton(final int keyCode, final String name, final Runnable action) {
        super(keyCode, name, action);
    }

    @Override
    public final boolean update(final int x, final int y, final boolean dragger, final int keyCode) {
        this.setDragged(dragger);
        if (this.getKeyCode() == keyCode) {
            printMouseKeyPressed(keyCode);
            if (dragger) {
                this.getAction().run();
                return true;
            } else {
                return true;
            }
        }
        updatePosition(x, y);
        printMouseKeyPressed(keyCode);
        printIsDragged();

        return false;
    }

    @Override
    public final boolean updatePosition(final int x, final int y) {
        this.mouseLocation.x = x;
        this.mouseLocation.y = y;
        printMousePosition();
        return true;
    }

    @Override
    public final boolean updatePositionDragged(final int x, final int y) {
        updatePosition(x, y);
        this.dragged = true;
        printMousePosition();
        printIsDragged();
        return true;
    }

    @Override
    public final boolean updateScrolled(final int scrolledAmount) {
        this.scrolledAmount += scrolledAmount;
        printMouseScrolledAmount();
        return false;
    }

    /**
     * Called for print the mouse key pressed. Mouse has mapped key like this: 0 -
     * left mouse button 1 - right mouse button 2 - middle mouse button
     * 
     * @param keyCode - button key code
     */
    private void printMouseKeyPressed(final int keyCode) {
        String button = null;
        if (keyCode == 0) {
            button = "Left key";
        } else if (keyCode == 1) {
            button = "Right key";
        } else if (keyCode == 2) {
            button = "Middle key";
        } else {
            button = "Unknown";
        }
        Debugger.getInstance().printMessage("Mouse key pressed: " + button);

    }

    /**
     * Called for print the mouse current position.
     */
    private void printMousePosition() {
        Debugger.getInstance().printMessage("[x,y]=(" + this.mouseLocation.x + ", " + this.mouseLocation.y + ")");
    }

    /**
     * Called for print dragged state.
     */
    private void printIsDragged() {
        Debugger.getInstance().printMessage("Is dragged: " + this.dragged);
    }

    /**
     * Called for print mouse scrolled amount.
     */
    private void printMouseScrolledAmount() {
        Debugger.getInstance().printMessage("Mouse scrolled amount: " + this.scrolledAmount);
    }

    /**
     * @return the isDragged
     */
    public final boolean isDragged() {
        return dragged;
    }

    /**
     * @param isDragged the isDragged to set
     */
    public final void setDragged(final boolean isDragged) {
        this.dragged = isDragged;
    }
    /**
     * @return the scrolledAmount
     */
    public int getScrolledAmount() {
        return scrolledAmount;
    }

    /**
     * @param scrolledAmount the scrolledAmount to set
     */
    public void setScrolledAmount(final int scrolledAmount) {
        this.scrolledAmount = scrolledAmount;
    }

    /**
     * @return the mouseLocation
     */
    public final Vector2 getMouseLocation() {
        return mouseLocation;
    }

    /**
     * @param mouseLocation the mouseLocation to set
     */
    public final void setMouseLocation(final Vector2 mouseLocation) {
        this.mouseLocation = mouseLocation;
    }

    /**
     * ToString method.
     */
    @Override
    public String toString() {
        return "\nMouseButton [" + "keyCode=" + this.getKeyCode() + "name=" + this.getName() + ", action="
                + this.getAction() + ", isDragged=" + this.isDragged() + ", mouseLocation=" + this.getMouseLocation()
                + "]";
    }

    /**
     * Equals method.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getAction() == null) ? 0 : this.getAction().hashCode());
        final int magic1 = 1231;
        final int magic2 = 1231;
        result = prime * result + (dragged ? magic1 : magic2);
        result = prime * result + this.getKeyCode();
        result = prime * result + ((mouseLocation == null) ? 0 : mouseLocation.hashCode());
        result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
        return result;
    }

    /**
     * HashCode method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MouseButton other = (MouseButton) obj;
        if (this.getAction() == null) {
            if (other.getAction() != null) {
                return false;
            }
        } else if (!this.getAction().equals(other.getAction())) {
            return false;
        }
        if (dragged != other.dragged) {
            return false;
        }
        if (this.getKeyCode() != other.getKeyCode()) {
            return false;
        }
        if (mouseLocation == null) {
            if (other.mouseLocation != null) {
                return false;
            }
        } else if (!mouseLocation.equals(other.mouseLocation)) {
            return false;
        }
        if (this.getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!this.getName().equals(other.getName())) {
            return false;
        }
        return true;
    }
}
