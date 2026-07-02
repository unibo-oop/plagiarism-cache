package spacesurvival.utilities.gameobject;

import java.awt.Dimension;

import spacesurvival.model.common.P2d;
import spacesurvival.model.gameobject.Edge;
import spacesurvival.utilities.RandomUtils;
import spacesurvival.utilities.SystemVariables;
import spacesurvival.utilities.dimension.Screen;

/**
 * Utils for position.
 */
public final class PositionUtils {

    /**
     * Generate a random position to spawn a certain object.
     * 
     * @param objectDim the object dimension to spawn
     * @return return a random point on an edge
     */
    public static P2d generateSpawnPoint(final Dimension objectDim) {
        int xAxis = 0;
        int yAxis = 0;

        switch (Edge.random()) {
        case TOP:
            xAxis = RandomUtils.RANDOM.nextInt(Screen.WIDTH_FULLSCREEN);
            yAxis = 0 - (int) objectDim.getHeight();
            break;
        case BOTTOM:
            xAxis = RandomUtils.RANDOM.nextInt(Screen.WIDTH_FULLSCREEN);
            yAxis = Screen.WIDTH_FULLSCREEN;
            break;
        case LEFT:
            xAxis = 0 - (int) objectDim.getWidth();
            yAxis = RandomUtils.RANDOM.nextInt(Screen.HEIGHT_FULLSCREEN);
            break;
        case RIGHT:
            xAxis = Screen.WIDTH_FULLSCREEN;
            yAxis = RandomUtils.RANDOM.nextInt(Screen.HEIGHT_FULLSCREEN);
            break;
        default:
            break;
        }

        return new P2d(xAxis, yAxis);
    }

    /**
     * @return a random point inside the game screen
     */
    public static P2d generateRandomPoint() {
        return new P2d(RandomUtils.RANDOM.nextInt((int) (Screen.WIDTH_FULLSCREEN * SystemVariables.SCALE_X)),
                RandomUtils.RANDOM.nextInt((int) (Screen.HEIGHT_FULLSCREEN * SystemVariables.SCALE_Y)));
    }


    private PositionUtils() {
    }
}
