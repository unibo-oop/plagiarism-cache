package clashclass.ai.logic;

import clashclass.ecs.GameObject;

import java.util.List;

/**
 * Represents an algorithm used by the troops in battle,
 * for choosing the next building (target) to attack.
 */
@FunctionalInterface
public interface ChooseTargetLogic {
    /**
     * Chooses the next target to attack.
     *
     * @param actor the in-game actor that needs to choose the next target
     * @param potentialTargets all the potential targets that could be chosen
     *
     * @return the chosen target
     */
    GameObject chooseTarget(GameObject actor, List<GameObject> potentialTargets);
}
