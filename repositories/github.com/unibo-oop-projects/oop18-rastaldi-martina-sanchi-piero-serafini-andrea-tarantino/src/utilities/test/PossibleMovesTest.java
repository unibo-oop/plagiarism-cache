
package utilities.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import javafx.util.Pair;
import model.board.Board;
import model.elements.Hero;
import model.elements.Minotaurus;
import model.elements.Wall;
import model.players.Player;
import utilities.Colors;
import utilities.Status;

/**
 * This class can be used to test the implementation of the possible moves
 * method. Andrea Serafini.
 *
 */
public class PossibleMovesTest {

    private static final int LIMIT = 2;

    private final Hero centralHero = new Hero(new Pair<>(1, 1), new Player("Prova", Colors.Blue), 0);
    private final Hero cornerHero = new Hero(new Pair<>(0, 0), new Player("Prova", Colors.Blue), 0);
    private final Hero lateralHero = new Hero(new Pair<>(0, 1), new Player("Prova", Colors.Blue), 0);

    private final Map<Pair<Integer, Integer>, Pair<Status, Colors>> occupationList = new HashMap<>();
    private final Map<Pair<Integer, Integer>, Hero> heroMap = new HashMap<>();
    private final Map<Pair<Integer, Integer>, Wall> wallMap = new HashMap<>();
    private final Minotaurus minotaurus = new Minotaurus(6);;

    /*
     * elements for tests
     * 
     * private final Pair<Status, Colors> siepe = new Pair<>(Status.SIEPE,
     * Colors.Green); private final Pair<Status, Colors> arrivoBlu = new
     * Pair<>(Status.ARRIVO, Colors.Blue); private final Pair<Status, Colors>
     * arrivoRosso = new Pair<>(Status.ARRIVO, Colors.Red); private final Element
     * enemyHero = new Hero(new Pair<>(1, 0), new Player("Prova-enemy", Colors.Red),
     * 0);
     */

    /**
     *
     * @param pair
     *            the coordinates
     * @return the optional cell status
     */
    public Optional<Pair<Status, Colors>> cellStatus(final Pair<Integer, Integer> pair) {
        return Optional.ofNullable(this.occupationList.get(pair));
    }

    /**
     *
     * @param hero
     *            the hero to move
     * @return the list of possible moves in jumping mode
     */
    protected List<Pair<Integer, Integer>> getJumpingMoves(final Hero hero) {
        final List<Pair<Integer, Integer>> moves = new ArrayList<>();
        final List<Pair<Integer, Integer>> provMoves = new ArrayList<>();
        final Integer startx = hero.getActualPosition().getKey();
        final Integer starty = hero.getActualPosition().getValue();
        provMoves.add(new Pair<>((startx - 1) < 0 ? 0 : startx - 1, starty));
        provMoves
                .add(new Pair<>(startx, (starty + 1) > PossibleMovesTest.LIMIT ? PossibleMovesTest.LIMIT : starty + 1));
        provMoves.add(new Pair<>(startx, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves
                .add(new Pair<>((startx + 1) > PossibleMovesTest.LIMIT ? PossibleMovesTest.LIMIT : startx + 1, starty));

        for (final Pair<Integer, Integer> n : provMoves) {
            // System.out.println(cellStatus(n));
            if (!moves.contains(n) && !Board.getLog().getWallMap().containsKey(n) && !this.heroMap.containsKey(n)
                    && !this.minotaurus.getActualPosition().equals(n)
                    && (!this.cellStatus(n).isPresent() || (this.occupationList.get(n).getValue() == hero.getColor())
                            || (this.occupationList.get(n).getKey() == Status.SIEPE))) {
                moves.add(n);
            }
        }
        return moves;
    }

    /**
     *
     * @param hero
     *            the hero to move
     * @return the list of possible moves
     */
    protected List<Pair<Integer, Integer>> getMoves(final Hero hero) {
        final List<Pair<Integer, Integer>> moves = new ArrayList<>();
        final List<Pair<Integer, Integer>> provMoves = new ArrayList<>();
        final Integer startx = hero.getActualPosition().getKey();
        final Integer starty = hero.getActualPosition().getValue();
        provMoves.add(new Pair<>((startx - 1) < 0 ? 0 : startx - 1, starty));
        provMoves
                .add(new Pair<>(startx, (starty + 1) > PossibleMovesTest.LIMIT ? PossibleMovesTest.LIMIT : starty + 1));
        provMoves.add(new Pair<>(startx, (starty - 1) < 0 ? 0 : starty - 1));
        provMoves
                .add(new Pair<>((startx + 1) > PossibleMovesTest.LIMIT ? PossibleMovesTest.LIMIT : startx + 1, starty));

        for (final Pair<Integer, Integer> n : provMoves) {
            // System.out.println(cellStatus(n));
            if (!hero.getActualPosition().equals(n) && !moves.contains(n) && !this.wallMap.containsKey(n)
                    && !this.heroMap.containsKey(n) && !this.minotaurus.getActualPosition().equals(n)
                    && (!this.cellStatus(n).isPresent()
                            || (this.occupationList.get(n).getValue() == hero.getColor()))) {
                moves.add(n);
            }
        }
        return moves;
    }

    /**
     *
     */
    @Test
    public void testCentral() {
        assertTrue("ok", this.getMoves(this.centralHero).size() == 4); // NOPMD
        assertTrue("ok", this.getMoves(this.centralHero)
                .containsAll(Arrays.asList(new Pair<>(0, 1), new Pair<>(1, 2), new Pair<>(1, 0), new Pair<>(2, 1))));
    }

    /**
     *
     */
    @Test
    public void testCorner() {
        assertTrue("ok", this.getMoves(this.cornerHero).size() == 2); // NOPMD
        assertTrue("ok", this.getMoves(this.cornerHero).containsAll(Arrays.asList(new Pair<>(0, 1), new Pair<>(1, 0))));

    }

    /**
     *
     */
    @Test
    public void testLateral() {
        System.out.println(this.getMoves(this.lateralHero));
        assertTrue("ok", this.getMoves(this.lateralHero).size() == 3); // NOPMD
        assertTrue("ok", this.getMoves(this.lateralHero)
                .containsAll(Arrays.asList(new Pair<>(0, 2), new Pair<>(0, 0), new Pair<>(1, 1))));
    }

}
