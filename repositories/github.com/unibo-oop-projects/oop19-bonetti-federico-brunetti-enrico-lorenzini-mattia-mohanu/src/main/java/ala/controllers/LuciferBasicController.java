package ala.controllers;

import ala.models.LuciferBasicModel;
import ala.views.LuciferBasicView;

/**
 * LuciferBasicController class.
 * 
 */
public class LuciferBasicController extends GameObjectAliveController {

    //Attributes:
    private LuciferBasicModel luciferBasicModel;
    private LuciferBasicView luciferBasicView;

    //Constructors:
    public LuciferBasicController() { }

    /**
     * Contructor.
     * 
     * @param luciferBasicModel 
     * @param luciferBasicView
     */
    public LuciferBasicController(final LuciferBasicModel luciferBasicModel, final LuciferBasicView luciferBasicView) {
        super(luciferBasicModel, luciferBasicView);
        this.luciferBasicModel = luciferBasicModel;
        this.luciferBasicView = luciferBasicView;
    }

    public final LuciferBasicModel getLuciferBasicModel() {
        return luciferBasicModel;
    }

    public final LuciferBasicView getLuciferBasicView() {
        return luciferBasicView;
    }

    public final void setLuciferBasicModel(final LuciferBasicModel luciferBasicModel) {
        this.luciferBasicModel = luciferBasicModel;
    }

    public final void setLuciferBasicView(final LuciferBasicView luciferBasicView) {
        this.luciferBasicView = luciferBasicView;
    }

    /**
     * Update Lucifer's lifepoints.
     * 
     * @param damage
     *        take trace of damage to decrease it from health bar
     * 
     */
    public void gettingDamaged(final double damage) {
        super.gettingDamaged(damage);
        this.luciferBasicView.getLuciferHealthView().getStatusBar().setWidth(this.luciferBasicView.getLuciferHealthView().getStatusBar().getWidth() - (damage * 100) / this.luciferBasicModel.getMaxHealth());
        if (!this.luciferBasicModel.isAlive()) {
            this.luciferBasicView.explode();
        }
    }

    /**
     * Update Lucifer's stamina.
     * 
     */
    public void updateLuciferData() { //Increments charge counters each frames only if they aren't at the max limit.
        if (this.luciferBasicModel.getRangedAttackChargeCounter() < LuciferBasicModel.getRangedAttackChargeTime()) {
            this.luciferBasicModel.setRangedAttackChargeCounter(this.luciferBasicModel.getRangedAttackChargeCounter() + 1);
        }
        if (this.luciferBasicModel.getMeleeAttackChargeCounter() < LuciferBasicModel.getMeleeAttackChargeTime()) {
            this.luciferBasicModel.setMeleeAttackChargeCounter(this.luciferBasicModel.getMeleeAttackChargeCounter() + 1);
        }
        if (this.luciferBasicModel.getFinalAttackChargeCounter() < LuciferBasicModel.getFinalAttackChargeTime()) {
            this.luciferBasicModel.setFinalAttackChargeCounter(this.luciferBasicModel.getFinalAttackChargeCounter() + 1);
            this.luciferBasicView.getLuciferFinalAttackBarView().getStatusBar().setWidth(this.luciferBasicView.getLuciferFinalAttackBarView().getStatusBar().getWidth() + LuciferBasicModel.LUCIFER_STAMINA_CHARGE_DELTA);
        }
        if (this.luciferBasicModel.getAnimationCounter() < LuciferBasicModel.getAnimationTime()) {
            this.luciferBasicModel.setAnimationCounter(this.luciferBasicModel.getAnimationCounter() + 1);
        }
        if (this.luciferBasicView.getLuciferStaminaView().getStatusBar().getWidth() < LuciferBasicModel.getRangedAttackChargeTime()) {
            this.luciferBasicView.getLuciferStaminaView().getStatusBar().setWidth(this.luciferBasicView.getLuciferStaminaView().getStatusBar().getWidth() + 1);
        }
    }

    /**
     * Exit from level if esc is pressed.
     * 
     * @param isEscPressed
     *        take trace if ESC is pressed on keyboard
     */
    public void exitLevel(final boolean isEscPressed) {
            if (isEscPressed) {
            luciferBasicModel.setHealth(0);
        }
    }
}
