package it.unibo.javajump.view;

import it.unibo.javajump.controller.input.InputManager;

import javax.swing.JFrame;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.io.Serializable;

import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.Constants.SERIAL_ID;

/**
 * The implementation of the GameFrame interface.
 */
public final class GameFrameImpl extends JFrame implements GameFrame, Serializable {
    @Serial
    private static final long serialVersionUID = SERIAL_ID;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp(final InputManager inputManager, final int height, final int width,
                      final MainGameView view, final String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.addKeyListener(inputManager);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.add((Component) view);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                GameFrameImpl.this.setPreferredSize(e.getComponent().getSize());
                GameFrameImpl.this.pack();
            }
        });
        this.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeGame() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}

