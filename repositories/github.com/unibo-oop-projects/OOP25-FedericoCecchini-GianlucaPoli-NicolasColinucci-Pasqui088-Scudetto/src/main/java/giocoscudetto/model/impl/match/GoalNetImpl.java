package giocoscudetto.model.impl.match;

import java.util.ArrayList;
import java.util.List;

import giocoscudetto.model.api.match.GoalNet;

/**
 * This class represents the goal net of the game, it has a list of integers that represent the position of the goalkeeper.
 */
public class GoalNetImpl implements GoalNet {

    private final List<Integer> goalKeeperPositions;
    private int lastShootPosition;

    /**
     * Constructor for GoalNetImpl, it initializes the list of goalkeeper positions.
     */
    public GoalNetImpl() {
        this.goalKeeperPositions = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGoalKeeperPosition(final int position) {
        if (goalKeeperPositions.size() < 2) {
            this.goalKeeperPositions.add(position);
            // System.out.println("Posizione del portiere impostata: " + position);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGoal(final int ballPosition) {
        this.lastShootPosition = ballPosition;
        if (goalKeeperPositions.contains(ballPosition)) {
            this.goalKeeperPositions.clear();
            //System.out.println("Parata del portiere in posizione: " + ballPosition);
            return false;
        }
        this.goalKeeperPositions.clear();
        //System.out.println("Goal! Il pallone è passato oltre il portiere." + ballPosition);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLastShootPosition() {
        return this.lastShootPosition;
    }
}
