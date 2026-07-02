package clashclass.ai.behaviourtree;

/**
 * Represents a node that can wait a certain amount of time.
 */
public class WaitNode extends AbstractBehaviourNode {
    private final float duration;
    private float currentTime;

    /**
     * Constructs the Wait Node.
     *
     * @param duration the duration of the wait node
     */
    public WaitNode(final float duration) {
        this.duration = duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.currentTime = 0.0f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        this.currentTime += deltaTime;
        return this.currentTime >= duration ? State.SUCCESS : State.RUNNING;
    }
}
