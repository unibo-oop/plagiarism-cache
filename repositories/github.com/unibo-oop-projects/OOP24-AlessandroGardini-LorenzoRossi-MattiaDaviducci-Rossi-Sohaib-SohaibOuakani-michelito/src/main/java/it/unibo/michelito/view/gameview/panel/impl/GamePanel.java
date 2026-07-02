package it.unibo.michelito.view.gameview.panel.impl;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.view.gameview.panel.api.InputHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serial;
import java.util.Collections;
import java.util.Optional;
import java.util.Objects;
import java.util.Set;
import javax.swing.JPanel;

/**
 * Implementation of the game panel.
 */
public final class GamePanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The width of the game panel.
     */
    public static final int WIDTH = 840;
    /**
     * The height of the game panel.
     */
    public static final int HEIGHT = 600;

    private Set<GameObject> gameObjects;
    private int currentLives;
    private int currentLevelNumber;
    private final InputHandler inputHandler = new InputHandlerImpl();

    /**
     * Constructor for the game panel.
     */
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paint(final Graphics g) {
        super.paintComponent(g);
        if (Objects.nonNull(gameObjects)) {
            final var doorPosition = doorPosition(gameObjects)
                    .orElseThrow(() -> new IllegalStateException("Every maze must contain a door"));

            final var isDoorBlocked = isDoorBlock(gameObjects, doorPosition);

            gameObjects.stream()
                    .filter(o -> !isDoorBlocked || !o.objectType().equals(ObjectType.DOOR))
                    .forEach(o -> GameObjectRenderer.render(g, o, this));

            GameStatisticRenderer.render(g, this, this.currentLives, this.currentLevelNumber);
        } else {
            g.setColor(Color.RED);
            g.drawString("No game objects to display", 10, 10);
        }
    }

    /**
     * Displays the game objects.
     *
     * @param gameObjects the game objects to display.
     * @param lives the number of lives.
     * @param levelNumber the level number.
     */
    public void display(final Set<GameObject> gameObjects, final int lives, final int levelNumber) {
        boolean needsRepaint = false;
        if (this.currentLives != lives) {
            this.currentLives = lives;
            needsRepaint = true;
        }
        if (this.currentLevelNumber != levelNumber) {
            this.currentLevelNumber = levelNumber;
            needsRepaint = true;
        }
        if (!Objects.equals(this.gameObjects, gameObjects)) {
            this.gameObjects = Collections.unmodifiableSet(gameObjects);
            needsRepaint = true;
        }
        if (needsRepaint) {
            this.repaint();
        }
    }

    /**
     * Get the keys pressed.
     *
     * @return the keys pressed.
     */
    public Set<Integer> getKeysPressed() {
        return inputHandler.keysPressed();
    }

    private static Optional<Position> doorPosition(final Set<GameObject> gameObjects) {
        return gameObjects.stream()
                .filter(o -> o.objectType().equals(ObjectType.DOOR))
                .map(GameObject::position)
                .findAny();
    }

    private static boolean isDoorBlock(final Set<GameObject> gameObjects, final Position doorPosition) {
        return gameObjects.stream()
                .filter(o -> o.objectType().equals(ObjectType.BOX))
                .anyMatch(o -> o.position().equals(doorPosition));
    }
}
