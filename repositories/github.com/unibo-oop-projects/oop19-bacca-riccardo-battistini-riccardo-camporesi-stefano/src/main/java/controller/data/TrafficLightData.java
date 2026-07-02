package controller.data;

import java.util.LinkedList;

import org.apache.commons.lang3.tuple.Pair;

import constraints.TrafficLightConstraints;
import model.entities.trafficlight.TrafficLight.Phases;

/**
 * This class holds all the parameters needed to create a system composed of
 * traffic lights.
 */
public class TrafficLightData {

    private final LinkedList<Pair<Phases, Integer>> list = new LinkedList<>();
    private int standardPhaseDuration;

    public TrafficLightData(final int standardPhaseDuration) {
        this.standardPhaseDuration = standardPhaseDuration;
    }

    /**
     * Sets the list of phases that traffic lights should follow. It does so by
     * reading the current standard phase duration. Each time this method is called
     * the list of phases is refreshed.
     */
    public void setPhasesList() {
        list.clear();
        list.add(Pair.of(Phases.GREEN, standardPhaseDuration));
        list.add(Pair.of(Phases.YELLOW, TrafficLightConstraints.YELLOW_DEFAULT_DURATION));
        list.add(Pair.of(Phases.RED, standardPhaseDuration));
        list.add(Pair.of(Phases.YELLOW, TrafficLightConstraints.YELLOW_DEFAULT_DURATION));
    }

    /**
     * Returns the list of phases. This method should be called after the list of
     * phases has been set.
     *
     * @return The list of phases to be used to synchronise traffic lights.
     * @throws IllegalStateException if the list is empty.
     */
    public LinkedList<Pair<Phases, Integer>> getPhasesList() {
        if (list.isEmpty()) {
            throw new IllegalStateException("A list of phases should be set first!");
        }
        return list;
    }

    /**
     * Query used to get the current duration of green and red phases.
     *
     * @return The current duration of the red and green traffic lights phases.
     */
    public int getStandardPhaseDuration() {
        return standardPhaseDuration;
    }

    /**
     * Changes the duration of the green and red phases based on the new value
     * given.
     *
     * @param standardPhaseDuration The new duration of green and red phases.
     */
    public void changePhaseDuration(final int standardPhaseDuration) {
        this.standardPhaseDuration = standardPhaseDuration;
    }

}
