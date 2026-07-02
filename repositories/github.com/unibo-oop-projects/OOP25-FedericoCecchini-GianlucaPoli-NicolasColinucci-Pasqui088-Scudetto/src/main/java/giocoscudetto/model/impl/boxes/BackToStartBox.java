package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * Box that sends the player back to the starting position.
 */
public final class BackToStartBox implements Box {

    private static final String DESCRIPTION = 
        "Box Event: Back to Start. "
        + "If you land on this box, you must return to the starting point of the board.";
    private static final String IMAGE = "casella_34.png";
    private final int position;

    /**
     * Creates a BackToStartBox.
     * 
     * @param position box position
     */
    public BackToStartBox(final int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return "Back to Start";
    }

    @Override
    public void event(final Match match) {
        match.getCurrentPlayer().getPawn().setPosition(0);
        //System.out.println(match.getCurrentPlayer() + " go back to start");
        match.turn();
        //System.out.println("turno di " + match.getCurrentPlayer());
    }

    @Override
    public String getImage() {
        return IMAGE;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
