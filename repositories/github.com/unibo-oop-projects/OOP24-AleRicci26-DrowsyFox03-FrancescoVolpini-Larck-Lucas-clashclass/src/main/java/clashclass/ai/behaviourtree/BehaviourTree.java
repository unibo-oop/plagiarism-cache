package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.Blackboard;
import clashclass.ecs.Component;
import clashclass.ecs.UpdateProvider;

/**
 * Represents a Behaviour Tree, a data structure for smart in-game AI management.
 */
public interface BehaviourTree extends Component, UpdateProvider {
    /**
     * Gets the blackboard.
     *
     * @return the blackboard
     */
    Blackboard getBlackboard();

    /**
     * Starts the Behaviour Tree's loop.
     */
    void start();

    /**
     * Restarts the Behaviour Tree.
     */
    void restart();

    /**
     * Stops the Behaviour Tree.
     */
    void stop();
}
