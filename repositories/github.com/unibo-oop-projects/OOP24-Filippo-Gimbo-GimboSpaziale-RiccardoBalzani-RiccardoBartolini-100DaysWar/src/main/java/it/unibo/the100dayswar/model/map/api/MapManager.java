package it.unibo.the100dayswar.model.map.api;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.awt.Dimension;
import java.io.Serializable;

import it.unibo.the100dayswar.commons.patterns.Observable;
import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Interface that model the concept of Mapmanager for the managment of the iteration between the soldiers and the bonus cells.
 */
public interface MapManager extends Observer<Pair<Unit, Cell>>, Observable<Pair<Player, Unit>>, Serializable {

    /**
     * method that update the map with the new position of the soldier.
     * @param pair the pair of the soldier and the new position.
     */
    @Override
    void update(Pair<Unit, Cell> pair);

    /**
     * @return the cells in possession of the players.
     */
    Map<Player, Set<Cell>> getPlayersCells();
    /**
     * @return the spawn cell in possession of the bot.
     */
    Cell getBotSpawn();

    /**
     * @return the spawn cell in possession of the player.
     */
    Cell getPlayerSpawn();

    /**
     * @return map as a stream.
     */
    Stream<Cell> getMapAsAStream();

    /**
     * @return the dimension of the map.
     */
    Dimension getMapDimension();

    /**
     * Notify the observers.
     * 
     * @param source the source of the notification.
     */
    void notifyObservers(Pair<Player, Unit> source);

    /**
     * @param width the width of the map.
     * @param height the height of the map.
     * @param cellStream the stream of the cells.
     * @return the map as a matrix.
     */
    static Cell[][] createMapFromStream(final int width, final int height, final Stream<Cell> cellStream) {

        final Cell[][] map = new Cell[width][height];
        final Iterator<Cell> iterator = cellStream.iterator();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (iterator.hasNext()) {
                    map[x][y] = iterator.next();
                }
            }
        }

        return map;
    }
}
