package clashclass.battle.destruction;

import clashclass.ecs.Component;
import clashclass.ecs.UpdateProvider;

/**
 * Indicates that an object can be observed for destruction events.
 * Each building in the game has its own DestructionObservable instance,
 * which allows DestructionObservers to subscribe and get notified
 * when the building is destroyed.
 */
public interface DestructionObservable extends Component, UpdateProvider {
    /**
     * Add an observer to the set of Observers.
     *
     * @param observer to add
     */
    void addObserver(DestructionObserver observer);

    /**
     * Remove an observer to the set of Observers.
     *
     * @param observer to remove
     */
    void removeObserver(DestructionObserver observer);
}

