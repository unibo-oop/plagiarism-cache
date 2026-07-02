package clashclass.ai.behaviourtree;

/**
 * Represents a factory the creation of {@link BehaviourTree}.
 */
@FunctionalInterface
public interface BehaviourTreeFactory {
    /**
     * Creates the Behaviour Tree.
     *
     * @return the Behaviour Tree
     */
    BehaviourTree create();
}
