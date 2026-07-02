package model.objectives;

import model.map.ObservableGameMap;
import model.player.Player;

/**
 * Models an objective.
 */
public abstract class AbstractObjective implements Objective {

    private final String description;

    /**
     * @param desc the description of this objective
     */
    public AbstractObjective(final String desc) {
        this.description = desc;
    }

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return this.description;
    }

    /** {@inheritDoc} **/
    @Override
    public abstract boolean isCompleted(ObservableGameMap actualGameMap, Player player);

}
