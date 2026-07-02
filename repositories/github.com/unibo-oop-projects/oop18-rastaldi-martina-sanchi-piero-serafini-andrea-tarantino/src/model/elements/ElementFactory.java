package model.elements;

import javafx.util.Pair;
import model.players.Player;

/**
 * A factory used to create every element of the board.
 * Andrea Serafini.
 *
 */
public class ElementFactory {

    /**
     *
     * @param position
     *            of the hero
     * @param player
     *            controlling the hero
     * @param id
     *            of the hero
     * @return a new drunk hero
     */
    public DrunkHero getDrunkHero(final Pair<Integer, Integer> position, final Player player, final Integer id) {
        return new DrunkHero(position, player, id);
    }

    /**
     *
     * @param position
     *            of the hero
     * @param player
     *            controlling the hero
     * @param id
     *            of the hero
     * @return a new hero
     */
    public Hero getHero(final Pair<Integer, Integer> position, final Player player, final Integer id) {
        return new Hero(position, player, id);
    }

    /**
     *
     * @param steps
     *            of the minotaurus
     * @return a new minotaurus
     */
    public Minotaurus getMinotaurus(final int steps) {
        return new Minotaurus(steps);
    }

    /**
     *
     * @param position1
     *            of the wall
     * @param position2
     *            of the wall
     * @return a new wall
     */
    public Wall getWall(final Pair<Integer, Integer> position1, final Pair<Integer, Integer> position2) {
        return new Wall(position1, position2);
    }
}
