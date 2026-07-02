package clashclass.ai.logic;

import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;

import java.util.Comparator;
import java.util.List;

/**
 * Represents a ChooseTargetLogic implementation.
 * It acts by choosing the nearest of the potential targets.
 */
public class ChooseTargetNearestLogicImpl implements ChooseTargetLogic {
    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject chooseTarget(final GameObject actor, final List<GameObject> potentialTargets) {
        final var actorPosition = actor.getComponentOfType(Transform2D.class).get().getPosition();
        return potentialTargets.stream()
                .min(Comparator.comparingDouble(target -> target
                        .getComponentOfType(Transform2D.class)
                        .get()
                        .getPosition()
                        .distance(actorPosition)))
                .orElseThrow();
    }
}
