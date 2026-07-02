package clashclass.ai.behaviourtree;

import clashclass.ai.behaviourtree.blackboard.BlackboardProperty;
import clashclass.ai.logic.DamageLogicComponent;
import clashclass.commons.HealthComponent;
import clashclass.ecs.GameObject;


/**
 * Represents a node used to inflict damage to the current target.
 */
public class DamageTargetNode extends AbstractBehaviourNode {
    private BlackboardProperty<GameObject> actorProp;
    private BlackboardProperty<GameObject> targetProp;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.actorProp = this.getBlackboard().getProperty("actor", GameObject.class);
        this.targetProp = this.getBlackboard().getProperty("target", GameObject.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public State onUpdate(final float deltaTime) {
        final var actor = this.actorProp.getValue();
        final var target = this.targetProp.getValue();

        final var damage = actor.getComponentOfType(DamageLogicComponent.class).get()
                .getDamageLogic()
                .calculateDamage(actor, target);

        final var targetHealth = target.getComponentOfType(HealthComponent.class).get();
        targetHealth.decrease(damage);

        return State.SUCCESS;
    }
}
