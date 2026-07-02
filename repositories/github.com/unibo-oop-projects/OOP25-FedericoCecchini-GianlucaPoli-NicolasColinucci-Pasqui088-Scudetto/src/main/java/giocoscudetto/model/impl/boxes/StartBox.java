package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * This class represents the first box.
 */
public class StartBox implements Box {

    private static final String IMAGE = "casella_31.png";
    private static final String DESCRIPTION = "Box Event: Start. Start of the match, throw the dice[0-6]";
    private final int position;

    /**
     * Constructor of the StartBox.
     * 
     * @param position the position of the box on the board.
     */
    public StartBox(final int position) {
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
        //System.out.println("Il gioco è iniziato");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Start box";
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
