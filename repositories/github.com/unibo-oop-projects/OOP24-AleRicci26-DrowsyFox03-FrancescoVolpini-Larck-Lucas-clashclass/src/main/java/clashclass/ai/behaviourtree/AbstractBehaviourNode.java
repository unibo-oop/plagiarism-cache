package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.Blackboard;


/**
 * Represents an abstract implementation of Node.
 */
public abstract class AbstractBehaviourNode implements BehaviourNode {
    private Blackboard blackboard;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        return State.SUCCESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExit() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restart() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setBlackboard(final Blackboard blackboard) {
        this.blackboard = blackboard;
    }

    /**
     * Gets the {@link BehaviourTree}'s {@link Blackboard} reference.
     *
     * @return the {@link BehaviourTree}'s {@link Blackboard} reference
     */
    protected final Blackboard getBlackboard() {
        return this.blackboard;
    }
}
