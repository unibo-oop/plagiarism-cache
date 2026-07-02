package ala.controllers;

import ala.models.StandardEnemyModel;
import ala.views.StandardEnemyView;

/**
 * StandardEnemyController class.
 * 
 */
public abstract class StandardEnemyController extends GameObjectAliveController {
    //Attributes:
    private StandardEnemyModel standardEnemyModel;
    private StandardEnemyView standardEnemyView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param standardEnemyModel
     * @param standardEnemyView
     * 
     */
    public StandardEnemyController(final StandardEnemyModel standardEnemyModel, final StandardEnemyView standardEnemyView) {
        super(standardEnemyModel, standardEnemyView);
        this.standardEnemyModel = standardEnemyModel;
        this.standardEnemyView = standardEnemyView;
    }

    //Getters&Setters:
    public final StandardEnemyModel getStandardEnemyModel() {
        return standardEnemyModel;
    }

    public final StandardEnemyView getStandardEnemyView() {
        return standardEnemyView;
    }

    public final void setStandardEnemyModel(final StandardEnemyModel standardEnemyModel) {
        this.standardEnemyModel = standardEnemyModel;
    }

    public final void setStandardEnemyView(final StandardEnemyView standardEnemyView) {
        this.standardEnemyView = standardEnemyView;
    }

    /**
     * make that make StandardEnemyControllers objects die.
     * 
     */
    public void death() {
        if (!this.standardEnemyModel.isAlive()) {
            if (this.standardEnemyModel.getDeathAnimationCounter() >= StandardEnemyModel.DEATH_ANIMATION_TIME) {
                this.standardEnemyView.getLayer().getChildren().remove(this.standardEnemyView.getImageView());
            } else {
                this.standardEnemyModel.setDeathAnimationCounter(this.standardEnemyModel.getDeathAnimationCounter() + 1);
                this.standardEnemyModel.setDx(0);
                this.standardEnemyModel.setDy(0);
            }
        }
    }

    /**
     * method that make StandardEnemyController get damage.
     * 
     * @param damage
     *        need to know damage value to decrease from health bar
     */
    public void gettingDamaged(final double damage) {
        super.gettingDamaged(damage);
        if (!this.standardEnemyModel.isAlive()) {
            this.standardEnemyView.explode();
        }
    }
}
