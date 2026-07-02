package model.board;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import model.board.manager.GameManager;
import model.elements.Element;
import model.elements.Hero;
import model.elements.Minotaurus;
import model.elements.Wall;
import model.players.User;
import utilities.Colors;
import utilities.Status;

/**
 * This class represent every aspect of the board, elements and fixed parts.
 * Andrea Serafini.
 *
 */
public final class Board implements BoardInterface, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3444530113081776497L;
    private static Board gameBoard;

    private final Map<Pair<Integer, Integer>, Hero> heroMap;
    private final Map<Pair<Integer, Integer>, Wall> wallMap;
    private final Map<Pair<Integer, Integer>, Pair<Status, Colors>> occupationList;
    private final Minotaurus minotaurus;
    private int arrivedHeroNumber;

    /**
     * Constructor.
     */
    public Board() {
        this.occupationList = Initializator.getLog().getOccupationList();
        this.heroMap = Initializator.getLog().getHeroMap();
        this.wallMap = Initializator.getLog().getWallMap();
        this.minotaurus = Initializator.getLog().getMinotaurus();
    }

    @Override
    public int arrivedHero(final User player) {
        this.arrivedHeroNumber = 0;
        this.heroMap.values().stream().forEach(e -> {
            if (e.getColor().equals(player.getColor()) && e.isArrived()) {
                this.arrivedHeroNumber++;
            }
        });
        return this.arrivedHeroNumber;
    }

    @Override
    public Optional<Pair<Status, Colors>> cellStatus(final Pair<Integer, Integer> pair) {
        return Optional.ofNullable(this.occupationList.get(pair));
    }

    @Override
    public Element getElementAtPosition(final Pair<Integer, Integer> position) {
        if (this.minotaurus.getActualPosition().equals(position)) {
            return this.minotaurus;
        } else if (this.wallMap.containsKey(position)) {
            return this.wallMap.get(position);
        } else if (this.heroMap.containsKey(position)) {
            return this.heroMap.get(position);
        }
        return null;
    }

    @Override
    public Map<Pair<Integer, Integer>, Hero> getHeroMap() {
        return this.heroMap;
    }

    @Override
    public Minotaurus getMinotaurus() {
        return this.minotaurus;
    }

    @Override
    public Map<Pair<Integer, Integer>, Pair<Status, Colors>> getOccupationList() {
        return this.occupationList;
    }

    @Override
    public Element getSelected(final Pair<Integer, Integer> selected) {

        if (selected.equals(this.minotaurus.getActualPosition())) {
            return this.minotaurus;
        } else if (this.heroMap.containsKey(selected)) {
            return this.heroMap.get(selected);
        } else if (this.wallMap.containsKey(selected)) {
            return this.wallMap.get(selected);
        }
        return null;
    }

    @Override
    public Map<Pair<Integer, Integer>, Wall> getWallMap() {
        return this.wallMap;
    }

    @Override
    public void resetArrivedHero() {
        this.heroMap.values().stream().forEach(e -> e.setArrived(false));
    }

    /**
     * Return the Singleton of this class.
     *
     * @return board
     */
    public static synchronized Board getLog() {
        if (gameBoard == null) {
            gameBoard = new Board();
        }
        return gameBoard;
    }

    /**
     * Reset the board.
     */
    public static void resetBoard() {
        gameBoard = null;
    }

    /**
     * Restore board from a previous game.
     */
    public static void restoreBoard() {
        gameBoard = GameManager.getLog().getGame();
    }

}
