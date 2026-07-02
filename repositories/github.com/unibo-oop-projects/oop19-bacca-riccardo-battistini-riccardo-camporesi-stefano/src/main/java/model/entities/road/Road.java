package model.entities.road;

import java.util.Map;
import java.util.Set;

import model.entities.lane.Lane;

public interface Road {

    /**
     *
     * create the road.
     */
    void createRoad();

    /**
     *
     * @return the road that was created.
     */
    Map<String, Set<Lane>> getRoads();
}
