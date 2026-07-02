package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * This class represents the Finish Box.
 */
public class FinishBox implements Box {

    private static final String IMAGE = "casella_32.png";
    private static final String DESCRIPTION = "Box event: Last Box. If you land on this box the game will end";
    private final int position;

    /**
     * Constructor of the FinishBox class.
     * 
     * @param position the position of the box on the board.
     */
    public FinishBox(final int position) {
        this.position = position;
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
        match.getClubHome().getPawn().setPosition(32);
        match.getClubAway().getPawn().setPosition(32);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Finish Box";
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
