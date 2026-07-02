package it.unibo.jtrs.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

import it.unibo.jtrs.controller.api.Application;
import it.unibo.jtrs.game.core.inputs.KeyboardReader;
import it.unibo.jtrs.model.api.GameModel.GameState;
import it.unibo.jtrs.utils.ResourceLoader;
import it.unibo.jtrs.view.api.GenericPanel;
/**
 * A class modelling the main panel to be inserted in a frame.
 */
public class ApplicationPanel extends JLayeredPane {

    public static final long serialVersionUID = 4328743;

    private final transient Application application;
    private final List<GenericPanel> panels = new ArrayList<>();
    private transient BufferedImage background;

    /**
     * Constructor.
     *
     * @param application the application this panel should show
     */
    public ApplicationPanel(final Application application) {
        this.setLayout(new OverlayLayout(this));

        this.application = application;

        this.panels.add(GameState.START.ordinal(), new StartPanel());
        this.panels.add(GameState.RUNNING.ordinal(), new MainPanel(application));
        this.panels.add(GameState.PAUSE.ordinal(), new MessagePanel("PAUSE", "Press SPACE to resume"));
        this.panels.add(GameState.OVER.ordinal(), new MessagePanel("GAME OVER", "Press ESC to exit the game"));

        for (final GameState s : GameState.values()) {
            final var i = s.ordinal();
            this.panels.get(i).setVisible(false);
            this.add(this.panels.get(i), i);
        }

        this.addKeyListener(new KeyboardReader(this.application));
        this.setFocusable(true);

        try {
            this.background = ImageIO.read(ResourceLoader.loadAsStream("background.jpg"));
        } catch (IOException e) { // fallback to default background
            this.setOpaque(true);
            this.setBackground(Color.DARK_GRAY);
            for (final GameState s : GameState.values()) {
                final var i = s.ordinal();
                this.panels.get(i).setOpaque(true);
                this.panels.get(i).setBackground(Color.DARK_GRAY);
            }

            this.background = null;
        }
    }

    /**
     * Redraws the application components.
     */
    public void redraw() {
        final var layer = this.application.getState().ordinal();
        this.setActiveLayer(layer);
        this.panels.get(layer).redraw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, this);
    }

    private void setActiveLayer(final int layer) {
        for (int i = 0; i < this.panels.size(); i++) {
            this.panels.get(i).setVisible(i == layer);
        }
    }
}
