package ala.controllers;

import java.util.List;

import ala.models.FireBallModel;
import ala.models.LuciferBasicModel;
import ala.models.LuciferFlyModel;
import ala.models.LuciferModel;
import ala.models.ScratchModel;
import ala.views.FireBallView;
import ala.views.LuciferFlyView;
import ala.views.ScratchView;

/**
 * LuciferFlyController class.
 * 
 */
public class LuciferFlyController extends LuciferBasicController implements LuciferFlyAbilitiesController {

    //attributes:
    private LuciferFlyModel luciferFlyModel;
    private LuciferFlyView luciferFlyView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param luciferFlyModel
     * @param luciferFlyView
     */
    public LuciferFlyController(final LuciferFlyModel luciferFlyModel, final LuciferFlyView luciferFlyView) {
        super(luciferFlyModel, luciferFlyView);
        this.luciferFlyModel = luciferFlyModel;
        this.luciferFlyView = luciferFlyView;
    }

    //Getters&Setters:
    public final LuciferFlyView getLuciferFlyView() {
        return luciferFlyView;
    }

    public final LuciferFlyModel getLuciferFlyModel() {
        return luciferFlyModel;
    }

    public final void setLuciferFlyModel(final LuciferFlyModel luciferFlyModel) {
        this.luciferFlyModel = luciferFlyModel;
    }

    public final void setLuciferFlyView(final LuciferFlyView luciferFlyView) {
        this.luciferFlyView = luciferFlyView;
    }

    //Getters&Setters:
    /**
     * Make Lucifer move with keyboard pression.
     * 
     * @param isLeftPressed
     * @param isRightPressed
     * 
     */
    @Override
    public void moveLucifer(final boolean isLeftPressed, final boolean isRightPressed) {
        if (luciferFlyModel.isAlive()) {
            if (isLeftPressed && !isRightPressed && !luciferFlyModel.checkBounds(isLeftPressed, isRightPressed)) {
                this.luciferFlyModel.setDx(-LuciferFlyModel.getLuciferXSpeed());
            } else if (!isLeftPressed && isRightPressed && !luciferFlyModel.checkBounds(isLeftPressed, isRightPressed)) {
                this.luciferFlyModel.setDx(LuciferFlyModel.getLuciferXSpeed());
            } else if (!isLeftPressed && !isRightPressed) {
                this.luciferFlyModel.setDx(0);
            }
            if (this.luciferFlyModel.getAnimationCounter() >= LuciferBasicModel.getAnimationTime()) {
                this.luciferFlyView.setImgLuciferFly();
            }
        } else {
            luciferFlyModel.setDx(0);
            luciferFlyModel.setDy(0);
        }
    }

    /**
     * Make Lucifer attack with keyboard pression.
     * 
     * @param dynamicObjectsInScene
     * @param isMeleeAttackPressed
     * @param isRangedAttackPressed
     * @param isFinalAttackPressed
     * 
     */
    @Override
    public void luciferAttack(final List<DynamicGameObjectController> dynamicObjectsInScene, final boolean isMeleeAttackPressed, final boolean isRangedAttackPressed, final boolean isFinalAttackPressed) {
        if (isMeleeAttackPressed && luciferFlyModel.isAlive() && luciferFlyModel.getMeleeAttackChargeCounter() >= LuciferModel.getMeleeAttackChargeTime()) {
            this.luciferFlyView.setImgLuciferFlyScratch();
            ScratchModel newScratchModel = new ScratchModel(luciferFlyView.getImageView().getLayoutX(), luciferFlyView.getImageView().getLayoutY() - luciferFlyView.getImageView().getImage().getHeight() / 2, ScratchModel.SCRATCH_LEFT_ROTATION);
            this.addNewObjectInScene(dynamicObjectsInScene, new ScratchController(newScratchModel, new ScratchView(luciferFlyView.getLayer(), newScratchModel)));
            luciferFlyModel.setMeleeAttackChargeCounter(0);
            luciferFlyModel.setAnimationCounter(0);
        } else if (isRangedAttackPressed && luciferFlyModel.isAlive() && this.luciferFlyModel.getRangedAttackChargeCounter() >= LuciferBasicModel.getRangedAttackChargeTime()) {
            this.luciferFlyView.setImgLuciferFlyFireball();
            this.luciferFlyModel.setRangedAttackChargeCounter(0);
            this.luciferFlyModel.setAnimationCounter(0);
            FireBallModel newFireBallModel = new FireBallModel(luciferFlyView.getImageView().getLayoutX() + luciferFlyView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferFlyView.getImageView().getLayoutY(), 90, this.luciferFlyModel.getType(), 0, -LuciferBasicModel.getRangedAttackSpeed(), LuciferBasicModel.getRangedAttackDamage());
            FireBallView newFireBallView = new FireBallView(luciferFlyView.getLayer(), newFireBallModel);
            this.addNewObjectInScene(dynamicObjectsInScene, new FireBallController(newFireBallModel, newFireBallView));
            luciferFlyModel.setRangedAttackChargeCounter(0);
            luciferFlyModel.setAnimationCounter(0);
            luciferFlyView.getLuciferStaminaView().getStatusBar().setWidth(0);
        } else if (isFinalAttackPressed && luciferFlyModel.isAlive() && luciferFlyModel.getFinalAttackChargeCounter() >= LuciferModel.getFinalAttackChargeTime()) {
            FireBallModel newFireBallModelA = new FireBallModel();
            FireBallModel newFireBallModelB = new FireBallModel();
            for (int i = 0; i < LuciferBasicModel.getRangedAttacks(); i++) {
                luciferFlyView.setImgLuciferFlyFireball();
                newFireBallModelA = new FireBallModel(luciferFlyView.getImageView().getLayoutX() + luciferFlyView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferFlyView.getImageView().getLayoutY(), 180, this.luciferFlyModel.getType(), LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                newFireBallModelB = new FireBallModel(luciferFlyView.getImageView().getLayoutX() + luciferFlyView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferFlyView.getImageView().getLayoutY(), 0, this.luciferFlyModel.getType(), -LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                FireBallView newFireBallViewA = new FireBallView(luciferFlyView.getLayer(), newFireBallModelA);
                FireBallView newFireBallViewB = new FireBallView(luciferFlyView.getLayer(), newFireBallModelB);
                this.addNewObjectInScene(dynamicObjectsInScene, new FireBallController(newFireBallModelA, newFireBallViewA));
                this.addNewObjectInScene(dynamicObjectsInScene, new FireBallController(newFireBallModelB, newFireBallViewB));
            }
            luciferFlyModel.setFinalAttackChargeCounter(0);
            luciferFlyModel.setAnimationCounter(0);
            luciferFlyView.getLuciferFinalAttackBarView().getStatusBar().setWidth(0);
        }
    }
}
