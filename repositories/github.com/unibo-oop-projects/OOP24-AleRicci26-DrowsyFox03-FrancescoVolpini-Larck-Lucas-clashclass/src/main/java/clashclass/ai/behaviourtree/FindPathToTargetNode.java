package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.PathNodeListWrapper;
import clashclass.ai.pathfinding.PathNode;
import clashclass.ai.pathfinding.PathNodeGrid;
import clashclass.ai.pathfinding.PathfindingAlgorithm;
import clashclass.commons.BuildingTypeComponentImpl;
import clashclass.commons.ConversionUtility;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node used to find a valid path to the current target.
 */
public class FindPathToTargetNode extends AbstractBehaviourNode {
    private static final float VALID_PATH_COST_LIMIT = 995.0f;
    private final PathfindingAlgorithm pathfindingAlgorithm;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<PathNodeGrid> pathNodeGridProp;
    private BlackboardProperty<GameObject> targetProp;
    private BlackboardProperty<PathNodeListWrapper> pathProp;

    /**
     * Constructs the node.
     *
     * @param pathfindingAlgorithm the algorithm used for pathfinding
     */
    public FindPathToTargetNode(final PathfindingAlgorithm pathfindingAlgorithm) {
        this.pathfindingAlgorithm = pathfindingAlgorithm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
        this.pathProp = this.getBlackboard().getProperty("path", PathNodeListWrapper.class);
        this.pathNodeGridProp = this.getBlackboard().getProperty("pathNodeGrid", PathNodeGrid.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var target = this.targetProp.getValue();
        final var pathNodeGrid = this.pathNodeGridProp.getValue();

        final var actorGridPosition = ConversionUtility.convertWorldToGridPosition(
                actor.getComponentOfType(Transform2D.class).get().getPosition()
        );

        final var targetGridPosition = ConversionUtility.convertWorldToGridPosition(
                target.getComponentOfType(Transform2D.class).get().getPosition()
        );

        final var pathResult = pathfindingAlgorithm.findPath(
                pathNodeGrid,
                pathNodeGrid.getNode(actorGridPosition.x(), actorGridPosition.y()),
                pathNodeGrid.getNode(targetGridPosition.x(), targetGridPosition.y()));

        if (pathResult.cost() > VALID_PATH_COST_LIMIT) {
            final List<PathNode> newPath = new ArrayList<>();
            GameObject wall = null;

            for (final var pathNode : pathResult.pathNodes()) {
                newPath.add(pathNode);
                if (pathNode.getRefGameObject().isPresent() && pathNode.getRefGameObject().get()
                        .getComponentOfType(BuildingTypeComponentImpl.class).get()
                        .getBuildingType().equals(VillageElementData.WALL)) {
                    wall = pathNode.getRefGameObject().get();
                    break;
                }
            }

            this.pathProp.setValue(new PathNodeListWrapper(newPath));
            this.targetProp.setValue(wall);
        } else {
            this.pathProp.setValue(new PathNodeListWrapper(pathResult.pathNodes()));
        }

        return State.SUCCESS;
    }
}
