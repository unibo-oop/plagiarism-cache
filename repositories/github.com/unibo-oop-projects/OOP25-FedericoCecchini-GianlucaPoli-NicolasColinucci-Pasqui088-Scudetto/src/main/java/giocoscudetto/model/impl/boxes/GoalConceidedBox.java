package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * Implementing GoalConceidedBox.
 * Opponent score a goal if u land on this box.
 */
public class GoalConceidedBox implements Box {

    private static final String BOX_NAME = "Goal Conceded";
    private static final String IMAGE = "casella_9.png";
    private static final String DESCRIPTION = "Box Event: Goal Conceded. If you land on this box, you concede a goal.";

    private final int position;

    /**
     * @param position it's the position in the board of this box.
     */
    public GoalConceidedBox(final int position) {
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
            match.goalAway();
        } else {
            match.goalHome();
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
