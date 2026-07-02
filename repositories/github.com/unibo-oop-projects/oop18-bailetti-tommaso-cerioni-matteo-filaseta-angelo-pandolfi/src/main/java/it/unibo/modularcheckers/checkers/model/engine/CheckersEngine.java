package it.unibo.modularcheckers.checkers.model.engine;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Player;
import it.unibo.modularcheckers.model.engine.AbstractEngine;
import it.unibo.modularcheckers.model.engine.GameStatus;
import it.unibo.modularcheckers.util.GameDataDeserializerImpl;

/**
 * The Engine designated to play the game "Checkers".
 */
public class CheckersEngine extends AbstractEngine {

    /**
     * Counts the moves that any Color do without eating something, for tie
     * condition.
     */
    private final Map<Color, Integer> movesCounter;
    /** Max Number of moves before a Tie condition. */
    private static final int MOVES_BEFORE_TIE = 40;

    /**
     * Uses super Constructor; added only a move counter for the Checker game rule.
     * 
     * @param logicBoard the board configuration.
     * @param colorList  The ordered list of colors.
     * @param playerList The ordered list of players.
     */
    public CheckersEngine(final Chessboard logicBoard, final List<Color> colorList, final List<Player> playerList) {
        super(logicBoard, colorList, playerList);
        this.movesCounter = colorList.stream().collect(Collectors.toMap(c -> c, c -> 0));
    }

    /**
     * Same as the other constructor, but the configuration are taken from an XML
     * file.
     * 
     * @param playerList the players of the game.
     */
    public CheckersEngine(final List<Player> playerList) {
        super(new GameDataDeserializerImpl().deserializeCheckersBoard(),
                new GameDataDeserializerImpl().deserializeColor(), playerList);
        // TODO Write this thing.
        this.movesCounter = getPlayers().keySet().stream().collect(Collectors.toMap(c -> c, c -> 0));
    }

    /*
     * A winner is declared if only a color have any piece left on the board.
     * 
     * @see it.unibo.modularcheckers.model.engine.AbstractEngine#winCondition()
     */
    @Override
    protected Set<Player> winCondition() {
        if (getPiecesOnBoard().values().stream().filter(l -> !l.isEmpty()).count() == 1) {
            setStatus(GameStatus.PLAYER_WON);
            final Color winnerColor = getPiecesOnBoard().keySet().stream()
                    .filter(c -> !getPiecesOnBoard().get(c).isEmpty()).findFirst().get();
            getWinners().add(getPlayers().get(winnerColor));
        } else if (this.movesCounter.values().stream().anyMatch(v -> v >= MOVES_BEFORE_TIE)) {
            setStatus(GameStatus.TIE);
            getPlayers().keySet().stream().filter(c -> !getPiecesOnBoard().get(c).isEmpty())
                    .map(c -> getPlayers().get(c)).forEach(p -> getWinners().add(p));
        }
        return getWinners();
    }

    @Override
    /**
     * {@inerithDoc}
     * 
     * @param condition In this case, if the actual color ate something, the counter
     *                  resets.
     */
    protected void checkIfSomeoneDied(final boolean condition) {
        if (condition) {
            this.movesCounter.replace(getActualTurn(), 0);
        } else {
            this.movesCounter.replace(getActualTurn(), this.movesCounter.get(getActualTurn()) + 1);
        }
    }
}
