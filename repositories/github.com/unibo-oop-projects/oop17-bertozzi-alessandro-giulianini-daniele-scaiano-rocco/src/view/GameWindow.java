package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import controller.KeyInput;
import game.GameImpl;
import game.GameMode;
import game.GameObject;
import game.ID;
import game.Player;
import utilities.Pair;

/**
 * The window used during the game. On this window are drawn all the entities while the game is running.
 */
public final class GameWindow extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 5598583322994584807L;

    /**
     * An identifier for the panel.
     */
    public static final String TITLE = "Game";

    private final HeadUpDisplay hud;
    private final BackgroundAnimator bgAnimator;
    private final EntityAnimator entAnimator;
    private final List<Pair<GameObject, Image>> entities;
    private double widthProportion;
    private double heightProportion;

    /**
     * @param mode the game mode chosen
     * @param input the class that will catch the input from keyboard
     */
    public GameWindow(final GameMode mode, final KeyInput input) {
        super();
        this.setLayout(new BorderLayout());
        this.bgAnimator = new BackgroundAnimator(GameWindow.TITLE);
        this.entAnimator = new EntityAnimator();
        this.entities = Collections.synchronizedList(new LinkedList<>());
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.hud = mode.equals(GameMode.SINGLEPLAYER) ? new SinglePlayerDisplay() : new MultiPlayerDisplay();
        this.setPreferredSize(new Dimension(ViewImpl.SCREEN_WIDTH, ViewImpl.SCREEN_HEIGHT));
        this.add(this.hud, BorderLayout.NORTH);
        this.addKeyListener(input);
        this.setFocusIfNotPresent();
    }

    private void setFocusIfNotPresent() {
        if (!this.hasFocus()) {
            this.requestFocusInWindow();
        }
    }

    /**
     * @param gameEntities all the entities in the game at the moment
     * @param score the current score
     * @param level the current level
     */
    public void render(final List<GameObject> gameEntities, final int score, final int level) {
        this.setFocusIfNotPresent();
        this.entities.clear();
        widthProportion = (double) this.getWidth() / (double) GameImpl.GAMEAREA_WIDTH;
        heightProportion = (double) this.getHeight() / (double) GameImpl.GAMEAREA_HEIGHT;
        gameEntities.stream().filter(e -> e.getHitbox() != null).map(e -> new Pair<>(e, entAnimator.loadImage(e)))
            .forEach(p -> this.entities.add(p));
        this.repaint();
        hud.render(gameEntities.stream().filter(e -> e.getID().equals(ID.PLAYER) || e.getID().equals(ID.PLAYER2))
                         .map(e -> (Player) e).collect(Collectors.toList()), score, level);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgAnimator.loadImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        this.entities.forEach(p -> {
            g.drawImage(p.getY(), 
            (int) (p.getX().getHitbox().getMinX() * this.widthProportion),
            (int) (p.getX().getHitbox().getMinY() * this.heightProportion), 
            (int) (p.getX().getHitbox().getWidth() * this.widthProportion), 
            (int) (p.getX().getHitbox().getHeight() * this.heightProportion), 
            this);
        });
    }
}
