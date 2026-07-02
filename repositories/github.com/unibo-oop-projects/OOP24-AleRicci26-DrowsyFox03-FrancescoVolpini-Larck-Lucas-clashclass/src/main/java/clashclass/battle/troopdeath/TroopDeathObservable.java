package clashclass.battle.troopdeath;

import clashclass.ecs.Component;
import clashclass.ecs.UpdateProvider;

/**
 * Indicates that an object can be observed for troop death events.
 * Each troop in the game has its own TroopDeathObservable instance,
 * which allows TroopDeathObservers to subscribe and get notified
 * when the troop is destroyed.
 */
public interface TroopDeathObservable extends Component, UpdateProvider {
    /**
     * Add an observer to the set of Observers.
     *
     * @param observer to add
     */
    void addObserver(TroopDeathObserver observer);

    /**
     * Remove an observer from the set of Observers.
     *
     * @param observer to remove
     */
    void removeObserver(TroopDeathObserver observer);
}
