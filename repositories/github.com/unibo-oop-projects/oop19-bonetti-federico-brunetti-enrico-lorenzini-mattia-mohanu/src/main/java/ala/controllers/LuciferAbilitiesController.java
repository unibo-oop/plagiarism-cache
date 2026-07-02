package ala.controllers;

import java.util.List;
interface LuciferAbilitiesController {

    /**
     * move Lucifer character basing on keyboard pression.
     * 
     * @param isLeftPressed
     * @param isRightPressed
     * @param isJumpPressed
     */
    void moveLucifer(boolean isLeftPressed, boolean isRightPressed, boolean isJumpPressed);

    /**
     *  make Lucifer character attack basing on keyboard pression.
     * 
     * @param objectsInScene
     * @param isMeleeAttackPressed
     * @param isRangedAttackPressed
     * @param isFinalAttackpressed 
     * 
     */
    void luciferAttack(List<DynamicGameObjectController> objectsInScene, boolean isMeleeAttackPressed, boolean isRangedAttackPressed, boolean isFinalAttackPressed);
}
