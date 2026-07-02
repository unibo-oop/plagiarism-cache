package fargoal.model.entity.monsters.api;

import fargoal.commons.api.Position;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;

/**
 * A factory to generate any type of monster that may be present
 * on the map.
 */
public interface MonsterFactory {

    /**
     * Method to generate a random monster, based on the floor where the 
     * player is currently located.
     * 
     * @param position - the starting position
     * @param floorManager - to get infos also about other entities
     * @param renderFactory - to give the monsters a render
     * @return a Monster
     */
    Monster generate(Position position, FloorManager floorManager, RenderFactory renderFactory);

}
