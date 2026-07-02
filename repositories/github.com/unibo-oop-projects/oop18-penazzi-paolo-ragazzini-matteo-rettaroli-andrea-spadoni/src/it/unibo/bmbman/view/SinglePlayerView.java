package it.unibo.bmbman.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibo.bmbman.controller.game.KeyInput;
import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.leaderboard.PlayerScoreImpl;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.view.entities.BombView;
import it.unibo.bmbman.view.entities.EntityView;

/**
 * Frame for single player game mode.
 */
public class SinglePlayerView implements GameView {
    private static final int SECONDS_IN_MINUTE = 60;
    private final GUIFactory gui = new GUIFactoryImpl();
    private final Canvas canvas = new Canvas(); 
    private final JFrame frame = gui.createFrame();
    private BufferStrategy bs;
    private final JPanel sPanel = new JPanel();
    private final TopBar nPanel;
    private final Timer timer;
    private int seconds;
    private int minutes;
    /**
     * Construct the frame.
     * @param ki {@link KeyInput}
     * @param hero {@link HeroImpl}
     * @param ps {@link PlayerScoreImpl}
     */
    public SinglePlayerView(final KeyInput ki, final PlayerScoreImpl ps, final HeroImpl hero) {
        nPanel = new TopBar(gui, ps, hero);
        seconds = 0;
        minutes = 0;
        nPanel.getLabelTime().setText(String.format("%02d:%02d", minutes, seconds));
        this.timer = new Timer(1000, a -> {
            seconds++;
            if (seconds == SECONDS_IN_MINUTE) {
                minutes++;
                seconds = 0;
            }
            nPanel.getLabelTime().setText(this.getTime());
        });
        this.timer.start();
        frame.add(sPanel);
        frame.add(nPanel, BorderLayout.NORTH);
        canvas.setSize(TerrainFactoryImpl.TERRAIN_WIDTH, TerrainFactoryImpl.TERRAIN_HEGHT);
        sPanel.add(canvas, BorderLayout.SOUTH);
        frame.pack();
        canvas.addKeyListener(ki);
        bs = this.canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            bs = canvas.getBufferStrategy();
        }
    }
    /**
     * Stop time.
     */
    public void stopTimer() {
        this.timer.stop();
    }
    /**
     * Start time.
     */
    public void startTimer() {
        this.timer.start();
    }
    /**
     * Get timer in string format.
     * @return time 
     */
    public String getTime() {
        return String.format("%02d:%02d", minutes, seconds);
    }
    /**
     * Used to get {@link Graphics} component to update.
     * @return {@link Graphics}
     */
    public Graphics getGraphics() {
        return this.bs.getDrawGraphics();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Set<EntityView> entitiesView, final Set<BombView> bombs) {
        entitiesView.stream().filter(ev -> ev.getType() == EntityType.TILE).forEach(v -> v.render(getGraphics()));
        entitiesView.stream().filter(ev -> ev.getType() == EntityType.POWER_UP).forEach(v -> v.render(getGraphics()));
        entitiesView.stream().filter(ev -> ev.getType() == EntityType.BLOCK).forEach(v -> v.render(getGraphics()));
        entitiesView.stream().filter(ev -> ev.getType() == EntityType.WALL).forEach(v -> v.render(getGraphics()));
        entitiesView.stream().filter(ev -> ev.getType() == EntityType.HERO || ev.getType() == EntityType.MONSTER).forEach(v -> v.render(getGraphics()));
        bombs.forEach(b -> b.render(getGraphics()));
        this.bs.show();
        this.nPanel.render();
        this.frame.pack();
        this.canvas.requestFocus();
    }
    /**
     * Get the frame.
     * @return the current frame
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
