package it.unibo.modularcheckers.model;

import java.util.Optional;
import java.util.Set;

import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;

/**
 * Implementation of Player. He needs to interact with the view to choose the
 * move.
 */
public class HumanPlayer extends AbstractPlayer {

    /**
     * Superclass constructor.
     * 
     * @param playerName the player name.
     */
    public HumanPlayer(final String playerName) {
        super(playerName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Coordinate> selectedPiece(final Set<Coordinate> movablePieces) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Return the step chosen by the player that interact with the view.
     */
    @Override
    public final Pair<Step, Step> chooseStep(final Tree<Step> allSteps) {
        // TODO
        return null;
    }

}
