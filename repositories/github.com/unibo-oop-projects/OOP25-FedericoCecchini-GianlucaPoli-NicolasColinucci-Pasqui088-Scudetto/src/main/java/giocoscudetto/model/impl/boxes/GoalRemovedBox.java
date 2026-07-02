package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * Implementing GoalRemovedBox.
 * One of your goal get removed if you land on this box.
 */
public class GoalRemovedBox implements Box {

    private static final String BOX_NAME = "Goal Removed";

    private static final String IMAGE = "casella_33.png";
    private static final String DESCRIPTION = "If you land on this box, a goal is removed from your score.";
    private final int position;

    /**
     * @param position is the position of this box.
     */
    public GoalRemovedBox(final int position) {
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
            match.removeGoalHome();
        } else {
            match.removeGoalAway();
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
