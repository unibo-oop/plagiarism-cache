package ala.controllers;

import java.util.Iterator;

import ala.models.CurrentLevelModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.NormalParadiseLevelGeneratorModel;
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
import ala.models.LuciferBasicModel;
import ala.views.DisplayManagerView;
import ala.views.NormalParadiseLevelGeneratorView;
import javafx.animation.AnimationTimer;

/**
 * NormalParadiseLevelGeneratorController class.
 * 
 */
public class NormalParadiseLevelGeneratorController extends LevelGeneratorPatternController implements NormalLevelGeneratorController {

    //Attributes:
    private NormalParadiseLevelGeneratorModel normalParadiseLevelGeneratorModel;
    private NormalParadiseLevelGeneratorView normalParadiseLevelGeneratorView;

    private ParadiseShootingEnemyController shootingEnemyController;

    /**
     * Constructor.
     * 
     * @param normalParadiseLevelGeneratorModel
     * @param normalParadiseLevelGeneratorView
     * 
     */
    public NormalParadiseLevelGeneratorController(final NormalParadiseLevelGeneratorModel normalParadiseLevelGeneratorModel, final NormalParadiseLevelGeneratorView normalParadiseLevelGeneratorView) {
        super(normalParadiseLevelGeneratorModel, normalParadiseLevelGeneratorView);
        this.normalParadiseLevelGeneratorModel = normalParadiseLevelGeneratorModel;
        this.normalParadiseLevelGeneratorView = normalParadiseLevelGeneratorView;
    }

    //Getters&Setters:
    public final ParadiseShootingEnemyController getShootingEnemyController() {
        return shootingEnemyController;
    }

    public final NormalParadiseLevelGeneratorModel getNormalParadiseLevelGeneratorModel() {
        return normalParadiseLevelGeneratorModel;
    }

    public final void setNormalParadiseLevelGeneratorModel(final NormalParadiseLevelGeneratorModel normalParadiseLevelGeneratorModel) {
        this.normalParadiseLevelGeneratorModel = normalParadiseLevelGeneratorModel;
    }

    public final NormalParadiseLevelGeneratorView getNormalParadiseLevelGeneratorView() {
        return normalParadiseLevelGeneratorView;
    }

    public final void setNormalParadiseLevelGeneratorView(final NormalParadiseLevelGeneratorView normalParadiseLevelGeneratorView) {
        this.normalParadiseLevelGeneratorView = normalParadiseLevelGeneratorView;
    }

    public final void setShootingEnemyController(final ParadiseShootingEnemyController shootingEnemyController) {
        this.shootingEnemyController = shootingEnemyController;
    }

    //Methods:
    /**
     * Create game loop.
     * 
     */
    @Override
    public void createGameLoop() {
        this.normalParadiseLevelGeneratorModel.setGameLoop(new AnimationTimer()  {
            @Override
            public void handle(final long l) {
                // move GameObjects internally and on Screen
                moveObjectsInScene();

                //Updates lucifer internal and on screen position and variables:
                // get keyboard input for: horizontal and jump movements
                ((LuciferFlyController) getLuciferBasicController()).moveLucifer(normalParadiseLevelGeneratorView.getInputManager().isMovingLeft(), normalParadiseLevelGeneratorView.getInputManager().isMovingRight());
                ((LuciferFlyController) getLuciferBasicController()).luciferAttack(getDynamicObjectsInScene(), normalParadiseLevelGeneratorView.getInputManager().isUsingMeleeAttack(), normalParadiseLevelGeneratorView.getInputManager().isUsingRangedAttack(), normalParadiseLevelGeneratorView.getInputManager().isUsingFinalAttack());
                ((LuciferFlyController) getLuciferBasicController()).exitLevel(normalParadiseLevelGeneratorView.getInputManager().isExitingLevel());
                ((LuciferFlyController) getLuciferBasicController()).updateLuciferData();
                ((LuciferFlyController) getLuciferBasicController()).getDynamicGameObjectModel().move();
                ((LuciferFlyController) getLuciferBasicController()).getDynamicGameObjectView().updateUI();

                //Move Camera:
                if (getLuciferBasicController().getLuciferBasicModel().getY() >= LevelGeneratorPatternModel.END_OF_LEVEL - getLuciferBasicController().getLuciferBasicModel().getHeight()) {
                    normalParadiseLevelGeneratorView.moveCamera(getLuciferBasicController().getLuciferBasicModel());
                    normalParadiseLevelGeneratorView.getLuciferBasicView().getLuciferHealthView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight());
                    normalParadiseLevelGeneratorView.getLuciferBasicView().getLuciferStaminaView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight() + LuciferBasicModel.STAMINA_BAR_DELTA_MOVEMENT);
                    normalParadiseLevelGeneratorView.getLuciferBasicView().getLuciferFinalAttackBarView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight() + LuciferBasicModel.FINAL_ATTACK_BAR_DELTA_MOVEMENT);
                }

                //Enemy fire test
                enemiesAttack();

                // check GameObjects visibility
                // remove every GameObjects that's not visible anymore
                removeGameObjectsIfNotVisible();

                //Check collisions between all the objects into objectInScene list.
                checkAliveObjectInSceneCollisions();
                checkLuciferCollisions();

                 //Update and show debug info:
                 normalParadiseLevelGeneratorModel.getDebugInformations().calculateFPS();
                 normalParadiseLevelGeneratorView.getDebugLabel().setText("FPS: " + normalParadiseLevelGeneratorModel.getDebugInformations().getFpsCurrent()); //Number of sprites current on the screen and the overall fps of the game. 

                 //unlock next level if Lucifer arrive at the top
                 if (getLuciferBasicController().getLuciferBasicModel().getY() <= LevelGeneratorPatternModel.END_OF_LEVEL) {
                     normalParadiseLevelGeneratorModel.getGameLoop().stop();
                     DisplayManagerView.setMessage(true);
                     normalParadiseLevelGeneratorView.backToMenuScene();
                     CurrentLevelModel.setCurrentLevel(CurrentLevelModel.LEVELS.CINQUE);
                     CurrentLevelModel.unlock();
                 }

                 if (!getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                     normalParadiseLevelGeneratorModel.setDeathAnimationCounter(normalParadiseLevelGeneratorModel.getDeathAnimationCounter() + 1);
                     if (normalParadiseLevelGeneratorModel.getDeathAnimationCounter() == LevelGeneratorPatternModel.DEATH_ANIMATION_TIME) {
                         normalParadiseLevelGeneratorModel.getGameLoop().stop();
                         DisplayManagerView.setMessage(false);
                         normalParadiseLevelGeneratorView.backToMenuScene();
                     }
                }
            }
        });
        normalParadiseLevelGeneratorModel.getGameLoop().start();
    }

    //The following methods are used in the CreateGameLoop method.
    /**
     * Method used in the CreateGameLoop method that make paradise objects move.
     * 
     */
    @Override
    public void moveObjectsInScene() {
        // move GameObjects internally
        for (DynamicGameObjectController dynamicGameObjectController: getDynamicObjectsInScene()) {
            dynamicGameObjectController.getDynamicGameObjectModel().move();
        }

        // move GameObjects on screen
        for (DynamicGameObjectController dynamicGameObjectController: getDynamicObjectsInScene()) {
            if (dynamicGameObjectController.getDynamicGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                dynamicGameObjectController.getDynamicGameObjectView().getImageView().relocate(dynamicGameObjectController.getGameObjectModel().getX(), dynamicGameObjectController.getGameObjectModel().getY());
            }
        }

        // move GameObjects internally
        for (StandardEnemyController standardEnemyController: getEnemyCharactersInScene()) {
            standardEnemyController.death();
            standardEnemyController.getStandardEnemyModel().checkRanged(this.getLuciferBasicController().getLuciferBasicModel().getY());
            if (standardEnemyController.getStandardEnemyModel().getType() == OBJECTSTYPE.SHOOTING_ENEMY) {
                ((ParadiseShootingEnemyController) standardEnemyController).getParadiseShootingEnemyModel().moveEnemy();
            }
            if (standardEnemyController.getStandardEnemyModel().getType() == OBJECTSTYPE.WALKING_ENEMY) {
                ((ParadiseWalkingEnemyController) standardEnemyController).getParadiseWalkingEnemyModel().moveEnemy();
            }
            if (standardEnemyController.getStandardEnemyModel().getType() == OBJECTSTYPE.BOSS_SHOOTING_ENEMY) {
                ((ParadiseShootingMiniBossController) standardEnemyController).getParadiseShootingMiniBossModel().moveEnemy();
            }
            if (standardEnemyController.getStandardEnemyModel().getType() == OBJECTSTYPE.BOSS_WALKING_ENEMY) {
                ((ParadiseWalkingMiniBossController) standardEnemyController).getParadiseWalkingMiniBossModel().moveEnemy();
            }
            standardEnemyController.getDynamicGameObjectModel().move();

            //Move Objects on screen:
            standardEnemyController.getDynamicGameObjectView().getImageView().relocate(standardEnemyController.getGameObjectModel().getX(), standardEnemyController.getGameObjectModel().getY());
        }
    }

    /**
     * Method used in the CreateGameLoop method that make paradise enemies attack.
     * 
     */
    @Override
    public void enemiesAttack() {
        for (StandardEnemyController standardEnemyController: this.getEnemyCharactersInScene()) {
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.SHOOTING_ENEMY) {
                ((ParadiseShootingEnemyController) standardEnemyController).fire(this.getDynamicObjectsInScene());
            }
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.WALKING_ENEMY) {
                ((ParadiseWalkingEnemyController) standardEnemyController).walkingEnemyAttack();
            }
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.BOSS_WALKING_ENEMY) {
                ((ParadiseWalkingMiniBossController) standardEnemyController).walkingEnemyAttack();
            }
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.BOSS_SHOOTING_ENEMY) {
                ((ParadiseShootingMiniBossController) standardEnemyController).fire(this.getDynamicObjectsInScene());
            }
        }
    }

    //Collisions between Lucifer and Other enemies , staticObjects or Dynamic objects:
    /**
     * check collisions between Lucifer and other enemies.
     * 
     */
    public void checkLuciferCollisions() {
           //check the collisions between all the GameObjects in the list objectInscene and them whit Lucifer, these collisions will be always in all levels.
           Iterator<StandardEnemyController> iterOnAliveHittedObjects = this.getEnemyCharactersInScene().iterator();
           while (iterOnAliveHittedObjects.hasNext()) {
               //Search for collisions with static objects in scene.
               GameObjectAliveController aliveCharacterHitted = iterOnAliveHittedObjects.next();
               if (getLuciferBasicController().getGameObjectModel().getHitBox().checkCollision(aliveCharacterHitted.getGameObjectModel().getHitBox()) && aliveCharacterHitted.getGameObjectAliveModel().isAlive()) { 
                   System.out.println(aliveCharacterHitted.getGameObjectModel().getType() + "Is Hit by " + getLuciferBasicController().getGameObjectModel().getType());
                   if (aliveCharacterHitted.getDynamicGameObjectModel().getType() == OBJECTSTYPE.WALKING_ENEMY) {
                          ((ParadiseWalkingEnemyController) aliveCharacterHitted).getParadiseWalkingEnemyModel().setAttacking(true);
                   }
                   if (aliveCharacterHitted.getDynamicGameObjectModel().getType() == OBJECTSTYPE.BOSS_WALKING_ENEMY) {
                          ((ParadiseWalkingMiniBossController) aliveCharacterHitted).getParadiseWalkingMiniBossModel().setAttacking(true);
                   }
                   getLuciferBasicController().gettingDamaged(aliveCharacterHitted.getGameObjectModel().getDamageOnContact());
                   if (((LuciferFlyController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(aliveCharacterHitted.getGameObjectModel().getHitBox()) && aliveCharacterHitted.getGameObjectAliveModel().isAlive()) { 
                       ((LuciferFlyController) getLuciferBasicController()).gettingDamaged(aliveCharacterHitted.getGameObjectModel().getDamageOnContact());
                   }
               }
           }
           //menage the collsionos between 2 attacks.
           //Search for collisions with dynamic objects in scene.
           Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
           while (iterOnDynamicObjects.hasNext()) {
                DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
                if (((LuciferFlyController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && ((LuciferFlyController) getLuciferBasicController()).getGameObjectView().getLayer() != dynamicObjectHitted.getGameObjectView().getLayer()) {
                         //The alive object get damage
                         ((LuciferFlyController) getLuciferBasicController()).gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                         //The dynamic object is deleted
                         iterOnDynamicObjects.remove();
                         dynamicObjectHitted.getGameObjectView().removeFromLayer();
                }
           }
     }
}
