package it.unibo.jtrs.view.impl;

import javax.swing.JFrame;

/**
 * A class representing the main window frame.
 */
public class ApplicationFrame extends JFrame {

    public static final long serialVersionUID = 4328743;

    /**
     * Constructor.
     *
     * @param panel the application panel to show
     */
    public ApplicationFrame(final ApplicationPanel panel) {
        super();

        this.add(panel);

        super.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
