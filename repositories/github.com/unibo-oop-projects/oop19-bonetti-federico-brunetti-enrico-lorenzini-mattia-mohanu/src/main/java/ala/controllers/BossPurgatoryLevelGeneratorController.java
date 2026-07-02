package ala.controllers;

import java.util.Iterator;

import ala.models.BossPurgatoryLevelGeneratorModel;
import ala.models.CurrentLevelModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
import ala.models.LuciferBasicModel;
import ala.views.BossPurgatoryLevelGeneratorView;
import ala.views.DisplayManagerView;
import ala.views.GameObjectView;
import javafx.animation.AnimationTimer;

/**
 * this class implements the iterations between Caino and the main character.
 * 
 */
public final class BossPurgatoryLevelGeneratorController extends NormalPurgatoryLevelGeneratorController implements BossLevelPattern {
    //Attributes:
    private BossPurgatoryLevelGeneratorModel bossPurgatoryLevelGeneratorModel;
    private BossPurgatoryLevelGeneratorView bossPurgatoryLevelGeneratorView;

    private CainoController cainoController;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param bossPurgatoryLevelGeneratorModel
     * @param bossPurgatoryLevelGeneratorView
     * 
     */
    public BossPurgatoryLevelGeneratorController(final BossPurgatoryLevelGeneratorModel bossPurgatoryLevelGeneratorModel, final BossPurgatoryLevelGeneratorView bossPurgatoryLevelGeneratorView) {
        super(bossPurgatoryLevelGeneratorModel, bossPurgatoryLevelGeneratorView);

        this.bossPurgatoryLevelGeneratorModel = bossPurgatoryLevelGeneratorModel;
        this.bossPurgatoryLevelGeneratorView = bossPurgatoryLevelGeneratorView;

        this.cainoController = new CainoController(bossPurgatoryLevelGeneratorModel.getCainoModel(), bossPurgatoryLevelGeneratorView.getCainoView(), new AsteroidController(bossPurgatoryLevelGeneratorModel.getCainoModel().getAsteroidModel(), bossPurgatoryLevelGeneratorView.getCainoView().getAsteroidView()));
        //getDynamicObjectsInScene().add(cainoController.getMoonController());
        getDynamicObjectsInScene().add(cainoController.getAsteroidController());
    }

    //Getters&Setters:
    public CainoController getCainoController() {
        return cainoController;
    }
    public BossPurgatoryLevelGeneratorView getBossPurgatoryLevelGeneratorView() {
        return bossPurgatoryLevelGeneratorView;
    }

    public BossPurgatoryLevelGeneratorModel getBossPurgatoryLevelGeneratorModel() {
        return bossPurgatoryLevelGeneratorModel;
    }

    //Methods:
    /**
     * it manage the program in sixtieth of second. Then execute every operation in this time.
     * 
     */
    @Override
    public void createGameLoop() {
        this.bossPurgatoryLevelGeneratorModel.setGameLoop(new AnimationTimer()  {

            @Override
            public void handle(final long l) {
                // move GameObjects internally and on Screen
                moveObjectsInScene();
 
                //Updates lucifer and Cerbero internal and on screen position and variables:
                // get keyboard input for: horizontal and jump movements

                ((LuciferController) getLuciferBasicController()).moveLucifer(bossPurgatoryLevelGeneratorView.getInputManager().isMovingLeft(), bossPurgatoryLevelGeneratorView.getInputManager().isMovingRight(), bossPurgatoryLevelGeneratorView.getInputManager().isJumping());
                ((LuciferController) getLuciferBasicController()).getLuciferModel().jumpManager();
                ((LuciferController) getLuciferBasicController()).luciferAttack(getDynamicObjectsInScene(), bossPurgatoryLevelGeneratorView.getInputManager().isUsingMeleeAttack(), bossPurgatoryLevelGeneratorView.getInputManager().isUsingRangedAttack(), bossPurgatoryLevelGeneratorView.getInputManager().isUsingFinalAttack());
                ((LuciferController) getLuciferBasicController()).exitLevel(bossPurgatoryLevelGeneratorView.getInputManager().isExitingLevel());
                ((LuciferController) getLuciferBasicController()).updateLuciferData();
                ((LuciferController) getLuciferBasicController()).getDynamicGameObjectModel().move();
                ((LuciferController) getLuciferBasicController()).getDynamicGameObjectView().updateUI();

                //Enemy fire test
                enemiesAttack();

                //Update the positions of all Michi components also of itself.
                getCainoController().checkPosition();

                // check GameObjects visibility
                // remove every GameObjects that's not visible anymore
                removeGameObjectsIfNotVisible();

                //Check collisions between all the objects into objectInScene list.
                checkAliveObjectInSceneCollisions();
                checkBossCollisions();
                checkLuciferCollisions();
 
                 //Update and show debug info:
                 bossPurgatoryLevelGeneratorModel.getDebugInformations().calculateFPS();
                 bossPurgatoryLevelGeneratorView.getDebugLabel().setText("FPS: " + bossPurgatoryLevelGeneratorModel.getDebugInformations().getFpsCurrent()); //Number of sprites current on the screen and the overall fps of the game. 
 
                 if (!getCainoController().getCainoModel().isAlive() || !getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                     bossPurgatoryLevelGeneratorModel.setDeathAnimationCounter(bossPurgatoryLevelGeneratorModel.getDeathAnimationCounter() + 1);
                     bossPurgatoryLevelGeneratorModel.getCainoModel().setHealth(0);
                     if (bossPurgatoryLevelGeneratorModel.getDeathAnimationCounter() == LevelGeneratorPatternModel.END_DEATH_ANIMATION_COUNTER_VALUE) {
                         bossPurgatoryLevelGeneratorModel.getGameLoop().stop();
                         if (getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                             CurrentLevelModel.setCurrentLevel(CurrentLevelModel.LEVELS.QUATTRO);
                             CurrentLevelModel.unlock();
                             DisplayManagerView.setMessage(true);
                         } else {
                             DisplayManagerView.setMessage(false);
                         }
                         bossPurgatoryLevelGeneratorView.backToMenuScene();
                    }
                }
            }
        }); 

    bossPurgatoryLevelGeneratorModel.getGameLoop().start();

    }
 
    /**
     * every kind of collision with the main character is spotted and managed by this method.
     * 
     */
    @Override
    public void checkLuciferCollisions() {
        //check the collisions between all the GameObjects in the list objectInscene and them whit Lucifer, these collisions will be always in all levels.
        Iterator<StandardEnemyController> iterOnEnemyObjects = this.getEnemyCharactersInScene().iterator();
        while (iterOnEnemyObjects.hasNext()) {
            //Search for collisions with static objects in scene.
            GameObjectAliveController aliveCharacterHitted = iterOnEnemyObjects.next();
            if (getLuciferBasicController().getGameObjectModel().getHitBox().checkCollision(aliveCharacterHitted.getGameObjectModel().getHitBox()) && aliveCharacterHitted.getGameObjectAliveModel().isAlive()) { 
                getLuciferBasicController().gettingDamaged(aliveCharacterHitted.getGameObjectModel().getDamageOnContact());
            }
        }

        //Search for collisions with dynamic objects in scene.
        Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
        while (iterOnDynamicObjects.hasNext()) {
            DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
            if (getLuciferBasicController().getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && getLuciferBasicController().getGameObjectView().getLayer() != dynamicObjectHitted.getGameObjectView().getLayer()) {
                if (dynamicObjectHitted.getGameObjectModel().getType() != OBJECTSTYPE.BOSS) { //FireBall will be ATTACK for including also other attacks ex. arrow of angels ecc...
                    //The alive object get damage
                    getLuciferBasicController().gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                    //The dynamic object is deleted
                    iterOnDynamicObjects.remove();
                    dynamicObjectHitted.getGameObjectView().removeFromLayer();
                } else { //This will be Boss Attack if we don't find a way to guve the power of creation at the bosses.
                      //The alive object get damage
                      getLuciferBasicController().gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                  }
            }
        }

        ((LuciferController) getLuciferBasicController()).getLuciferModel().setLuciferOnPlatform(false);

        //Search for collisions with static objects in scene.
        Iterator<GameObjectView> iterOnStaticObjects = this.bossPurgatoryLevelGeneratorView.getStaticObjectsInSceneViews().iterator();
        while (iterOnStaticObjects.hasNext()) {
            GameObjectView staticObjectHitted = iterOnStaticObjects.next();
            if (((LuciferController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(staticObjectHitted.getGameObjectModel().getHitBox())) { 
                if (staticObjectHitted.getGameObjectModel().getType() == OBJECTSTYPE.PLATFORM) {
                    //block lucifer Y if he is on a new platform.
                    if (getLuciferBasicController().getGameObjectModel().getY() <= staticObjectHitted.getGameObjectModel().getY() && !((LuciferController) getLuciferBasicController()).getLuciferModel().isLuciferOnPlatform()) {
                        ((LuciferController) getLuciferBasicController()).getLuciferModel().setCurrentJumpHeight(0);
                        ((LuciferController) getLuciferBasicController()).getLuciferModel().setDy(0);
                        ((LuciferController) getLuciferBasicController()).getLuciferModel().setLuciferOnPlatform(true);
                        ((LuciferController) getLuciferBasicController()).getLuciferModel().setY(staticObjectHitted.getGameObjectModel().getY() - LuciferBasicModel.LUCIFER_ON_PLATFORM_DELTA_BALANCING);
                    }
                }
            }
        }

        //check the collisions with Lucifer:
        //Search for collisions with static objects in scene.
        if (getLuciferBasicController().getGameObjectModel().getHitBox().checkCollision(cainoController.getGameObjectModel().getHitBox()) && cainoController.getGameObjectAliveModel().isAlive()) { 
            getLuciferBasicController().gettingDamaged(cainoController.getGameObjectModel().getDamageOnContact());
        }
    }

    //Collisions between Cerbero and Other In Scene entities:
    /**
     * every kind of collision with the boss is spotted and managed by this method.
     * 
     */
    @Override
    public void checkBossCollisions() {
        //check the collisions with Lucifer:
        if (cainoController.getGameObjectModel().getHitBox().checkCollision(getLuciferBasicController().getGameObjectModel().getHitBox()) && getLuciferBasicController().getGameObjectAliveModel().isAlive()) { 
            cainoController.gettingDamaged(getLuciferBasicController().getGameObjectModel().getDamageOnContact());
        }

        //menage the collsionos between 2 attacks.
        //Search for collisions with dynamic objects in scene.
        Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
        while (iterOnDynamicObjects.hasNext()) {
            DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
            if (cainoController.getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && cainoController.getGameObjectView().getLayer() != dynamicObjectHitted.getGameObjectView().getLayer() && dynamicObjectHitted.getGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                //The alive object get damage
                cainoController.gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                //The dynamic object is deleted
                iterOnDynamicObjects.remove();
                dynamicObjectHitted.getGameObjectView().removeFromLayer();
            }
        }
    }
}
