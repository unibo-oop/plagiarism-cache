package model.rules;

import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import model.entities.trafficlight.TrafficLight.Phases;
import model.observer.Observer;

public abstract class AbstractDriverBehaviour implements DriverBehaviour, Observer {

    /**
     * @param distFromTrafficLight the map returned from driver sight
     * @return false if there is one value of the specified map with empty optional.
     */
    public final boolean isPresent(final Map<Phases, Optional<Integer>> distFromTrafficLight) {
        for (final Entry<Phases, Optional<Integer>> entry : distFromTrafficLight.entrySet()) {
            if (entry.getValue().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param distFromTrafficLight the map returned from driver sight
     * @return the only value of the specified map.
     */
    public final Optional<Integer> getTrafficLightDistance(final Map<Phases, Optional<Integer>> distFromTrafficLight) {
        return distFromTrafficLight.entrySet().stream().findFirst().get().getValue();
    }

    /**
     *
     * @param distFromTrafficLight the map returned from driver sight
     * @return the only key associated with the only value on this map.
     */
    public final Phases getTrafficLightStatus(final Map<Phases, Optional<Integer>> distFromTrafficLight) {
        return distFromTrafficLight.entrySet().stream().findFirst().get().getKey();
    }

}
