package ala.controllers;

import java.util.List;

public interface LuciferFlyAbilitiesController {
    /**
     * move flying Lucifer character basing on keyboard pression.
     * 
     * @param isLeftPressed
     * @param isRightPressed
     * 
     */
    void moveLucifer(boolean isLeftPressed, boolean isRightPressed);

    /**
     *  make flying Lucifer character attack basing on keyboard pression.
     * 
     * @param objectsInScene
     * @param isMeleeAttackPressed
     * @param isRangedAttackPressed
     * @param isFinalAttackPressed
     * 
     */
    void luciferAttack(List<DynamicGameObjectController> objectsInScene, boolean isMeleeAttackPressed, boolean isRangedAttackPressed, boolean isFinalAttackPressed);

}
