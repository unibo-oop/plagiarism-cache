package breakout.model.entities;

import breakout.model.AdvancedMode;
import breakout.model.physics.MyBoundingBox;
import breakout.model.physics.Vector2D;
import javafx.geometry.Point2D;

/**
 * Defines a power up game object. A power up is an object that modifies the
 * status of the game for a certain amount of time then it ends is effect.
 */
public class PowerUp extends AbstractGameObject {

    private final PowerUpEffect puType;

    /**
     * Creates a PowerUp object of a given type in the given position. This
     * object will always fall in the direction of the player's paddle
     * 
     * @param type
     *            A powerup type defined in the enum {@link PowerUpEffect}
     * @param position
     *            The starting position of the power up
     * @param speed
     *            the speed of the power up
     * @param width
     *            the width of the power up
     * @param height
     *            the height of the power up
     */
    public PowerUp(final PowerUpEffect type, final Point2D position, final double speed, final double width,
            final double height) {
        super(position, Vector2D.valueOfPolar(speed, 90),
                new MyBoundingBox(position.getX(), position.getY(), width, height));
        this.puType = type;
    }

    /**
     * Apply the function of this power up type to the given model.
     * 
     * @param model
     *            the model where to apply the action of the power up
     */
    public void applyTo(final AdvancedMode model) {
        this.puType.action(model);
    }

    /**
     * @return the type of this power up.
     */
    public PowerUpEffect getEffectType() {
        return this.puType;
    }

}
