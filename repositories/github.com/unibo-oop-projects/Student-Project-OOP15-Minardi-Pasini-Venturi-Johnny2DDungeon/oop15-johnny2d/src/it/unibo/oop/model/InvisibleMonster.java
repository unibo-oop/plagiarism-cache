package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.INVISIBLE_ENEMY;

import java.awt.Rectangle;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;
import it.unibo.oop.utilities.Velocity;;

public class InvisibleMonster extends AbstractEnemy {

    private static final int SCORE_VALUE = 50;
    private final static int DMG = 2;

    private static int actionRadiusLenght = 1000;

    private boolean isVisible;
    private final Rectangle actionRadius;

    /**
     * Creates an invisible monster that reaches you if you enter his visibility
     * area
     * @param startingX
     *            The X position where the Monster is created
     * @param startingY
     *            The Y position where the Monster is created
     * @param movementVector
     *            The initial movement of the monster
     * @param speedValue 
     * 			  His own speed information
     */
    public InvisibleMonster(final double startingX, final double startingY, final Vector2 movementVector,
            final Velocity speedValue) {
        super(startingX, startingY, movementVector, speedValue);
        this.attachBehavior(new InvisibleEnemyBehavior(this));
        this.actionRadius = new Rectangle((int) startingX - actionRadiusLenght / 2,
                (int) startingY - actionRadiusLenght / 2, actionRadiusLenght, actionRadiusLenght);
    }

    /**
     * Checks if the monster collides the walls or it goes out from the playable
     * area
     * @param newPosition The next position where the function has to check collisions
     */
    public void checkCollision(final Position newPosition) throws CollisionHandlingException {
        final BasicMonster tmpEnemy = Factory.EnemiesFactory.generateStillBasicEnemy(newPosition.getIntX(),
                newPosition.getIntY());
        // Checks if the entity in the next move is inside the rectangular Arena
        if (!this.getEnvironment().getArena().isInside(tmpEnemy)) {
            throw new CollisionHandlingException("Next movement not inside the arena");
        }
        final long numWallCollisions = this.getEnvironment().getStableList().stream().filter(x -> x instanceof Wall)
                .filter(tmpEnemy::intersecate).count();

        if (numWallCollisions > 0) {
            throw new CollisionHandlingException("Next movement collides a wall");
        }
    }

    /**
     * Returns if the monster is visible or not
     */
    public boolean isVisible() {
        return this.isVisible;
    }

    /**
     * Sets the monster visibility
     */
    public void setVisible(final boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Gets the action and visibility radius of the monster
     */
    public Rectangle getActionRadius() {
        return this.actionRadius;
    }

    /**
     * Gets the {@link InvisibleMonster} height
     */
    protected int getEntityHeight() {
        return INVISIBLE_ENEMY.getHeight();
    }

    /**
     * Gets the {@link InvisibleMonster} width
     */
    protected int getEntityWidth() {
        return INVISIBLE_ENEMY.getWidth();
    }

    /**
     * Gets the score points for the {@link InvisibleMonster}
     */
    public int getScoreValue() {
        return InvisibleMonster.SCORE_VALUE;
    }

    /**
     * Gets the damage dealt by the {@link InvisibleMonster}
     */
    public int getDamage() {
        return InvisibleMonster.DMG;
    }
}