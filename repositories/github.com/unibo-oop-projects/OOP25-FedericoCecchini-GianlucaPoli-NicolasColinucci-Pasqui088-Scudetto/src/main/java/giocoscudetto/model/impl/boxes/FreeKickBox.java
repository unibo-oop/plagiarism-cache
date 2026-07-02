package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * This class represents the Free Kick Box.
 */
public class FreeKickBox implements Box {

    private static final String DESCRIPTION = "Box Event: Free Kick. If you land on this box,"
                                       + "you throw two dice(0-6) and if the sum is exactly 7 you score a goal";
    private static final String IMAGE = "casella_26.png";
    private final int position;

    /**
     * Constructor of the FreeKickBox class.
     * 
     * @param position the position of the box on the board.
     */
    public FreeKickBox(final int position) {
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
        match.setGameMode(Match.GameMode.FREE_KICK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Freekick Box";
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
