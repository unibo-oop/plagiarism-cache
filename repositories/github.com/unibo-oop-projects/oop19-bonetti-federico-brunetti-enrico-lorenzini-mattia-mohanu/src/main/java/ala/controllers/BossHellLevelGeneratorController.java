package ala.controllers;

import java.util.Iterator;
import ala.models.BossHellLevelGeneratorModel;
import ala.models.CerberoModel;
import ala.models.CurrentLevelModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
import ala.models.LuciferBasicModel;
import ala.views.BossHellLevelGeneratorView;
import ala.views.DisplayManagerView;
import ala.views.GameObjectView;
import javafx.animation.AnimationTimer;

/**
 * this class implements the iterations between Cerbero and the main character.
 * 
 */
public final class BossHellLevelGeneratorController extends NormalHellLevelGeneratorController implements BossLevelPattern {

    //Attributes:
    private BossHellLevelGeneratorModel bossHellLevelGeneratorModel;
    private BossHellLevelGeneratorView bossHellLevelGeneratorView;

    private CerberoController cerberoController;

    //Constructor:
    /**
     * Contructor.
     * 
     * @param bossHellLevelGeneratorModel
     * @param bossHellLevelGeneratorView
     * 
     */
    public BossHellLevelGeneratorController(final BossHellLevelGeneratorModel bossHellLevelGeneratorModel, final BossHellLevelGeneratorView bossHellLevelGeneratorView) {
        super(bossHellLevelGeneratorModel, bossHellLevelGeneratorView);

        this.bossHellLevelGeneratorModel = bossHellLevelGeneratorModel;
        this.bossHellLevelGeneratorView = bossHellLevelGeneratorView;

        this.cerberoController = new CerberoController(this.bossHellLevelGeneratorModel.getCerberoModel(), this.bossHellLevelGeneratorView.getCerberoView(), this.getDynamicObjectsInScene());

        this.getDynamicObjectsInScene().add(this.cerberoController.getFireBallController());
        this.getDynamicObjectsInScene().add(this.cerberoController.getFireRainController().getFireBallControllerA());
        this.getDynamicObjectsInScene().add(this.cerberoController.getFireRainController().getFireBallControllerB());
        this.getDynamicObjectsInScene().add(this.cerberoController.getFireRainController().getFireBallControllerC());
    }

    //Getters & Setters:
    public CerberoController getCerberoController() {
        return this.cerberoController;
    }

    public void setCerberoController(final CerberoController cerberoController) {
        this.cerberoController = cerberoController;
    }

    //Methods:
    /**
    * it manage the program in sixtieth of second. Then execute every operation in this time.
    * 
    */
    @Override
    public void createGameLoop() {
        this.bossHellLevelGeneratorModel.setGameLoop(new AnimationTimer()  {
            @Override
            public void handle(final long l) {
                // move GameObjects internally and on Screen
                moveObjectsInScene();

                // get keyboard input for: horizontal and jump movements
                ((LuciferController) getLuciferBasicController()).moveLucifer(bossHellLevelGeneratorView.getInputManager().isMovingLeft(), bossHellLevelGeneratorView.getInputManager().isMovingRight(), bossHellLevelGeneratorView.getInputManager().isJumping());
                ((LuciferController) getLuciferBasicController()).getLuciferModel().jumpManager();
                ((LuciferController) getLuciferBasicController()).luciferAttack(getDynamicObjectsInScene(), bossHellLevelGeneratorView.getInputManager().isUsingMeleeAttack(), bossHellLevelGeneratorView.getInputManager().isUsingRangedAttack(), bossHellLevelGeneratorView.getInputManager().isUsingFinalAttack());
                ((LuciferController) getLuciferBasicController()).exitLevel(bossHellLevelGeneratorView.getInputManager().isExitingLevel());
                ((LuciferController) getLuciferBasicController()).updateLuciferData();
                ((LuciferController) getLuciferBasicController()).getDynamicGameObjectModel().move();
                ((LuciferController) getLuciferBasicController()).getDynamicGameObjectView().updateUI();

                //Enemy fire:
                enemiesAttack();

                //Update the positions of all cerbero components.
                cerberoController.checkPosition(); //Check the position of cerbero the fireball and the firerain

                // check GameObjects visibility
                // remove every GameObjects that's not visible anymore
                removeGameObjectsIfNotVisible();

                //Check collisions between all the objects into objectInScene list.
                checkAliveObjectInSceneCollisions();
                checkBossCollisions();
                checkLuciferCollisions();

                //Update and show debug info:
                bossHellLevelGeneratorModel.getDebugInformations().calculateFPS();
                bossHellLevelGeneratorView.getDebugLabel().setText("FPS: " + bossHellLevelGeneratorModel.getDebugInformations().getFpsCurrent()); //Number of sprites current on the screen and the overall fps of the game. 

                if (!getCerberoController().getCerberoModel().getFirstHead().isAlive() || !getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                    bossHellLevelGeneratorModel.setDeathAnimationCounter(bossHellLevelGeneratorModel.getDeathAnimationCounter() + 1);
                    if (bossHellLevelGeneratorModel.getDeathAnimationCounter() == LevelGeneratorPatternModel.END_DEATH_ANIMATION_COUNTER_VALUE) {
                        bossHellLevelGeneratorModel.getGameLoop().stop();
                        bossHellLevelGeneratorModel.getCerberoModel().getFirstHead().setHealth(0);
                        if (getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                            CurrentLevelModel.setCurrentLevel(CurrentLevelModel.LEVELS.DUE);
                            CurrentLevelModel.unlock();
                            DisplayManagerView.setMessage(true);
                        } else {
                            DisplayManagerView.setMessage(false);
                          }
                        bossHellLevelGeneratorView.backToMenuScene();
                    }
                }
            }
    });
        bossHellLevelGeneratorModel.getGameLoop().start();
    }

    //Collisions between Lucifer and Other enemies , staticObjects or Dynamic objects and Bosses:
    /**
     * every kind of collision with the main character is spotted and managed by this method.
     * 
     */
    @Override
    public void checkLuciferCollisions() {
        //check the collisions between all the GameObjects in the list objectInscene and them whit Lucifer, these collisions will be always in all levels.
        Iterator<StandardEnemyController> iterOnAliveHittedObjects = this.getEnemyCharactersInScene().iterator();
        while (iterOnAliveHittedObjects.hasNext()) {
            //Search for collisions with static objects in scene.
            GameObjectAliveController aliveCharacterHitted = iterOnAliveHittedObjects.next();
            if (((LuciferController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(aliveCharacterHitted.getGameObjectModel().getHitBox()) && aliveCharacterHitted.getGameObjectAliveModel().isAlive()) { 
                ((LuciferController) getLuciferBasicController()).gettingDamaged(aliveCharacterHitted.getGameObjectModel().getDamageOnContact());
            }
        }

        //menage the collsionos between 2 attacks.
        //Search for collisions with dynamic objects in scene.
        Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
        while (iterOnDynamicObjects.hasNext()) {
            DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
            if (((LuciferController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && ((LuciferController) getLuciferBasicController()).getGameObjectView().getLayer() != dynamicObjectHitted.getGameObjectView().getLayer()) {
                if (dynamicObjectHitted.getGameObjectModel().getType() != OBJECTSTYPE.BOSS) { 
                    //The alive object get damage
                    ((LuciferController) getLuciferBasicController()).gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                    //The dynamic object is deleted
                    iterOnDynamicObjects.remove();
                    dynamicObjectHitted.getGameObjectView().removeFromLayer();
                } else { //This will be Boss Attack if we don't find a way to guve the power of creation at the bosses.
                      //The alive object get damage
                      ((LuciferController) getLuciferBasicController()).gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                  }
            }
        }

        ((LuciferController) getLuciferBasicController()).getLuciferModel().setLuciferOnPlatform(false);
        //Search for collisions with static objects in scene.
        Iterator<GameObjectView> iterOnStaticObjects = this.bossHellLevelGeneratorView.getStaticObjectsInSceneViews().iterator();
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
        if (((LuciferController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(cerberoController.getImportantHeadController().getGameObjectModel().getHitBox()) && cerberoController.getImportantHeadController().getGameObjectAliveModel().isAlive()) { 
            ((LuciferController) getLuciferBasicController()).gettingDamaged(cerberoController.getImportantHeadController().getGameObjectModel().getDamageOnContact());
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
        //Search for collisions with static objects in scene.
        if (cerberoController.getImportantHeadController().getGameObjectModel().getHitBox().checkCollision(((LuciferController) getLuciferBasicController()).getGameObjectModel().getHitBox()) && ((LuciferController) getLuciferBasicController()).getGameObjectAliveModel().isAlive()) { 
            cerberoController.gettingDamaged(((LuciferController) getLuciferBasicController()).getGameObjectModel().getDamageOnContact());
        }

        //Search for collisions with dynamic objects in scene.
        Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
        while (iterOnDynamicObjects.hasNext()) {
            DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
            if (cerberoController.getImportantHeadController().getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && CerberoModel.getCerberoType() != dynamicObjectHitted.getGameObjectModel().getType()) {
                //The alive object get damage
                cerberoController.gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                //The dynamic object is deleted
                iterOnDynamicObjects.remove();
                dynamicObjectHitted.getGameObjectView().removeFromLayer();
            }
        }
    }
}

