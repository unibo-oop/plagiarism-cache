package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.Blackboard;

/**
 * Represents a single Node in a BehaviourTree.
 */
public interface BehaviourNode {
    /**
     * Initializes the node properties before processing it.
     */
    void onEnter();

    /**
     * Process the node every frame.
     *
     * @param deltaTime the time elapsed between the previous and the current frame
     *
     * @return the current state of the node after a single frame of processing
     */
    State onUpdate(float deltaTime);

    /**
     * Clears the node properties after processing it.
     */
    void onExit();

    /**
     * Restarts the behaviour tree.
     */
    void restart();

    /**
     * Sets the {@link BehaviourTree}'s {@link Blackboard} reference.
     *
     * @param blackboard the blackboard reference
     */
    void setBlackboard(Blackboard blackboard);

    /**
     * Represents the state of a node.
     */
    enum State {
        /**
         * Running State.
         */
        RUNNING,

        /**
         * Success State.
         */
        SUCCESS,

        /**
         * Failure State.
         */
        FAILURE
    }
}
