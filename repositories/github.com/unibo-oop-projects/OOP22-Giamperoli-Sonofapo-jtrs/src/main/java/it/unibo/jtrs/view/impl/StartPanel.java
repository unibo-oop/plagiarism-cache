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
 * This class model the start panel presented to the user a te beginning
 * of the game.
 */
public class StartPanel extends GenericPanel {

    public static final long serialVersionUID = 4328743;

    private final transient Chronometer chrono = new Chronometer();
    private final JLabel blinkLabel;
    private boolean active;

    /**
     * Constructor.
     */
    public StartPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        final JLabel title = new Label("JTETRIS", Constants.StartPanel.TITLE_SIZE);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(title);
        this.add(Box.createVerticalStrut(Constants.StartPanel.TITLE_INTERLINE));

        final JLabel commands = new Label("COMMANDS:", Constants.StartPanel.SUBTITLE_SIZE);
        commands.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(commands);
        this.add(Box.createVerticalStrut(Constants.StartPanel.SUBTITLE_INTERLINE));

        Constants.StartPanel.COMMANDS_LIST.stream()
            .map(c -> new Label(c, Constants.StartPanel.LIST_SIZE))
            .peek(l -> l.setAlignmentX(JComponent.CENTER_ALIGNMENT))
            .forEach(l -> {
                this.add(l);
                this.add(Box.createVerticalStrut(Constants.StartPanel.LIST_INTERLINE));
            });

        this.add(Box.createVerticalStrut(Constants.StartPanel.TITLE_INTERLINE));
        this.blinkLabel = new Label("Press SPACE to start", Constants.StartPanel.SUBTITLE_SIZE);
        this.blinkLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(this.blinkLabel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        if (this.chrono.elapsed() > 1000) {
            this.blinkLabel.setForeground(this.active ? new Color(0, true) : Constants.Label.TEXT_COLOR);
            this.active = !this.active;
            this.chrono.reset();
        }
    }

}
