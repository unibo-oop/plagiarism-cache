package snakerunner.graphics.impl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snakerunner.graphics.panel.BasePanel;

/**
 * All panels, instead of extending JPanel, extend BasePanel,
 * In this way I define common characteristics for the panels,
 * Remember to add the background, fonts, and anything else they might have in common.
 */
public abstract class AbstractBasePanel extends JPanel implements BasePanel {

    private static final long serialVersionUID = 1L;
    private static final String TITLE = "Snake Runner";
    private static final int S_HEIGHT = 150;
    private static final int F_HEIGHT = 50;
    private final Font arial = new Font("Arial", Font.BOLD, 32);

    /**
     * Constructor's AbstracBasePanel.
     */
    public AbstractBasePanel() {
        super();
    }

    /**
     * Initialize the panels with common layout and styling.
     */
    protected final void initPanel() {
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(F_HEIGHT));
        add(createTitle(TITLE));
        add(Box.createVerticalStrut(S_HEIGHT));
    }

    /**
     * Create the title of the panels.
     * 
     * @param text Title's text.
     * @return JLabel with text.
     */
    protected JLabel createTitle(final String text) {
        final JLabel title = new JLabel(text);
        title.setFont(arial);
        title.setAlignmentX(CENTER_ALIGNMENT);
        return title;
    }

    /**
     * Creates a simple JLabel with the specified text.
     * 
     * @param text Label's text.
     * @return JLabel with text.
     */
    protected JLabel createLabel(final String text) {
        return new JLabel(text);
    }

    /**
     * Creates a customized JButton with the specified name.
     * 
     * @param name name the text to display on the button.
     * @return a JButton instance with the specified styling.
     */
    protected JButton createButton(final String name) {
        final JButton button = new JButton(name);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setContentAreaFilled(false);
        button.setAlignmentX(CENTER_ALIGNMENT);
        return button;
    }
}
