package it.unibo.arkanoid.model;

import it.unibo.arkanoid.subject.PowerUpType;
import it.unibo.arkanoid.subject.Subject;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * Factory interface for {@link Subject}.
 */
public interface SubjectFactory {

    /**
     * 
     * @param position
     *          space coordinate
     * @param velocity
     *          ball velocity
     * @return
     *          the ball created
     */
    Subject createBall(Vector2D position, Vector2D velocity);

    /**
     * 
     * @param position
     *          space coordinate
     * @param width
     *          brick width
     * @param height
     *          brick height
     * @return
     *          the indistructibleBrick created
     */
    Subject createIndestructibleBrick(Vector2D position, double width, double height);

    /**
     * 
     * @param position
     *          space coordinate
     * @param width
     *          brick width
     * @param height
     *          brick height
     * @return
     *          the simpleBrick created
     */
    Subject createSimpleBrick(Vector2D position, double width, double height);

    /**
     * 
     * @param position
     *          space coordinate
     * @param width
     *          brick width
     * @param height
     *          brick height
     * @param lives
     *          number of lives for this brick
     * @return
     *          the multipleBrick created
     */
    Subject createMultipleBrick(Vector2D position, double width, double height, int lives);

   /**
    * 
    * @return
    *           new paddle subject
    */
    Subject createPaddle();

    /**
     * 
     * @param position
     *          space coordinate
     * @param type
     *          powerUp's type
     * @return
     *          the created powerUp
     */
     Subject createPowerUp(Vector2D position, PowerUpType type);

    /**
     * 
     * @param position
     *          space coordinate
     * @param width
     *          block width
     * @param height
     *          block height
     * @return
     *          the block created
     */
    Subject createBlock(Vector2D position, double width, double height);

}
