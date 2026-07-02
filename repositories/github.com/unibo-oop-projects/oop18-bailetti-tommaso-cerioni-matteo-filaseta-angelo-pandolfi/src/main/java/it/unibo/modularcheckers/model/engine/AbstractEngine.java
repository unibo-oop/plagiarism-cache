package it.unibo.modularcheckers.model.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Optional;
import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.History;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.model.Player;
import it.unibo.modularcheckers.model.TurnIterator;
import it.unibo.modularcheckers.model.TurnIteratorSequential;
import it.unibo.modularcheckers.model.move.MoveFactory;
import it.unibo.modularcheckers.model.move.MoveFactoryImpl;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;
import it.unibo.modularcheckers.model.piece.Piece;

/**
 * The abstract Engine can be sub-classed to create Engines with different
 * rule-set.
 *
 */
public abstract class AbstractEngine implements Engine {
    /** The board situation. */
    private final Chessboard logicBoard;
    /** The history of the game. */
    private final History history;
    /** Every color is associated to a Player. */
    private final Map<Color, Player> players;
    /** Player that are alive during the game. */
    private final Set<Player> alivePlayers;
    /** Pieces that are NOT on board for each player. */
    private final Map<Color, List<Piece>> piecesRemoved;
    /** The turns sequence. */
    private final TurnIterator turnSequence;
    /** Move Factory to insert moves in History. */
    private final MoveFactory moveFactory;
    /**
     * Contains the Player that won, the players that tied, or nothing is game is
     * still in progress.
     */
    private final Set<Player> winners;
    /** Status of the Game. */
    private GameStatus status;
    /**
     * Contains all the trees of steps that every piece of the color in turn can do.
     */
    private final Map<Coordinate, Tree<Step>> allStepTrees;

    /**
     * Sole constructor. (For invocation by subclass constructors, typically
     * implicit.)
     *
     * @param logicBoard the board configuration.
     * @param colorList  The ordered list of colors.
     * @param playerList The ordered list of players.
     */
    public AbstractEngine(final Chessboard logicBoard, final List<Color> colorList, final List<Player> playerList) {
        if (colorList.size() != playerList.size() || colorList.size() <= 1) {
            throw new IllegalArgumentException(" Wrong playerList or/and colorList size.");
        }
        this.logicBoard = logicBoard;
        this.history = new History();
        this.players = IntStream.range(0, colorList.size()).boxed()
                .collect(Collectors.toMap(i -> colorList.get(i), i -> playerList.get(i)));
        this.alivePlayers = playerList.stream().collect(Collectors.toSet());
        this.piecesRemoved = colorList.stream().collect(Collectors.toMap(c -> c, c -> new ArrayList<>()));
        this.turnSequence = new TurnIteratorSequential(colorList);
        this.moveFactory = new MoveFactoryImpl();
        this.winners = new HashSet<>();
        this.setStatus(GameStatus.NOT_IN_PROGRESS);
        this.allStepTrees = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Player getActualPlayer() {
        return getPlayers().get(getActualTurn());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<Coordinate, Tree<Step>> calculateAllSteps() {
        final Map<Coordinate, Tree<Step>> completeSteps = getLogicBoard().getBlocks().keySet().stream()
                .filter(c -> getLogicBoard().getBlocks().get(c).getPiece().isPresent())
                .filter(c -> getLogicBoard().getBlocks().get(c).getPiece().get().getColor().equals(getActualTurn()))
                .collect(Collectors.toMap(c -> c, c -> calculateTreeFromCoordinate(c)));
        this.allStepTrees.clear();
        this.allStepTrees.putAll(completeSteps);
        return Collections.unmodifiableMap(this.allStepTrees);
    }

    /**
     * {@inheritDoc}
     */
    public Tree<Step> getStepTreeFromCoord(final Coordinate coordSelected) {
        if (this.allStepTrees.containsKey(coordSelected)) {
            return this.allStepTrees.get(coordSelected);
        }
        throw new IllegalArgumentException("Piece not found in the specified coordinate.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void executeStep(final Pair<Step, Step> steps) {
        // Kill the pieces.
        killPiece(steps.getX());
        checkIfSomeoneDied(killPiece(steps.getY()));
        // Move the piece.
        final Piece piecetoMove = getLogicBoard().getBlock(steps.getX().getCoordinate()).removePiece().get();
        getLogicBoard().getBlock(steps.getY().getCoordinate()).setPiece(piecetoMove);
        // Add the two steps to the move factory.
        getMoveFactory().addValue(steps.getX());
        getMoveFactory().addValue(steps.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveFinished() {
        getHistory().setMove(getMoveFactory().returnMove());
        getMoveFactory().reset();
        nextTurn();
        if (winCondition().isEmpty()) {
            calculateAllSteps();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start() {
        this.setStatus(GameStatus.IN_PROGRESS);
        calculateAllSteps();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void surrender() {
        this.killPlayer(this.getActualTurn());
    }

    /**
     * PROTECTED METHODS.
     */

    /**
     * Called at the end of every Step made. Does additional instruction if a piece
     * died during the step.
     *
     * @param someoneDied true if a piece died during the step, false otherwise.
     */
    protected abstract void checkIfSomeoneDied(boolean someoneDied);

    /**
     * Check if the game ended, turn by turn, using the strategy pattern.
     *
     * @return An Empty Set if there's no winner yet, a set with only one Player if
     *         someone won, or a Set with more Players if there was a Tie.
     */
    protected abstract Set<Player> winCondition();

    /**
     * PRIVATE METHODS.
     */

    /**
     * This method is called when a color ends its turn.
     *
     * @return the next color in the turn iteration.
     */
    private Color nextTurn() {
        return this.getTurnSequence().next();
    }

    /**
     *
     * @param coord the coordinate of the piece on the Chessboard.
     * @return A tree of Steps, containing all the steps that the piece can do.
     */
    private Tree<Step> calculateTreeFromCoordinate(final Coordinate coord) {
        Optional<Piece> pieceSelected = this.getLogicBoard().getBlock(coord).getPiece();
        if (pieceSelected.isPresent()) {
            throw new IllegalArgumentException("Can't calculate StepTree for this coordinate:" + coord.toString());
        }
        return null;// TODO
    }

    /**
     * Kill the piece in the chosen coordinate. Add the piece to removedPieces
     * Map.This method is often used in combination with {@link #killPiece(Step)}.
     *
     * @param coord The coordinate where the piece to kill is.
     * @return true, because the piece will always be killed. The passed coordinate
     *         is always correct.
     */
    private boolean killPiece(final Coordinate coord) {
        final Piece killedPiece = getLogicBoard().getBlocks().get(coord).getPiece().get();
        final Color deadPieceColor = killedPiece.getColor();
        // Add the piece to piecesRemoved.
        this.getPiecesRemoved().get(deadPieceColor).add(killedPiece);
        // Remove the piece from the board.
        getLogicBoard().getBlocks().get(coord).removePiece();
        // Check if the player is still alive.
        isPlayerStillAlive(deadPieceColor);
        return true;
    }

    /**
     * Kill the pieces contained in the step passed. This method is always used in
     * combination with {@link #killPiece(Coordinate)}.
     *
     * @param step The step containing the piece to kill
     */
    private boolean killPiece(final Step step) {
        if (step.getDeadPiece().isPresent()) {
            return killPiece(step.getDeadPiece().get().getX());
        }
        return false;
    }

    /**
     * Check if a player is still alive. If not, remove it from AlivePlayer.
     *
     * @param color
     */
    private boolean isPlayerStillAlive(final Color color) {
        if (getPiecesOnBoard().get(color).isEmpty()) {
            getAlivePlayers().remove(getPlayers().get(color));
            return false;
        }
        return true;
    }

    /**
     * Delete a player and his pieces from the game.
     *
     * @param deafColor the color of the player to kill.
     * @return the player killed.
     */
    private Player killPlayer(final Color deafColor) {
        if (getAlivePlayers().remove(getPlayers().get(deafColor))) {
            getLogicBoard().getBlocks().keySet().stream()
                    .filter(k -> getLogicBoard().getBlock(k).getPiece().isPresent())
                    .filter(k -> getLogicBoard().getBlock(k).getPiece().get().getColor().equals(deafColor))
                    .forEach(k -> {
                        // Add all the pieces to piecesRemoved.
                        getPiecesRemoved().get(deafColor).add(getLogicBoard().getBlock(k).getPiece().get());
                        // Remove the pieces from the board.
                        getLogicBoard().getBlock(k).removePiece();
                    });
            return getPlayers().get(deafColor);
        }
        throw new IllegalArgumentException(" The player is not present or is alredy dead!");
    }

    /*
     * /GETTERS AND SETTERS METHOD
     */

    /**
     * @return the logicBoard
     */
    protected Chessboard getLogicBoard() {
        return logicBoard;
    }

    /**
     * @return the history
     */
    protected History getHistory() {
        return history;
    }

    /**
     * @return the players
     */
    protected Map<Color, Player> getPlayers() {
        return players;
    }

    /**
     * @return the alivePlayers
     */
    protected Set<Player> getAlivePlayers() {
        return alivePlayers;
    }

    /**
     * @return the piecesOnBoard
     */
    protected Map<Color, List<Piece>> getPiecesOnBoard() {
        Map<Color, List<Piece>> piecesOnBoard;
        piecesOnBoard = getPlayers().keySet().stream().collect(Collectors.toMap(c -> c, c -> Collections.emptyList()));
        logicBoard.getBlocks().values().stream().filter(b -> b.getPiece().isPresent()).map(b -> b.getPiece().get())
                .forEach(p -> piecesOnBoard.get(p.getColor()).add(p));
        return piecesOnBoard;
    }

    /**
     * @return the piecesRemoved
     */
    protected Map<Color, List<Piece>> getPiecesRemoved() {
        return piecesRemoved;
    }

    /**
     * @return the turnSequence
     */
    protected TurnIterator getTurnSequence() {
        return turnSequence;
    }

    /**
     * This method is called to know who's turn is it.
     *
     * @return The Color that has to do the move in the turn in which this method is
     *         called.
     */
    public final Color getActualTurn() {
        return this.turnSequence.getActualTurn();
    }

    /**
     * @return the status.
     */
    protected final GameStatus getStatus() {
        return status;
    }

    /**
     * @param status the updated status.
     */
    protected final void setStatus(final GameStatus status) {
        this.status = status;
    }

    /**
     * @return the move factory.
     */
    protected MoveFactory getMoveFactory() {
        return moveFactory;
    }

    /**
     * @return the winners
     */
    protected Set<Player> getWinners() {
        return winners;
    }

}
