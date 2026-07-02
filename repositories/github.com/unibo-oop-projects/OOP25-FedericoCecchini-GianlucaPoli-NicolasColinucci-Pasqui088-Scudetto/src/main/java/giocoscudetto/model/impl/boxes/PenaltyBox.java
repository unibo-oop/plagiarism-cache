package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * This class represents the Penalty Box.
 */
public final class PenaltyBox implements Box {

    private static final String DESCRIPTION = "Box Event: Penalty. If you land on this box" 
                                        + " The opponent must decide the position of the goalkeeper, \n" 
                                        + " once finished you kick the penalty which consists of throwing a dice" 
                                        + " [1-6] if you roll a number not selected by the opponent you score a goal.";
    private static final String IMAGE = "casella_16.png";
    private final int position;
    private final String name;

    /**
     * Constructor of the PenaltyBox class.
     * 
     * @param position the position of the box on the board.
     */
    public PenaltyBox(final int position) {
        this.position = position;
        this.name = "Penalty Box";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void event(final Match match) {
        match.setGameMode(Match.GameMode.PENALTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}
