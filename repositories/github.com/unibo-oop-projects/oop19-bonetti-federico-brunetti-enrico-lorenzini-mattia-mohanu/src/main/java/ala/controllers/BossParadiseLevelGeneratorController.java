package ala.controllers;

import java.util.Iterator;

import ala.models.BossParadiseLevelGeneratorModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
import ala.views.BossParadiseLevelGeneratorView;
import ala.views.DisplayManagerView;
import javafx.animation.AnimationTimer;

/**
 * this class implements the iterations between Michele and the main character.
 * 
 */
public final class BossParadiseLevelGeneratorController extends NormalParadiseLevelGeneratorController implements BossLevelPattern {
    //Attributes:
    private BossParadiseLevelGeneratorModel bossParadiseLevelGeneratorModel;
    private BossParadiseLevelGeneratorView bossParadiseLevelGeneratorView;

    private MicheleController micheleController;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param bossParadiseLevelGeneratorModel
     * @param bossParadiseLevelGeneratorView
     * 
     */
    public BossParadiseLevelGeneratorController(final BossParadiseLevelGeneratorModel bossParadiseLevelGeneratorModel, final BossParadiseLevelGeneratorView bossParadiseLevelGeneratorView) {
        super(bossParadiseLevelGeneratorModel, bossParadiseLevelGeneratorView);

        this.bossParadiseLevelGeneratorModel = bossParadiseLevelGeneratorModel;
        this.bossParadiseLevelGeneratorView = bossParadiseLevelGeneratorView;

        this.micheleController = new MicheleController(bossParadiseLevelGeneratorModel.getMicheleModel(), bossParadiseLevelGeneratorView.getMicheleView());

        this.getDynamicObjectsInScene().add(micheleController.getLightBallController());
        this.getLuciferBasicController().getLuciferBasicModel().setDy(0);
    }

    //Getters & Setters:
    public MicheleController getMicheleController() {
        return micheleController;
    }

    //Methods:
    /**
     * it manage the program in sixtieth of second. Then execute every operation in this time.
     * 
     */
    @Override
    public void createGameLoop() {
        this.bossParadiseLevelGeneratorModel.setGameLoop(new AnimationTimer()  {
            @Override
            public void handle(final long l) {
                // move GameObjects internally and on Screen
                moveObjectsInScene();

                //Updates lucifer and Cerbero internal and on screen position and variables:
                // get keyboard input for: horizontal and jump movements
                ((LuciferFlyController) getLuciferBasicController()).moveLucifer(bossParadiseLevelGeneratorView.getInputManager().isMovingLeft(), bossParadiseLevelGeneratorView.getInputManager().isMovingRight());
                ((LuciferFlyController) getLuciferBasicController()).luciferAttack(getDynamicObjectsInScene(), bossParadiseLevelGeneratorView.getInputManager().isUsingMeleeAttack(), bossParadiseLevelGeneratorView.getInputManager().isUsingRangedAttack(), bossParadiseLevelGeneratorView.getInputManager().isUsingFinalAttack());
                ((LuciferFlyController) getLuciferBasicController()).exitLevel(bossParadiseLevelGeneratorView.getInputManager().isExitingLevel());
                ((LuciferFlyController) getLuciferBasicController()).updateLuciferData();
                ((LuciferFlyController) getLuciferBasicController()).getDynamicGameObjectModel().move();
                ((LuciferFlyController) getLuciferBasicController()).getDynamicGameObjectView().updateUI();

                //Enemy fire test
                enemiesAttack();

                //Update the positions of all Michi components also of itself.
                getMicheleController().checkPosition();

                // check GameObjects visibility
                // remove every GameObjects that's not visible anymore
                removeGameObjectsIfNotVisible();

                //Check collisions between all the objects into objectInScene list.
                checkAliveObjectInSceneCollisions();
                checkBossCollisions();
                checkLuciferCollisions();

                //Update and show debug info:
                bossParadiseLevelGeneratorModel.getDebugInformations().calculateFPS();
                bossParadiseLevelGeneratorView.getDebugLabel().setText("FPS: " + bossParadiseLevelGeneratorModel.getDebugInformations().getFpsCurrent()); //Number of sprites current on the screen and the overall fps of the game. 

                if (!getMicheleController().getMicheleModel().isAlive() || !getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                    bossParadiseLevelGeneratorModel.setDeathAnimationCounter(bossParadiseLevelGeneratorModel.getDeathAnimationCounter() + 1);
                    if (bossParadiseLevelGeneratorModel.getDeathAnimationCounter() == LevelGeneratorPatternModel.END_DEATH_ANIMATION_COUNTER_VALUE) {
                        bossParadiseLevelGeneratorModel.getMicheleModel().setHealth(0);
                        if (getLuciferBasicController().getLuciferBasicModel().isAlive()) {
                            DisplayManagerView.setMessage(true);
                        } else {
                            DisplayManagerView.setMessage(false);
                        }
                        bossParadiseLevelGeneratorModel.getGameLoop().stop();
                        bossParadiseLevelGeneratorView.backToMenuScene();
                    }
               }
         }

    });
    bossParadiseLevelGeneratorModel.getGameLoop().start();
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
        if (micheleController.getGameObjectModel().getHitBox().checkCollision(getLuciferBasicController().getGameObjectModel().getHitBox()) && getLuciferBasicController().getGameObjectAliveModel().isAlive()) { 
            micheleController.gettingDamaged(getLuciferBasicController().getGameObjectModel().getDamageOnContact());
        }

        //menage the collsionos between 2 attacks.
        //Search for collisions with dynamic objects in scene.
        Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
        while (iterOnDynamicObjects.hasNext()) {
            DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
            if (micheleController.getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && micheleController.getGameObjectModel().getType() != dynamicObjectHitted.getGameObjectModel().getType() && dynamicObjectHitted.getGameObjectModel().getType() == OBJECTSTYPE.LUCIFER) {
                //alive object get damage
                micheleController.gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                //The dynamic object is deleted
                iterOnDynamicObjects.remove();
                dynamicObjectHitted.getGameObjectView().removeFromLayer();
            }
        }
    }

    //Collisions between Lucifer and Other enemies , staticObjects or Dynamic objects and Bosses:
    /**
     * every kind of collision with the main character is spotted and managed by this method.
     * 
     */
    public void checkLuciferCollisions() {
        //check the collisions between all the GameObjects in the list objectInscene and them whit Lucifer, these collisions will be always in all levels.
        Iterator<StandardEnemyController> iterOnAliveHittedObjects = this.getEnemyCharactersInScene().iterator();
        while (iterOnAliveHittedObjects.hasNext()) {
            //Search for collisions with static objects in scene.
            GameObjectAliveController aliveCharacterHitted = iterOnAliveHittedObjects.next();
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

        //check the collisions with Lucifer:
        //Search for collisions with static objects in scene.
        if (getLuciferBasicController().getGameObjectModel().getHitBox().checkCollision(micheleController.getGameObjectModel().getHitBox()) && micheleController.getGameObjectAliveModel().isAlive()) { 
            getLuciferBasicController().gettingDamaged(micheleController.getGameObjectModel().getDamageOnContact());
        }
    }
}
