package it.unibo.isaccoop.model.spawn;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import it.unibo.isaccoop.model.boundingbox.CircleBoundingBox;
import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.Point2D;

/**
 * SpawnRandom class that implements Spawn interface, it models a random spawn.
 */
public class SpawnRandom implements Spawn {

    /**
     * Method that set a random position of the elements inside the room, in a random way.
     * @param elementsToSpawn
     * @param width of room
     * @param height of room
     */
    @Override
    public void setPosition(final List<MapElement> elementsToSpawn, final int width, final int height) {
        elementsToSpawn.forEach(e -> {
            final double radius = ((CircleBoundingBox) e.getBox()).getRadius();
            e.setCoords(new Point2D(ThreadLocalRandom.current()
                    .nextDouble(radius + radius / 2, width - (radius + radius / 2)),
                    ThreadLocalRandom.current()
                    .nextDouble(radius + radius / 2, height - (radius + radius / 2))));
        });

    }

}
