package clashclass.ai.pathfinding;

import clashclass.ecs.GameObject;
import clashclass.village.Village;

/**
 * Represents an object which can build the grid data for troops' pathfinding.
 */
public interface AiNodesBuilder {
    /**
     * Builds the path node grid.
     *
     * @param village the battle village
     *
     * @return the path node grid
     */
    PathNodeGrid buildPathNodeList(Village village);

    /**
     * Builds the path node grid.
     *
     * @param destroyedBuilding the building that has been destroyed
     *
     * @return the path node grid
     */
    PathNodeGrid buildPathNodeList(GameObject destroyedBuilding);
}
