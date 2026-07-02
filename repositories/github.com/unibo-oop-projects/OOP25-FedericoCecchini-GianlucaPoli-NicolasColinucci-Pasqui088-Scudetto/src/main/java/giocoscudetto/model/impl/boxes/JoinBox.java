package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;

/**
 * Box that moves the opponent to the current player's position.
 */
public final class JoinBox implements Box {

    private static final String IMAGE = "casella_2.png";
    private static final String DESCRIPTION = 
        "Box Event: Join. If you land on this box, "
        + "the opponent has to reach your box.";
    private final int position;

    /**
     * Creates a JoinBox.
     *
     * @param position box position
     */
    public JoinBox(final int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Join Box";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void event(final Match match) {
        final Club current = match.getCurrentPlayer();
        final Club opponent;

        if (current.equals(match.getClubHome())) {
            opponent = match.getClubAway();
        } else {
            opponent = match.getClubHome();
        }

        //System.out.println(current + " entered Join Box");

        final int currentPosition = current.getPawn().getPosition();
        opponent.getPawn().setPosition(currentPosition);

        //System.out.println(
        //    opponent + " joined current player at position " + currentPosition
        //);
        match.turn();
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
