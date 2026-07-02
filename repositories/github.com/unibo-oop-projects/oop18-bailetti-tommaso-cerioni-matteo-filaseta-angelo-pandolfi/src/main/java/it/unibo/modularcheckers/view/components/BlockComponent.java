package it.unibo.modularcheckers.view.components;

import java.awt.Insets;

import javax.swing.JButton;

/**
 * JButton for the Chess Board.
 */
public class BlockComponent extends JButton {

    private static final long serialVersionUID = -2897691187240352157L;

    /**
     * Build it easily.
     */
    public BlockComponent() {
        super();
        setMargin(new Insets(0, 0, 0, 0));
        setOpaque(true);
        setBorderPainted(false);
    }
}
