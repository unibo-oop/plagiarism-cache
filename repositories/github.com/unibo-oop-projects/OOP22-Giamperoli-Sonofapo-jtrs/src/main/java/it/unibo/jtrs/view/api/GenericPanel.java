package it.unibo.jtrs.view.api;

import javax.swing.JPanel;

/**
 * A class that models a generic panel which has to be redrawn.
 */
public abstract class GenericPanel extends JPanel {

    public static final long serialVersionUID = 4328743;

    /**
     * Redraws the panel.
     */
    public abstract void redraw();
}
