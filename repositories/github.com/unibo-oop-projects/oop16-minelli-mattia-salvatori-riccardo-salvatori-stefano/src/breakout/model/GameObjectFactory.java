package breakout.model;

import breakout.model.entities.Ball;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickType;
import breakout.model.entities.Paddle;
import breakout.model.entities.Wall;
import breakout.model.entities.Wall.WallPos;
import javafx.util.Pair;

/**
 * Creates the game objects.
 */
public interface GameObjectFactory {

    /**
     * @return a ball.
     */
    Ball createBall();

    /**
     * @return a paddle.
     */
    Paddle createPaddle();

    /**
     * @param pos
     *            the position on the screen. 
     * @return a wall.
     */
    Wall createWall(final WallPos pos);

    /**
     * @return a brick of the given type {@link BrickType};
     * @param type
     *            the type of brick to create
     * @param position
     *            a Pair of integers describing position in the grid of birkcs
     */
    Brick createBrick(final BrickType type, final Pair<Integer, Integer> position);

}
