package pvz.model.entity.plant;

import pvz.model.WorldConstants;
import pvz.model.entity.LivingEntity;
import pvz.utility.Pair;

/**
 * Defines the generic Plant.<br>
 * Specific behavior needs to be implemented in the subclasses.
 */
public abstract class Plant extends LivingEntity {

    protected PlantType type;

    /**
     * Instantiates a plant at the given position.
     * 
     * @param position
     *            plant position
     */
    public Plant(Pair<Double, Double> position) {
        super(position, WorldConstants.CELL_WIDTH, WorldConstants.CELL_WIDTH);
    }

}
