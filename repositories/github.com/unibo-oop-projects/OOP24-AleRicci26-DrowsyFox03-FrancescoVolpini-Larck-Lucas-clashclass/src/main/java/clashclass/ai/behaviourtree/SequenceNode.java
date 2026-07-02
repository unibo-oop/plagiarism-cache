package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.Blackboard;

import java.util.List;

/**
 * Represents a sequence node, which executes all the nodes until the first failure.
 */
public class SequenceNode extends AbstractCompositeNode {
    /**
     * Constructs the sequence node.
     *
     * @param children the list of child nodes
     */
    public SequenceNode(final List<AbstractBehaviourNode> children) {
        super(children);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlackboard(final Blackboard blackboard) {
        super.setBlackboard(blackboard);
        this.getChildren().forEach(x -> x.setBlackboard(blackboard));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        super.onEnter();
        this.getChildren().forEach(AbstractBehaviourNode::onEnter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        if (this.getCurrentChildIndex() >= this.getChildren().size()) {
            return State.SUCCESS;
        }

        final var childState = this.getChildren().get(this.getCurrentChildIndex())
                .onUpdate(deltaTime);

        if (childState == State.FAILURE) {
            return State.FAILURE;
        }

        if (childState == State.SUCCESS) {
            this.getChildren().get(this.getCurrentChildIndex()).onExit();
            this.incrementCurrentChildIndex();
            if (this.getCurrentChildIndex() >= this.getChildren().size()) {
                return State.SUCCESS;
            }
            this.getChildren().get(this.getCurrentChildIndex()).onEnter();
        }
        return State.RUNNING;
    }
}
