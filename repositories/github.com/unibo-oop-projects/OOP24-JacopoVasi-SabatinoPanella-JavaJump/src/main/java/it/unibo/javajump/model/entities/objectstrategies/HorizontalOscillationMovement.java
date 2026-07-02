package it.unibo.javajump.model.entities.objectstrategies;

import it.unibo.javajump.model.entities.GameObject;

/**
 * The type Horizontal oscillation movement, implements the movement behaviour
 * for an object that oscillates horizontally.
 */
public final class HorizontalOscillationMovement implements MovementBehaviour {

    private final float minX;
    private final float maxX;
    private final float speed;
    private boolean goingRight;

    /**
     * Instantiates a new Horizontal oscillation movement.
     *
     * @param minX  the min x
     * @param maxX  the max x
     * @param speed the speed
     */
    public HorizontalOscillationMovement(final float minX, final float maxX, final float speed) {
        this.minX = minX;
        this.maxX = maxX;
        this.speed = speed;
        this.goingRight = true;
    }

    /**
     * {@inheritDoc} In the implementation of this method, the current X is updated in a direction
     * that oscillates between min and max X, while specifying if it is going right or left.
     */
    @Override
    public void update(final GameObject obj, final float deltaTime) {
        float currentX = obj.getX();
        if (goingRight) {
            currentX += speed * deltaTime;
            if (currentX > maxX) {
                currentX = maxX;
                goingRight = false;
            }
        } else {
            currentX -= speed * deltaTime;
            if (currentX < minX) {
                currentX = minX;
                goingRight = true;
            }
        }
        obj.setX(currentX);
    }
}
