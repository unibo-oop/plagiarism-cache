package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.logic.ChooseTargetLogic;
import clashclass.commons.BuildingTypeComponentImpl;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;


/**
 * Represents a node used to choose the next building to attack.
 */
public class ChooseNextTargetBuildingNode extends AbstractBehaviourNode {
    private final ChooseTargetLogic chooseTargetLogic;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<GameObject> targetProp;
    private BlackboardProperty<GameObjectListWrapper> potentialTargetsProp;

    /**
     * Constructs the node.
     *
     * @param chooseTargetLogic the logic to choose the target
     */
    public ChooseNextTargetBuildingNode(final ChooseTargetLogic chooseTargetLogic) {
        this.chooseTargetLogic = chooseTargetLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
        this.potentialTargetsProp = this.getBlackboard().getProperty("potentialTargets", GameObjectListWrapper.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var potentialTargets = this.potentialTargetsProp.getValue().list()
                .stream()
                .filter(x -> !x.getComponentOfType(BuildingTypeComponentImpl.class).get()
                        .getBuildingType().equals(VillageElementData.WALL))
                .toList();

        if (potentialTargets.isEmpty()) {
            return State.RUNNING;
        }

        final var nextTarget = chooseTargetLogic.chooseTarget(actor, potentialTargets);
        if (nextTarget.isMarkedAsDestroyed()) {
            return State.RUNNING;
        }
        this.targetProp.setValue(nextTarget);

        return State.SUCCESS;
    }
}
