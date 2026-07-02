package ala.controllers;

import java.util.List;

import ala.models.FireBallModel;
import ala.models.LuciferBasicModel;
import ala.views.FireBallView;
import ala.views.LuciferBasicView;
import ala.views.LuciferView;
import ala.views.ScratchView;
import ala.models.LuciferModel;
import ala.models.ScratchModel;

/**
 * LuciferController class.
 * 
 */
public class LuciferController extends LuciferBasicController implements LuciferAbilitiesController {
    //Attributes:
    private LuciferModel luciferModel;
    private LuciferView luciferView;

    //Constructors:
    /**
     * Constructor.
     * 
     * @param luciferModel
     * @param luciferView
     */
    public LuciferController(final LuciferModel luciferModel, final LuciferView luciferView) {
        super(luciferModel, luciferView); //make this variable constants
        this.luciferModel = luciferModel;
        this.luciferView = luciferView;
    }

    //Getters&Setters:
    public final LuciferModel getLuciferModel() {
        return luciferModel;
    }

    public final void setLuciferModel(final LuciferModel luciferModel) {
        this.luciferModel = luciferModel;
    }

    public final LuciferBasicView getLuciferView() {
        return luciferView;
    }

    public final void setLuciferView(final LuciferView luciferView) {
        this.luciferView = luciferView;
    }

    //Methods:
    /**
     * Make Lucifer move with keyboard pression.
     * 
     * @param isLeftPressed
     * @param isRightPressed
     * @param isJumpPressed
     */
    @Override
    public void moveLucifer(final boolean isLeftPressed, final boolean isRightPressed, final boolean isJumpPressed) {
        if (luciferModel.isAlive()) {
            if (isJumpPressed && luciferModel.getCurrentJumpHeight() == 0) {
                luciferModel.setDy(-LuciferModel.getDeltaJumpMaxVelocity());
            }
            if (isLeftPressed && !isRightPressed  && !luciferModel.checkBounds(isLeftPressed, isRightPressed)) {
                luciferModel.setDx(-LuciferModel.getLuciferXSpeed());
                if (luciferModel.getLastDirection() > -1) { //Change the current image only if lucifer was moving to the Right or was waiting
                    luciferView.setImgLuciferWalkLeft();
                    luciferModel.setLastDirection(-1);
                }
            } else if (!isLeftPressed && isRightPressed && !luciferModel.checkBounds(isLeftPressed, isRightPressed)) {
                  luciferModel.setDx(LuciferModel.getLuciferXSpeed());
                  if (luciferModel.getLastDirection() < 1) { //Change the current image only if lucifer was moving to the Left or was waiting
                      luciferView.setImgLuciferWalkRight();
                      luciferModel.setLastDirection(1);
                  }
              } else {
                    if (luciferModel.getLastDirection() == 1 && luciferModel.getDx() != 0) { //Change the current calm pose only if lastDirection is changed or Lucifero was moving
                        luciferView.setImgLuciferCalmPosRight();
                        luciferModel.setDx(0);
                        luciferModel.setLastDirection(0);
                    } else if (luciferModel.getLastDirection() == -1 && luciferModel.getDx() != 0) { //Change the current calm pose only if lastDirection is changed or Lucifero was moving
                          luciferView.setImgLuciferCalmPosLeft();
                          luciferModel.setDx(0);
                          luciferModel.setLastDirection(0);
                      } else if ((!this.luciferView.luciferDirection()) && luciferModel.getAnimationCounter() >= LuciferModel.getAnimationTime()) {
                            luciferView.setImgLuciferCalmPosLeft();
                        } else if (luciferModel.getAnimationCounter() >= LuciferModel.getAnimationTime()) {
                              luciferView.setImgLuciferCalmPosRight();
                          }
               }
        } else {
            luciferModel.setDx(0);
            luciferModel.setDy(0);
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
    public void luciferAttack(final List<DynamicGameObjectController> dynamicObjectsInScene, final boolean isMeleeAttackPressed, final boolean isRangedAttackPressed, final boolean isFinalAttackPressed) { //To implements the final attack
        if (isMeleeAttackPressed && luciferModel.isAlive() && luciferModel.getMeleeAttackChargeCounter() >= LuciferModel.getMeleeAttackChargeTime()) {
            ScratchModel newScratchModel = new ScratchModel();
            switch (luciferModel.getLastDirection()) { //Change the current image only if lucifer was moving to the Right or was waiting
                case -1:luciferView.setImgLuciferScratchLeft();
                    newScratchModel = new ScratchModel(luciferView.getImageView().getLayoutX() - luciferView.getImageView().getImage().getWidth(), luciferView.getImageView().getLayoutY(), 180);
                    break;
                case 1: luciferView.setImgLuciferScratchRight();
                    newScratchModel = new ScratchModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth(), luciferView.getImageView().getLayoutY(), 0);
                    break;
                case 0: 
                    if (!this.luciferView.luciferDirection()) {
                        luciferView.setImgLuciferScratchLeft();
                        newScratchModel = new ScratchModel(luciferView.getImageView().getLayoutX() - luciferView.getImageView().getImage().getWidth(), luciferView.getImageView().getLayoutY(), 180);
                    } else {
                        luciferView.setImgLuciferScratchRight();
                        newScratchModel = new ScratchModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth(), luciferView.getImageView().getLayoutY(), 0);
                    }
                    break;
                default: break;
            }
            ScratchView newScratchView = new ScratchView(luciferView.getLayer(), newScratchModel);
            this.addNewObjectInScene(dynamicObjectsInScene, new ScratchController(newScratchModel, newScratchView));
            luciferModel.setMeleeAttackChargeCounter(0);
        } else if (isRangedAttackPressed && luciferModel.isAlive() && luciferModel.getRangedAttackChargeCounter() >= LuciferModel.getRangedAttackChargeTime()) {
            FireBallModel newFireBallModel = new FireBallModel();
            switch (luciferModel.getLastDirection()) { //Change the current image only if lucifer was moving to the Right or was waiting
                case -1:luciferView.setImgLuciferFireballLeft();
                        newFireBallModel = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 0, this.luciferModel.getType(), -LuciferModel.getRangedAttackSpeed(), 0, LuciferModel.getRangedAttackDamage());
                        break;
                case 1: luciferView.setImgLuciferFireballRight();
                        newFireBallModel = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 180, this.luciferModel.getType(), LuciferModel.getRangedAttackSpeed(), 0, LuciferModel.getRangedAttackDamage());
                        break;
                case 0: if (!this.luciferView.luciferDirection()) {
                            luciferView.setImgLuciferFireballLeft();
                            newFireBallModel = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 0, this.luciferModel.getType(), -LuciferModel.getRangedAttackSpeed(), 0, LuciferModel.getRangedAttackDamage());
                        } else {
                            luciferView.setImgLuciferFireballRight();
                            newFireBallModel = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 180, this.luciferModel.getType(), LuciferModel.getRangedAttackSpeed(), 0, LuciferModel.getRangedAttackDamage());
                        }
                        break;
                default: break;
            }
            FireBallView newFireBallView = new FireBallView(luciferView.getLayer(), newFireBallModel);
            this.addNewObjectInScene(dynamicObjectsInScene, new FireBallController(newFireBallModel, newFireBallView));
            luciferModel.setRangedAttackChargeCounter(0);
            luciferModel.setAnimationCounter(0);
            luciferView.getLuciferStaminaView().getStatusBar().setWidth(0);
        } else if (isFinalAttackPressed && luciferModel.isAlive() && luciferModel.getFinalAttackChargeCounter() >= LuciferModel.getFinalAttackChargeTime()) {
            FireBallModel newFireBallModelA = new FireBallModel();
            FireBallModel newFireBallModelB = new FireBallModel();
            for (int i = 0; i < LuciferBasicModel.getRangedAttacks(); i++) {
                switch (luciferModel.getLastDirection()) { //Change the current image only if lucifer was moving to the Right or was waiting
                    case -1:luciferView.setImgLuciferFireballLeft();
                            newFireBallModelA = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 0, this.luciferModel.getType(), -LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                            newFireBallModelB = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 180, this.luciferModel.getType(), LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                            break;
                    case 1: luciferView.setImgLuciferFireballRight();
                            newFireBallModelA = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 180, this.luciferModel.getType(), LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                            newFireBallModelB = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 0, this.luciferModel.getType(), -LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                            break;
                    case 0: if (!this.luciferView.luciferDirection()) {
                                luciferView.setImgLuciferFireballLeft();
                                newFireBallModelA = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 0, this.luciferModel.getType(), -LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                                newFireBallModelB = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 180, this.luciferModel.getType(), LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                            } else {
                                luciferView.setImgLuciferFireballRight();
                                newFireBallModelA = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 180, this.luciferModel.getType(), LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                                newFireBallModelB = new FireBallModel(luciferView.getImageView().getLayoutX() + luciferView.getImageView().getImage().getWidth() / 2.0 - FireBallView.getImgFireBall().getWidth() / 2.0, luciferView.getImageView().getLayoutY(), 0, this.luciferModel.getType(), -LuciferModel.getRangedAttackSpeed(), -LuciferBasicModel.getRangedAttacksSpread() * i, LuciferModel.getRangedAttackDamage());
                            }
                            break;
                    default: break;
                }
                FireBallView newFireBallViewA = new FireBallView(luciferView.getLayer(), newFireBallModelA);
                FireBallView newFireBallViewB = new FireBallView(luciferView.getLayer(), newFireBallModelB);
                this.addNewObjectInScene(dynamicObjectsInScene, new FireBallController(newFireBallModelA, newFireBallViewA));
                this.addNewObjectInScene(dynamicObjectsInScene, new FireBallController(newFireBallModelB, newFireBallViewB));
            }
            luciferModel.setFinalAttackChargeCounter(0);
            luciferModel.setAnimationCounter(0);
            luciferView.getLuciferFinalAttackBarView().getStatusBar().setWidth(0);
        }
    }
}

