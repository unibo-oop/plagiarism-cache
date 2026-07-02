package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * This class represents the Corner Box.
 */
public class CornerBox implements Box {

    private static final String IMAGE = "casella_19.png";
    private static final String DESCRIPTION = "Box Event: Corner. If you land on this box,"
                                    + " you throw two dice and if you get a 1 you score a goal";
    private final int position;

    /**
     * Constructor of the CornerBox class.
     * 
     * @param position the position of the box on the board.
     */
    public CornerBox(final int position) {
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
        match.setGameMode(Match.GameMode.CORNER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Corner Box";
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
