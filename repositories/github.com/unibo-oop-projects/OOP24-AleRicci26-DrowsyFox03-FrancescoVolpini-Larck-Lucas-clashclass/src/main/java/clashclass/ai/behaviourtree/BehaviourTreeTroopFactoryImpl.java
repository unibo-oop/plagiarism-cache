package clashclass.ai.behaviourtree;

import clashclass.ai.logic.ChooseTargetNearestLogicImpl;
import clashclass.ai.pathfinding.AStarPathfindingImpl;
import clashclass.ai.pathfinding.EuclideanDistanceHeuristicImpl;

import java.util.List;

/**
 * Represents a {@link BehaviourTreeFactory} implementation for troops.
 */
public class BehaviourTreeTroopFactoryImpl implements BehaviourTreeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public BehaviourTree create() {
        return new BehaviourTreeImpl(
            new RootNode(
                new SequenceNode(List.of(
                    new ChooseNextTargetBuildingNode(new ChooseTargetNearestLogicImpl()),
                    new FindPathToTargetNode(new AStarPathfindingImpl(new EuclideanDistanceHeuristicImpl())),
                    new GoToTargetNode(0.5f),
                    new WaitNode(0.5f),
                    new RepeatNode(
                        new SequenceNode(List.of(
                            new DamageTargetNode(),
                            new WaitNode(0.5f)
                        ))
                    )
                ))
            )
        );
    }
}
