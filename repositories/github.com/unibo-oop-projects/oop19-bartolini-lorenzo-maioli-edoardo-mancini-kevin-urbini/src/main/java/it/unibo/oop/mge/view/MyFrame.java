package it.unibo.oop.mge.view;

import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JFrame specialization.
 */
public class MyFrame extends JFrame {

    private static final long serialVersionUID = -1256994087403826079L;
    private final JPanel mainPanel;

    /**
     * Instantiates a new my frame.
     *
     * @param title the frame's title
     * @param lm the frame's LayoutManager
     */
    public MyFrame(final String title, final LayoutManager lm) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainPanel = new JPanel(lm);
        this.getContentPane().add(this.mainPanel);
    }

    /**
     * Gets the main panel.
     *
     * @return the main panel
     */
    public final JPanel getMainPanel() {
        return this.mainPanel;
    }
}
