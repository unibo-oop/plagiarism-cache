package it.unibo.jtrs.view.impl;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;

import it.unibo.jtrs.utils.Chronometer;
import it.unibo.jtrs.view.api.GenericPanel;
import it.unibo.jtrs.view.custom.Constants;
import it.unibo.jtrs.view.custom.Label;

/**
 * A class modelling a message panel. This panel consists of a title
 * and a subtitle, both centered vertically and offsetted from the top
 * border.
 */
public class MessagePanel extends GenericPanel {

    public static final long serialVersionUID = 4328743;

    private final transient Chronometer chrono = new Chronometer();
    private final JLabel l2;
    private boolean active;

    /**
     * Constructor.
     *
     * @param t1 title
     * @param t2 subtitle
     */
    public MessagePanel(final String t1, final String t2) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        final JLabel l1 = new Label(t1, Constants.MessagePanel.TITLE_SIZE);
        l1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(l1);
        this.add(Box.createVerticalStrut(Constants.MessagePanel.INTERLINE));

        l2 = new Label(t2, Constants.MessagePanel.SUBTITLE_SIZE);
        l2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(l2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        if (this.chrono.elapsed() > Constants.MessagePanel.BLINK_TIME) {
            this.l2.setForeground(this.active ? new Color(0, true) : Constants.Label.TEXT_COLOR);
            this.active = !this.active;
            this.chrono.reset();
        }
    }

}
