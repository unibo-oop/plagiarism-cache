package it.unibo.modularcheckers.model;

import java.util.Optional;
import java.util.Set;

import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;

/**
 * Random player. The move to execute is chosen randomly.
 */
public class AIPlayer extends AbstractPlayer {

    /**
     * Superclass constructor.
     * 
     * @param playerName the player name.
     */
    public AIPlayer(final String playerName) {
        super(playerName);
    }

    /**
     * The piece is chosen randomly. {@inheritDoc}
     *
     */
    @Override
    public Optional<Coordinate> selectedPiece(final Set<Coordinate> movablePieces) {
        final Optional<Coordinate> chosenPiece = movablePieces.stream().findAny();
        if (chosenPiece.isEmpty()) {
            throw new IllegalStateException("No pieces can move.");
        }
        return chosenPiece;

    }

    /**
     * Return a random decision. There will always be a child. When the move is
     * created the check is on the the children of the Tree returned. No more checks
     * are required here.
     */
    @Override
    public Pair<Step, Step> chooseStep(final Tree<Step> allSteps) {
        return new Pair<>(allSteps.getRoot(), allSteps.getChildren().stream().findAny().get().getRoot());
    }

}
