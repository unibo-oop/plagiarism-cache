/**
 *
 */
package it.unibo.isaccoop.model.spawn;

import java.util.List;
import java.util.stream.Stream;

import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.Point2D;

/**
 * SpawnOrdered class that implements Spawn interface, it models an ordered spawn.
 */
public class SpawnOrdered implements Spawn {

    /**
     * Method that sets the positions of the elements inside the room, in an ordered way.
     * @param elementsToSpawn
     * @param width of room
     * @param height of room
     */
    @Override
    public void setPosition(final List<MapElement> elementsToSpawn, final int width, final int height) {
        if (elementsToSpawn.size() == 1) {
            elementsToSpawn.get(0).setCoords(new Point2D(width / 2.0, height / 2.0));
        } else {
            Stream.iterate(0, x -> x + 1)
                .limit(elementsToSpawn.size())
                .forEach(i -> elementsToSpawn.get(i).setCoords(new Point2D(width / 2.0,
                    (height / (elementsToSpawn.size() + 1.0)) * (i + 1))));
        }
    }

}
