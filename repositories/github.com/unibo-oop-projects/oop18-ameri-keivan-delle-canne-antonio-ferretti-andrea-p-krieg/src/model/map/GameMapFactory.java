package model.map;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import javafx.util.Pair;
import model.managers.SkillTreeManager;
import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.player.Player;
import util.Coordinates;

/**
 * a factory of game maps.
 */
public interface GameMapFactory {

    /**
     * @param background the background that the generated map will have
     * @param size       the size of the map to generate
     * @return the basic version of the game map
     */
    ModifiableGameMap getEmptyMapFromBackground(Map<Coordinates, Terrain> background, Pair<Integer, Integer> size);

    /**
     * @param size             the size of the map to generate height, width
     * @param players          the players to be inserted
     * @param skillTreeManager the skillTree on vich the structures are based, it's
     *                         used to get the right production of resources and
     *                         units based on the current level of the skillTree of
     *                         each player
     * @return a game map that has all of the passed terrain on it
     * @throws IllegalArgumentException if one of the passed structures can't be
     *                                  built in any of the passed terrains
     */
    ModifiableGameMap gameMapWithIslands(Pair<Integer, Integer> size, Set<Player> players,
            SkillTreeManager skillTreeManager);

    /**
     * @param size       the size of the map to generate
     * @param players    the players to be inserted
     * @param structures the structures to be put on the map
     * @param terrains   the terrains to be put on the map
     * @param skillTreeManager the skillTree on vich the structures are based, it's
     *                         used to get the right production of resources and
     *                         units based on the current level of the skillTree of
     *                         each player
     * @return a game map that has all of the passed terrain on it
     * @throws IllegalArgumentException if one of the passed structures can't be
     *                                  built in any of the passed terrains
     */
    ModifiableGameMap gameMapWithIslandsFromElements(Pair<Integer, Integer> size, Set<Player> players,
            List<Supplier<Terrain>> terrains, List<Supplier<Structure>> structures, SkillTreeManager skillTreeManager);
}
