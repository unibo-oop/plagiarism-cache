package zengine.constants;

import java.awt.event.MouseEvent;

import zengine.interfaces.ZengineInputConstant;

/**
 * This enum is a list of mouse buttons that are passed as argument to some
 * particular functions.
 */
public enum ZengineMouseConstant implements ZengineInputConstant {
    // mouse buttons
    /**
     * Constant representing the left mouse button.
     */
    MB_LEFT(MouseEvent.BUTTON1),

    /**
     * Constant representing the right mouse button.
     */
    MB_RIGHT(MouseEvent.BUTTON3),

    /**
     * Constant representing a third mouse button. Not every mouse has got this
     * button.
     */
    MB_OTHER(MouseEvent.BUTTON2),

    /**
     * Constant representing whatsoever mouse button.
     */
    MB_ANY(MouseEvent.NOBUTTON),;

    private int value; // = 0;

    ZengineMouseConstant(final int keyCode) {
        value = keyCode;
    }

    @Override
    public int getValue() {
        return value;
    }

}
