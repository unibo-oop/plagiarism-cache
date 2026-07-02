package clashclass.battle.troopdeath;

import clashclass.ecs.Component;
import clashclass.ecs.GameObject;

/**
 * Observer used to track the death of troops.
 */
public interface TroopDeathObserver extends Component {
    /**
     * Notify the death of a troop GameObject.
     *
     * @param troop the troop that has died
     */
    void notifyDeath(GameObject troop);
}
