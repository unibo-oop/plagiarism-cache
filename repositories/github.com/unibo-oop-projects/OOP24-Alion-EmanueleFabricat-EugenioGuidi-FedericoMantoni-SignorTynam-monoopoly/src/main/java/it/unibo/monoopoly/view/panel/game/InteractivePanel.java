package it.unibo.monoopoly.view.panel.game;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * {@link JPanel} in which to place the panels where choices can be made.
 */
public final class InteractivePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the class.
     * 
     * @param initPanel first {@link JPanel}
     */
    public InteractivePanel(final JPanel initPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(initPanel);
    }
}
