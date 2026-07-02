package ala.controllers;

import ala.models.CurrentLevelModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.NormalPurgatoryLevelGeneratorModel;
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
import ala.models.LuciferBasicModel;
import ala.views.DisplayManagerView;
import ala.views.NormalPurgatoryLevelGeneratorView;
import javafx.animation.AnimationTimer;

/**
 * NormalPurgatoryLevelGeneratorController class.
 * 
 */
public class NormalPurgatoryLevelGeneratorController extends LevelGeneratorPatternController implements NormalLevelGeneratorController {
    //Attributes:
    private NormalPurgatoryLevelGeneratorModel normalPurgatoryLevelGeneratorModel;
    private NormalPurgatoryLevelGeneratorView normalPurgatoryLevelGeneratorView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param normalPurgatoryLevelGeneratorModel
     * @param normalPurgatoryLevelGeneratorView
     * 
     */
    public NormalPurgatoryLevelGeneratorController(final NormalPurgatoryLevelGeneratorModel normalPurgatoryLevelGeneratorModel, final NormalPurgatoryLevelGeneratorView normalPurgatoryLevelGeneratorView) {
        super(normalPurgatoryLevelGeneratorModel, normalPurgatoryLevelGeneratorView);

        this.normalPurgatoryLevelGeneratorModel = normalPurgatoryLevelGeneratorModel;
        this.normalPurgatoryLevelGeneratorView = normalPurgatoryLevelGeneratorView;
    }

    //Getters&Setters:
    public final NormalPurgatoryLevelGeneratorModel getNormalPurgatoryLevelGeneratorModel() {
        return normalPurgatoryLevelGeneratorModel;
    }

    public final NormalPurgatoryLevelGeneratorView getNormalPurgatoryLevelGeneratorView() {
        return normalPurgatoryLevelGeneratorView;
    }

    //Methods:
    /**
     * Create game loop.
     * 
     */
    @Override
    public void createGameLoop() {
        this.normalPurgatoryLevelGeneratorModel.setGameLoop(new AnimationTimer()  {
            @Override
            public void handle(final long l) {
            // move GameObjects internally and on Screen
            moveObjectsInScene();

            //Updates lucifer and Cerbero internal and on screen position and variables:
            // get keyboard input for: horizontal and jump movements
            ((LuciferController) getLuciferBasicController()).moveLucifer(normalPurgatoryLevelGeneratorView.getInputManager().isMovingLeft(), normalPurgatoryLevelGeneratorView.getInputManager().isMovingRight(), normalPurgatoryLevelGeneratorView.getInputManager().isJumping());
            ((LuciferController) getLuciferBasicController()).getLuciferModel().jumpManager();
            ((LuciferController) getLuciferBasicController()).luciferAttack(getDynamicObjectsInScene(), normalPurgatoryLevelGeneratorView.getInputManager().isUsingMeleeAttack(), normalPurgatoryLevelGeneratorView.getInputManager().isUsingRangedAttack(), normalPurgatoryLevelGeneratorView.getInputManager().isUsingFinalAttack());
            ((LuciferController) getLuciferBasicController()).exitLevel(normalPurgatoryLevelGeneratorView.getInputManager().isExitingLevel());
            ((LuciferController) getLuciferBasicController()).updateLuciferData();
            ((LuciferController) getLuciferBasicController()).getDynamicGameObjectModel().move();
            ((LuciferController) getLuciferBasicController()).getDynamicGameObjectView().updateUI();

            //Move Camera and all Lucifer stat bars:
            if (getLuciferBasicController().getLuciferBasicModel().getY() >= LevelGeneratorPatternModel.END_OF_LEVEL - getLuciferBasicController().getLuciferBasicModel().getHeight()) {
                normalPurgatoryLevelGeneratorView.moveCamera(getLuciferBasicController().getLuciferBasicModel());
                normalPurgatoryLevelGeneratorView.getLuciferBasicView().getLuciferHealthView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight());
                normalPurgatoryLevelGeneratorView.getLuciferBasicView().getLuciferStaminaView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight() + LuciferBasicModel.STAMINA_BAR_DELTA_MOVEMENT);
                normalPurgatoryLevelGeneratorView.getLuciferBasicView().getLuciferFinalAttackBarView().getStatusBar().setLayoutY(getLuciferBasicController().getLuciferBasicModel().getY() + getLuciferBasicController().getLuciferBasicModel().getHeight() + LuciferBasicModel.FINAL_ATTACK_BAR_DELTA_MOVEMENT);
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
             normalPurgatoryLevelGeneratorModel.getDebugInformations().calculateFPS();
             normalPurgatoryLevelGeneratorView.getDebugLabel().setText("FPS: " + normalPurgatoryLevelGeneratorModel.getDebugInformations().getFpsCurrent()); //Number of sprites current on the screen and the overall fps of the game. 

            //unlock next level if Lucifer arrive at the top
             if (getLuciferBasicController().getLuciferBasicModel().getY() <= LevelGeneratorPatternModel.END_OF_LEVEL) {
                 normalPurgatoryLevelGeneratorModel.getGameLoop().stop();
                 DisplayManagerView.setMessage(true);
                 normalPurgatoryLevelGeneratorView.backToMenuScene();
                 CurrentLevelModel.setCurrentLevel(CurrentLevelModel.LEVELS.TRE);
                 CurrentLevelModel.unlock();
             }

             if (!getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                 normalPurgatoryLevelGeneratorModel.setDeathAnimationCounter(normalPurgatoryLevelGeneratorModel.getDeathAnimationCounter() + 1);
                 if (normalPurgatoryLevelGeneratorModel.getDeathAnimationCounter() == LevelGeneratorPatternModel.DEATH_ANIMATION_TIME) {
                     normalPurgatoryLevelGeneratorModel.getGameLoop().stop();
                     DisplayManagerView.setMessage(false);
                     normalPurgatoryLevelGeneratorView.backToMenuScene();
                 }
             }
         }
    });
        normalPurgatoryLevelGeneratorModel.getGameLoop().start();
    }

    //The following methods are used in the CreateGameLoop method.
    /**
     * Method used in the CreateGameLoop method that make purgatory objects move.
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
        for (StandardEnemyController standardEnemyController: this.getEnemyCharactersInScene()) {
            standardEnemyController.death();
            standardEnemyController.getStandardEnemyModel().checkRanged(this.getLuciferBasicController().getLuciferBasicModel().getY());
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.WALKING_ENEMY) {
                ((PurgatoryWalkingEnemyController) standardEnemyController).moveEnemy();
            }
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.BOSS_WALKING_ENEMY) {
                ((PurgatoryWalkingMiniBossController) standardEnemyController).moveEnemy();
            }
            standardEnemyController.getDynamicGameObjectModel().move();

            //Move Game Objects on screen:
            standardEnemyController.getDynamicGameObjectView().getImageView().relocate(standardEnemyController.getGameObjectModel().getX(), standardEnemyController.getGameObjectModel().getY());
        }
    }

    /**
     * Method used in the CreateGameLoop method that make purgatory enemies attack.
     * 
     */
    public void enemiesAttack() {
        for (GameObjectAliveController standardEnemyController: this.getEnemyCharactersInScene()) {
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.SHOOTING_ENEMY) {
                ((PurgatoryShootingEnemyController) standardEnemyController).fire(this.getLuciferBasicController().getLuciferBasicModel().getX(), this.getLuciferBasicController().getLuciferBasicModel().getY(), this.getDynamicObjectsInScene());
            }
            if (standardEnemyController.getDynamicGameObjectModel().getType() == OBJECTSTYPE.BOSS_SHOOTING_ENEMY) {
                ((PurgatoryShootingEnemyController) standardEnemyController).fire(this.getLuciferBasicController().getLuciferBasicModel().getX(), this.getLuciferBasicController().getLuciferBasicModel().getY(), this.getDynamicObjectsInScene());
            }
        }
    }
}
