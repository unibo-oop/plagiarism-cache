package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.PathNodeListWrapper;
import clashclass.ai.pathfinding.PathNode;
import clashclass.commons.ConversionUtility;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Represents a node used to move towards the current target.
 */
public class GoToTargetNode extends AbstractBehaviourNode {
    private static final float SPEED = 15.0f;
    private final float distanceToTargetTolerance;
    private BlackboardProperty<GameObject> actorProp;

    private final Queue<PathNode> remainingPathNodes;
    private Optional<PathNode> currentTarget;

    /**
     * Constructs the node.
     *
     * @param distanceToTargetTolerance the tolerance for reaching every step of the path
     */
    public GoToTargetNode(final float distanceToTargetTolerance) {
        this.distanceToTargetTolerance = distanceToTargetTolerance;
        this.remainingPathNodes = new LinkedList<>();
        this.currentTarget = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        final var pathProp = this.getBlackboard().getProperty("path", PathNodeListWrapper.class);
        this.currentTarget = Optional.empty();
        this.remainingPathNodes.clear();

        if (pathProp != null && pathProp.getValue() != null) {
            this.remainingPathNodes.addAll(pathProp.getValue().list());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State onUpdate(final float deltaTime) {
        if (remainingPathNodes.isEmpty()) {
            return State.SUCCESS;
        }

        final var actor = this.actorProp.getValue();
        final var actorPosition = actor.getComponentOfType(Transform2D.class).get().getPosition();

        if (this.currentTarget.isEmpty()) {
            this.currentTarget = Optional.ofNullable(this.remainingPathNodes.poll());
        }

        final var targetPosition = ConversionUtility.convertGridToWorldPosition(
                this.currentTarget.get().getPosition(), 0, 0
        );

        final var distanceToTarget = actorPosition.distance(targetPosition);

        if (distanceToTarget <= distanceToTargetTolerance) {
            this.currentTarget = Optional.empty();
        }

        final var actorTransform = actor.getComponentOfType(Transform2D.class).get();
        final var speed = SPEED;

        final var movement = targetPosition
                .subtract(actorPosition)
                .normalized()
                .multiply(speed * deltaTime);
        final var newPosition = actorPosition.add(movement);

        actorTransform.setPosition(newPosition);

        return State.RUNNING;
    }
}
