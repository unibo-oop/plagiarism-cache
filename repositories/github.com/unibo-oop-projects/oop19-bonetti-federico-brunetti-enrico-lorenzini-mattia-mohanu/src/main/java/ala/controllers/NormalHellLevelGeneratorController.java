package ala.controllers;

import ala.models.CurrentLevelModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.NormalHellLevelGeneratorModel;
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
import ala.models.LuciferBasicModel;
import ala.views.DisplayManagerView;
import ala.views.LevelGeneratorPatternView;
import ala.views.NormalHellLevelGeneratorView;
import javafx.animation.AnimationTimer;

/**
 * NormalHellLevelGeneratorController class.
 * 
 */
public class NormalHellLevelGeneratorController extends LevelGeneratorPatternController implements NormalLevelGeneratorController {
    //Attributes:
    private NormalHellLevelGeneratorModel normalHellLevelGeneratorModel;
    private NormalHellLevelGeneratorView normalHellLevelGeneratorView;

    /**
     * Constructor.
     * 
     * @param normalHellLevelGeneratorModel
     * @param normalHellLevelGeneratorView
     * 
     */
    public NormalHellLevelGeneratorController(final NormalHellLevelGeneratorModel normalHellLevelGeneratorModel, final NormalHellLevelGeneratorView normalHellLevelGeneratorView) {
        super(normalHellLevelGeneratorModel, normalHellLevelGeneratorView);

        this.normalHellLevelGeneratorModel = normalHellLevelGeneratorModel;
        this.normalHellLevelGeneratorView = normalHellLevelGeneratorView;
    }

    //Getters&Setters:
    public final NormalHellLevelGeneratorModel getNormalHellLevelGeneratorModel() {
        return normalHellLevelGeneratorModel;
    }

    public final NormalHellLevelGeneratorView getNormalHellLevelGeneratorView() {
        return normalHellLevelGeneratorView;
    }

    //Methods:
    /**
     * Create game loop.
     * 
     */
    @Override
    public void createGameLoop() {
        this.normalHellLevelGeneratorModel.setGameLoop(new AnimationTimer()  {
            @Override
            public void handle(final long l) {
                // move GameObjects internally and on Screen
                moveObjectsInScene();

                //Updates lucifer and Cerbero internal and on screen position and variables:
                // get keyboard input for: horizontal and jump movements
                ((LuciferController) getLuciferBasicController()).moveLucifer(normalHellLevelGeneratorView.getInputManager().isMovingLeft(), normalHellLevelGeneratorView.getInputManager().isMovingRight(), normalHellLevelGeneratorView.getInputManager().isJumping());
                ((LuciferController) getLuciferBasicController()).getLuciferModel().jumpManager();
                ((LuciferController) getLuciferBasicController()).luciferAttack(getDynamicObjectsInScene(), normalHellLevelGeneratorView.getInputManager().isUsingMeleeAttack(), normalHellLevelGeneratorView.getInputManager().isUsingRangedAttack(), normalHellLevelGeneratorView.getInputManager().isUsingFinalAttack());
                ((LuciferController) getLuciferBasicController()).exitLevel(normalHellLevelGeneratorView.getInputManager().isExitingLevel());
                ((LuciferController) getLuciferBasicController()).updateLuciferData();
                ((LuciferController) getLuciferBasicController()).getDynamicGameObjectModel().move();
                ((LuciferController) getLuciferBasicController()).getDynamicGameObjectView().updateUI();

                //Move Camera and all his stats:
                if (getLuciferBasicController().getLuciferBasicModel().getY() >= LevelGeneratorPatternView.CAMERA_FOLLOW_ON_DISTANCE - getLuciferBasicController().getLuciferBasicModel().getHeight()) {
                    normalHellLevelGeneratorView.moveCamera(getLuciferBasicController().getLuciferBasicModel());
                    normalHellLevelGeneratorView.getLuciferBasicView().getLuciferHealthView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight());
                    normalHellLevelGeneratorView.getLuciferBasicView().getLuciferStaminaView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight() + LuciferBasicModel.STAMINA_BAR_DELTA_MOVEMENT);
                    normalHellLevelGeneratorView.getLuciferBasicView().getLuciferFinalAttackBarView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight() + LuciferBasicModel.FINAL_ATTACK_BAR_DELTA_MOVEMENT);
                }

                //Enemy fire:
                enemiesAttack();

                // check GameObjects visibility
                // remove every GameObjects that's not visible anymore
                removeGameObjectsIfNotVisible();

                //Check collisions between all the objects into objectInScene list.
                checkLuciferCollisions();
                checkAliveObjectInSceneCollisions();

                //Update and show debug info:
                normalHellLevelGeneratorModel.getDebugInformations().calculateFPS();
                normalHellLevelGeneratorView.getDebugLabel().setText("FPS: " + normalHellLevelGeneratorModel.getDebugInformations().getFpsCurrent()); //Number of sprites current on the screen and the overall fps of the game. 

                //Exit Level
                //unlock next level if Lucifer arrive at the top
                if (getLuciferBasicController().getLuciferBasicModel().getY() <= LevelGeneratorPatternModel.END_OF_LEVEL) {
                    normalHellLevelGeneratorModel.getGameLoop().stop();
                    DisplayManagerView.setMessage(true);
                    normalHellLevelGeneratorView.backToMenuScene();
                    CurrentLevelModel.setCurrentLevel(CurrentLevelModel.LEVELS.UNO);
                    CurrentLevelModel.unlock();
                }

                //Check death:
                if (!((LuciferController) getLuciferBasicController()).getLuciferModel().isAlive()) {
                     normalHellLevelGeneratorModel.setDeathAnimationCounter(normalHellLevelGeneratorModel.getDeathAnimationCounter() + 1);
                     if (normalHellLevelGeneratorModel.getDeathAnimationCounter() == LevelGeneratorPatternModel.DEATH_ANIMATION_TIME) {
                         normalHellLevelGeneratorModel.getGameLoop().stop();
                         DisplayManagerView.setMessage(false);
                         normalHellLevelGeneratorView.backToMenuScene();
                     }
                }
           }
       });
       normalHellLevelGeneratorModel.getGameLoop().start();
    }

    //Methods:
    //The following methods are used in the CreateGameLoop method.
    /**
     * Method used in the CreateGameLoop method that moves objects in scene.
     * 
     */
    @Override
    public void moveObjectsInScene() {
        // move attacks GameObjects internally
        for (DynamicGameObjectController dynamicGameObjectController: getDynamicObjectsInScene()) {
            dynamicGameObjectController.getDynamicGameObjectModel().move();

            // move attacks on screen
            if (dynamicGameObjectController.getDynamicGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                dynamicGameObjectController.getDynamicGameObjectView().getImageView().relocate(dynamicGameObjectController.getGameObjectModel().getX(), dynamicGameObjectController.getGameObjectModel().getY());
            }
        }

        // move enemies internally
        for (StandardEnemyController standardEnemyController: this.getEnemyCharactersInScene()) {
            ((StandardEnemyController) standardEnemyController).death();
            ((StandardEnemyController) standardEnemyController).getStandardEnemyModel().checkRanged(this.getLuciferBasicController().getLuciferBasicModel().getY());
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.WALKING_ENEMY) {
                ((HellWalkingEnemyController) standardEnemyController).moveEnemy();
            }
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.BOSS_WALKING_ENEMY) {
                ((HellWalkingMiniBossController) standardEnemyController).moveEnemy();
            }
            standardEnemyController.getDynamicGameObjectModel().move();

            //Move enemies on screen:
            standardEnemyController.getDynamicGameObjectView().getImageView().relocate(standardEnemyController.getGameObjectModel().getX(), standardEnemyController.getGameObjectModel().getY());
        }
    }

    /**
     * Method used in the CreateGameLoop method that make hell enemies attack.
     * 
     */
    @Override
    public void enemiesAttack() {
        for (StandardEnemyController standardEnemyController: this.getEnemyCharactersInScene()) {
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.SHOOTING_ENEMY) {
                ((HellShootingEnemyController) standardEnemyController).fire(getLuciferBasicController().getLuciferBasicModel().getX(), getLuciferBasicController().getLuciferBasicModel().getY(), this.getDynamicObjectsInScene());
            }
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.BOSS_SHOOTING_ENEMY) {
                ((HellShootingMiniBossController) standardEnemyController).fire(getLuciferBasicController().getLuciferBasicModel().getX(), getLuciferBasicController().getLuciferBasicModel().getY(), this.getDynamicObjectsInScene());
            }
        }
    }
}
