package clashclass.ai.behaviourtree;

import clashclass.ai.logic.ChooseTargetNearestLogicImpl;

import java.util.List;

/**
 * Represents a {@link BehaviourTreeFactory} implementation for defense buildings.
 */
public class BehaviourTreeDefenseBuildingFactoryImpl implements BehaviourTreeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public BehaviourTree create() {
        return new BehaviourTreeImpl(
            new RootNode(
                new SequenceNode(List.of(
                    new ChooseNextTargetTroopNode(new ChooseTargetNearestLogicImpl()),
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
