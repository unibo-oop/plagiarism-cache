package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.behaviourtree.blackboard.wrappers.GameObjectListWrapper;
import clashclass.ai.logic.ChooseTargetLogic;
import clashclass.commons.GameConstants;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;
import clashclass.stats.DefenseBuildingBaseStatsComponent;


/**
 * Represents a node used to choose the next troop to attack.
 */
public class ChooseNextTargetTroopNode extends AbstractBehaviourNode {
    private final ChooseTargetLogic chooseTargetLogic;
    private BlackboardProperty<GameObjectListWrapper> troopsProp;
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<GameObject> targetProp;

    /**
     * Constructs the node.
     *
     * @param chooseTargetLogic the logic used to choose the target
     */
    public ChooseNextTargetTroopNode(final ChooseTargetLogic chooseTargetLogic) {
        this.chooseTargetLogic = chooseTargetLogic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
        this.troopsProp = this.getBlackboard().getProperty("troops", GameObjectListWrapper.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var troops = this.troopsProp.getValue().list();

        if (troops.isEmpty()) {
            return State.RUNNING;
        }

        final var stats = actor.getComponentOfType(DefenseBuildingBaseStatsComponent.class)
                .orElseThrow(() -> new RuntimeException("No DefenseBuildingBaseStatsComponent found"));

        final var nextTarget = chooseTargetLogic.chooseTarget(actor, troops);
        if (nextTarget.isMarkedAsDestroyed()) {
            return State.RUNNING;
        }
        final var actorPosition = actor.getComponentOfType(Transform2D.class).get().getPosition();
        final var targetPosition = nextTarget.getComponentOfType(Transform2D.class).get().getPosition();

        final var attackRange = stats.getAttackRange() * GameConstants.TILE_PIXEL_SIZE * GameConstants.TILE_SCALE;
        if (actorPosition.distance(targetPosition) > attackRange) {
            return State.RUNNING;
        }

        this.targetProp.setValue(nextTarget);
        return State.SUCCESS;
    }
}
