package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * This class represents the Result Box.
 */
public class ResultBox implements Box {

    private static final String DESCRIPTION = "Box Event: Result. If you land on this box"
                                     + ", you have to throw 2 dice and the numbers you get makes the new score";
    private static final String IMAGE = "casella_3.png";
    private final int position;

    /**
     * Constructor of the ResultBox class.
     * 
     * @param position the position of the box on the board.
     */
    public ResultBox(final int position) {
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
        match.setGameMode(Match.GameMode.RESULT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "result box";
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
