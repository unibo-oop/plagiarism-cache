package clashclass.battle.destruction;

import clashclass.ecs.Component;
import clashclass.ecs.GameObject;

/**
 * Observer used to track the destruction of the enemy village.
 */
public interface DestructionObserver extends Component {
    /**
     * Notify the destruction of a GameObject.
     *
     * @param obj the object that has been destroyed
     */
    void notifyDestruction(GameObject obj);
}
