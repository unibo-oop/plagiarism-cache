package it.unibo.modularcheckers.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import it.unibo.modularcheckers.checkers.model.engine.CheckersEngine;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.GameType;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.model.Player;
import it.unibo.modularcheckers.model.engine.Engine;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;
import it.unibo.modularcheckers.view.GameTableView;

/**
 * Basic implementation of GameLoop. This class is the implementation of the
 * controller part in the MVC pattern.
 */
public class GameLoopImpl implements GameLoop {

    private final Engine engine;

    private Optional<Coordinate> pieceSelected;
    private boolean moveStillInProgress;

    /**
     * Create the GameLoop and choose the engine to use.
     *
     * @param players the Player list, used to create the engine.
     * @param game    the game to be played.
     */
    public GameLoopImpl(final List<Player> players, final GameType game) {
        switch (game) {
        case CHECKERS:
            this.engine = new CheckersEngine(players);
            break;
        case CHESS:
            throw new IllegalArgumentException("Function not yet implemented.");
        default:
            throw new IllegalArgumentException("Unknown Game.");
        }
        this.pieceSelected = Optional.empty();
        new GameTableView();
        setmoveStillInProgress(false);
    }

    /**
     * @return the Engine of the GameLoop.
     */
    private Engine getEngine() {
        return engine;
    }

    /**
     * @return true if the move is still in progress, false otherwise.
     */
    private boolean isMoveStillinProgress() {
        return moveStillInProgress;
    }

    /**
     * Update the Status of moveStillInProgress.
     *
     * @param moveStillInProgress
     */
    private void setmoveStillInProgress(final boolean moveStillInProgress) {
        this.moveStillInProgress = moveStillInProgress;
    }

    /**
     * Start the gameLoop.
     */
    @Override
    public void startLoop() {
        getEngine().start();
        // TODO View part.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void surrender() {
        getEngine().surrender();
        // TODO View part.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectPiece(final Optional<Coordinate> selectedPiece) {
        if (isMoveStillinProgress()) {
            throw new IllegalStateException("You can't select a new piece.");
        }
        this.pieceSelected = selectedPiece;
        // TODO View part.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeStepChosen(final Coordinate whereToMove) {
        // TODO View Part.
        // Check if the piece was previously selected.
        if (this.pieceSelected.isEmpty()) {
            throw new IllegalStateException("No piece selected!");
        }
        setmoveStillInProgress(true);
        Tree<Step> selectedPieceStepTree = this.getEngine().getStepTreeFromCoord(pieceSelected.get());
        final Optional<Step> stepFound = selectedPieceStepTree.getFirstChildren().stream()
                .filter(s -> s.getCoordinate().equals(whereToMove)).findFirst();
        if (stepFound.isEmpty()) {
            throw new NoSuchElementException("No steps available for the coordinate: " + whereToMove.toString());
        }
        // Execute the step StepFound.
        getEngine().executeStep(new Pair<>(selectedPieceStepTree.getRoot(), stepFound.get()));
        selectedPieceStepTree = selectedPieceStepTree.getChildren().stream()
                .filter(t -> t.getRoot().getCoordinate().equals(whereToMove)).findFirst().get();
        // change turn if the same piece cannot move again.
        if (selectedPieceStepTree.getChildren().isEmpty()) {
            setmoveStillInProgress(false);
            getEngine().moveFinished();
        }
        // TODO UPDATE VIEW
    }

}
