package it.unibo.pacman.view.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.pacman.controller.entities.Renderable;
import it.unibo.pacman.controller.game.KeyInput;
import it.unibo.pacman.controller.utilities.MapIO;
import it.unibo.pacman.model.entities.Mortal;
import it.unibo.pacman.model.leaderboard.PlayerScoreImpl;
import it.unibo.pacman.view.GUIFactory;
import it.unibo.pacman.view.RenderableUI;

/**
 * Used to create games' main UI.
 */
public final class GameView implements RenderableUI {
    private static final int SCALE_FACTOR = MapIO.getScale();
    private final JFrame frame;
    private final Canvas canvas;
    private final TopBar topbar;
    private BufferStrategy bs;
    private final int rows;
    private final int columns;

    /**
     * GameView constructor.
     * 
     * @param gf      the factory used to create UI objects
     * @param input   gets keyboard input
     * @param rows    is used for canvas height
     * @param columns is used for canvas width
     * @param psi     is used to get real time score
     * @param pacman  used to get pacman's lives
     */
    private GameView(final int rows, final int columns, final Canvas canvas, final JFrame frame, final TopBar topbar) {
        super();
        this.rows = rows;
        this.columns = columns;
        this.frame = frame;
        this.canvas = canvas;
        this.topbar = topbar;
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            bs = canvas.getBufferStrategy();
        }
        frame.pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Set<? extends Renderable> setView) {
        bs.getDrawGraphics().fillRect(0, 0, columns * SCALE_FACTOR, rows * SCALE_FACTOR);
        setView.stream().forEach(v -> v.render(this.bs.getDrawGraphics()));
        topbar.update();
        bs.show();
        canvas.requestFocus();
        bs.getDrawGraphics().dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.frame.dispose();
    }

    public static class Builder {
        private static final int SCALE_FACTOR = 30;
        private final GUIFactory gf;
        private JFrame frame;
        private JPanel mapPanel;
        private Canvas canvas;
        private TopBar topbar;
        private int rows;
        private int columns;

        /**
         * Assigns local fields to arguments values.
         * 
         * @param gf GUIFactory used to create GUI objects
         */
        public Builder(final GUIFactory gf) {
            this.gf = gf;
        }

        /**
         * Creates the game map.
         * 
         * @param input   the keyinput object for pacman's movement
         * @param rows    number of rows of the canvas
         * @param columns number of columns of the canvas
         * @return this
         */
        public Builder map(final KeyInput input, final int rows, final int columns) {
            this.rows = rows;
            this.columns = columns;
            frame = gf.createFrame();
            frame.setVisible(true);
            mapPanel = gf.createJPanel(new BorderLayout(), Color.BLACK);
            canvas = gf.createCanvas(columns * SCALE_FACTOR, rows * SCALE_FACTOR);
            canvas.addKeyListener(input);
            mapPanel.add(canvas, BorderLayout.CENTER);
            frame.add(mapPanel);
            return this;
        }

        /**
         * Creates the scoreboard.
         * 
         * @param pacman game's playable character
         * @param psi    player score used for real time display of score
         * @return this
         */
        public Builder scoreboard(final Mortal pacman, final PlayerScoreImpl psi) {
            topbar = new TopBar(gf, psi, pacman);
            mapPanel.add(topbar, BorderLayout.NORTH);
            return this;
        }

        /**
         * Creates a GameView object.
         * 
         * @return a GameView
         */
        public GameView build() {
            return new GameView(this.rows, this.columns, this.canvas, this.frame, this.topbar);
        }
    }
}
