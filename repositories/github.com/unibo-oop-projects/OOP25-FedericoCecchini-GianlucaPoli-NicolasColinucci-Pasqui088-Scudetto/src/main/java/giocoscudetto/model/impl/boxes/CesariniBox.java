package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * Implementig Cesarini Box.
 * If you land on this box during a match, you score a goal.
 */
public class CesariniBox implements Box {

    private static final String BOX_NAME = "Cesarini Zone";
    private static final String IMAGE = "casella_30.png";
    private static final String DESCRIPTION = "Box Event: If you land on this box, you score a goal.";

    private final int position;

    /**
     * @param position it's the position in the board of this box.
     */
    public CesariniBox(final int position) {
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
        if (match.getCurrentPlayer().equals(match.getClubHome())) {
            match.goalHome();
        } else {
            match.goalAway();
        }
        match.turn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return BOX_NAME;
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
