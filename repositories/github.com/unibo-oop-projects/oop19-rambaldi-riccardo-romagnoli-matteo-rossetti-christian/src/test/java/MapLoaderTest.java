import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.obstacle.Obstacle;
import model.obstacle.ObstacleBehavior;
import model.world.MapLoader;
import model.world.MapLoaderImpl;

public class MapLoaderTest {

    private static final int TWENTY_OBSTACLES = 20;
    private static final int NUM_RED_IN_TWENTY_OBSTACLES = 10;
    private static final int NUM_BLUE_IN_TWENTY_OBSTACLES = 8;
    private static final int NUM_GREEN_IN_TWENTY_OBSTACLES = 1;
    private static final int NUM_PURPLE_IN_TWENTY_OBSTACLES = 1;

    private int numRedObstacles;
    private int numBlueObstacles;
    private int numGreenObstacles;
    private int numPurpleObstacles;

    @Test
    void testMapLoader() {
        final MapLoader mapLoader = new MapLoaderImpl();
        final List<Obstacle> zeroObstacles = mapLoader.loadMap("testMap0Obstacles");
        assertEquals(0, zeroObstacles.size());

        final List<Obstacle> twentyObstacles = mapLoader.loadMap("testMap20Obstacles");
        this.numRedObstacles = 0;
        this.numBlueObstacles = 0;
        this.numGreenObstacles = 0;
        this.numPurpleObstacles = 0;
        twentyObstacles.forEach(o -> {
            if (o.getBehavior() == ObstacleBehavior.RED) {
                this.numRedObstacles += 1;
            } else if (o.getBehavior() == ObstacleBehavior.PURPLE) {
                this.numPurpleObstacles += 1;
            } else if (o.getBehavior() == ObstacleBehavior.GREEN) {
                this.numGreenObstacles += 1;
            } else {
                this.numBlueObstacles += 1;
            }
        });
        assertEquals(TWENTY_OBSTACLES, twentyObstacles.size());
        assertEquals(NUM_BLUE_IN_TWENTY_OBSTACLES, this.numBlueObstacles);
        assertEquals(NUM_GREEN_IN_TWENTY_OBSTACLES, this.numGreenObstacles);
        assertEquals(NUM_RED_IN_TWENTY_OBSTACLES, this.numRedObstacles);
        assertEquals(NUM_PURPLE_IN_TWENTY_OBSTACLES, this.numPurpleObstacles);
    }
}
