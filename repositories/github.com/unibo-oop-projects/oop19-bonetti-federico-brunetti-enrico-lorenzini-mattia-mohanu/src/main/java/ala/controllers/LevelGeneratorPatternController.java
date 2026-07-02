package ala.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ala.models.HellShootingEnemyModel;
import ala.models.HellShootingMiniBossModel;
import ala.models.HellWalkingEnemyModel;
import ala.models.HellWalkingMiniBossModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.LuciferFlyModel;
import ala.models.LuciferModel;
import ala.models.ParadiseShootingEnemyModel;
import ala.models.ParadiseShootingMiniBossModel;
import ala.models.ParadiseWalkingEnemyModel;
import ala.models.ParadiseWalkingMiniBossModel;
import ala.models.PurgatoryShootingEnemyModel;
import ala.models.PurgatoryShootingMiniBossModel;
import ala.models.PurgatoryWalkingEnemyModel;
import ala.models.PurgatoryWalkingMiniBossModel;
import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;
import ala.models.LuciferBasicModel;
import ala.views.GameObjectView;
import ala.views.HellShootingEnemyView;
import ala.views.HellShootingMiniBossView;
import ala.views.HellWalkingEnemyView;
import ala.views.HellWalkingMiniBossView;
import ala.views.LevelGeneratorPatternView;
import ala.views.LuciferFlyView;
import ala.views.LuciferView;
import ala.views.ParadiseShootingEnemyView;
import ala.views.ParadiseShootingMiniBossView;
import ala.views.ParadiseWalkingEnemyView;
import ala.views.ParadiseWalkingMiniBossView;
import ala.views.PurgatoryShootingEnemyView;
import ala.views.PurgatoryShootingMiniBossView;
import ala.views.PurgatoryWalkingEnemyView;
import ala.views.PurgatoryWalkingMiniBossView;

/**
 * LevelGeneratorPatternController.
 * 
 */
public abstract class LevelGeneratorPatternController {
    //Attributes:
    private List<DynamicGameObjectController> dynamicObjectsInScene; //to pass at every controller for permit their to generate gameobjects.
    private List<StandardEnemyController> enemyCharactersInScene; 

    private LevelGeneratorPatternModel levelGeneratorPatternModel;
    private LevelGeneratorPatternView levelGeneratorPatternView;

    private LuciferBasicController luciferBasicController = new LuciferBasicController();

    //Constructor:
    /**
     * Constructor.
     * 
     * @param levelGeneratorPatternModel
     * @param levelGeneratorPatternView
     * 
     */
    public LevelGeneratorPatternController(final LevelGeneratorPatternModel levelGeneratorPatternModel, final LevelGeneratorPatternView levelGeneratorPatternView) {
        this.enemyCharactersInScene = new ArrayList<StandardEnemyController>();
        this.dynamicObjectsInScene = new ArrayList<DynamicGameObjectController>();

        this.levelGeneratorPatternModel = levelGeneratorPatternModel;
        this.levelGeneratorPatternView = levelGeneratorPatternView;

        initContentController();
    }

    //getters&Setters:
    public final List<DynamicGameObjectController> getDynamicObjectsInScene() {
        return dynamicObjectsInScene;
    }

    public final List<StandardEnemyController> getEnemyCharactersInScene() {
        return enemyCharactersInScene;
    }

    public final LevelGeneratorPatternModel getLevelGeneratorPatternModel() {
        return levelGeneratorPatternModel;
    }

    public final LevelGeneratorPatternView getLevelGeneratorPatternView() {
        return levelGeneratorPatternView;
    }

    public final LuciferBasicController getLuciferBasicController() {
        return luciferBasicController;
    }

    //Methods:
    /**
     * Add elements on scene basing on level and objects type.
     * 
     */
    private void initContentController() {
        //Enemies instancing:
        for (int i = 0; i < this.levelGeneratorPatternView.getStandardEnemyViews().size(); i++) {
            switch (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i).getType()) {
            case WALKING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn walking enemy chunk
                          case 1: this.enemyCharactersInScene.add(new HellWalkingEnemyController(
                                  (HellWalkingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (HellWalkingEnemyView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 2: this.enemyCharactersInScene.add(new PurgatoryWalkingEnemyController(
                                  (PurgatoryWalkingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (PurgatoryWalkingEnemyView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 3: this.enemyCharactersInScene.add(new ParadiseWalkingEnemyController(
                                  (ParadiseWalkingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (ParadiseWalkingEnemyView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          default:break;
                     }
                     break;
            case SHOOTING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn shooting enemy chunk
                          case 1: this.enemyCharactersInScene.add(new HellShootingEnemyController(
                                  (HellShootingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (HellShootingEnemyView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 2: this.enemyCharactersInScene.add(new PurgatoryShootingEnemyController(
                                  (PurgatoryShootingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (PurgatoryShootingEnemyView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 3: this.enemyCharactersInScene.add(new ParadiseShootingEnemyController(
                                  (ParadiseShootingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (ParadiseShootingEnemyView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          default:break;
                    }
                    break;
            case BOSS_WALKING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn shooting enemy chunk
                          case 1: this.enemyCharactersInScene.add(new HellWalkingMiniBossController(
                                  (HellWalkingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (HellWalkingMiniBossView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 2: this.enemyCharactersInScene.add(new PurgatoryWalkingMiniBossController(
                                  (PurgatoryWalkingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (PurgatoryWalkingMiniBossView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 3: this.enemyCharactersInScene.add(new ParadiseWalkingMiniBossController(
                                  (ParadiseWalkingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (ParadiseWalkingMiniBossView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          default: break;
                    }
                    break;
            case BOSS_SHOOTING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn shooting enemy chunk
                          case 1: this.enemyCharactersInScene.add(new HellShootingMiniBossController(
                                  (HellShootingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (HellShootingMiniBossView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 2: this.enemyCharactersInScene.add(new PurgatoryShootingMiniBossController(
                                  (PurgatoryShootingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (PurgatoryShootingMiniBossView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          case 3: this.enemyCharactersInScene.add(new ParadiseShootingMiniBossController(
                                  (ParadiseShootingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i)),
                                  (ParadiseShootingMiniBossView) (this.levelGeneratorPatternView.getStandardEnemyViews().get(i))));
                                  break;
                          default:break;
                     }
                     break;
            default:
            break;
            }
        }
            //Lucifer instancing:
            switch (this.levelGeneratorPatternModel.getLvlType()) {
                case 1: this.luciferBasicController = new LuciferController((LuciferModel) this.levelGeneratorPatternModel.getLuciferBasicModel(), (LuciferView) this.levelGeneratorPatternView.getLuciferBasicView());
                break;
                case 2: this.luciferBasicController = new LuciferController((LuciferModel) this.levelGeneratorPatternModel.getLuciferBasicModel(), (LuciferView) this.levelGeneratorPatternView.getLuciferBasicView());
                break;
                case 3: this.luciferBasicController = new LuciferFlyController((LuciferFlyModel) this.levelGeneratorPatternModel.getLuciferBasicModel(), (LuciferFlyView) this.levelGeneratorPatternView.getLuciferBasicView());
                break;
                default: break;
            }
    }

    //Methods:
    //These are all general methods, that group behaviors we usually want to be the same in all the levels.
    /**
     * These are all general methods, that group behaviors we usually want to be the same in all the levels.
     * 
     */
    public void removeGameObjectsIfNotVisible() { //Boss attacks cannot be removed because they use the seme instance of an object to do every attack, so after they couldn't attack again. This problem derive from the impossibility to menage an external list from inside a thread.
        Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
        while (iterOnDynamicObjects.hasNext()) {
            DynamicGameObjectController dynamicGameObjectController = iterOnDynamicObjects.next();
            // check lower screen bounds
            if (dynamicGameObjectController.getGameObjectModel().getY() > (this.levelGeneratorPatternView.getLuciferCamera().getTranslateY() + this.levelGeneratorPatternView.getGameLevelScene().getHeight()) && dynamicGameObjectController.getDynamicGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                dynamicGameObjectController.getGameObjectView().removeFromLayer();
                iterOnDynamicObjects.remove();
                break;
            }

            // check upper screen bounds
            if (dynamicGameObjectController.getGameObjectModel().getY() < this.levelGeneratorPatternView.getLuciferCamera().getTranslateY()  && dynamicGameObjectController.getDynamicGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                dynamicGameObjectController.getGameObjectView().removeFromLayer();
                iterOnDynamicObjects.remove();
                break;
            }

            // check left screen bounds
            if ((dynamicGameObjectController.getGameObjectModel().getX() + dynamicGameObjectController.getGameObjectModel().getWidth() < this.levelGeneratorPatternView.getLuciferCamera().getTranslateX()) && dynamicGameObjectController.getDynamicGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                dynamicGameObjectController.getGameObjectView().removeFromLayer();
                iterOnDynamicObjects.remove();
                break;
            }

            // check right screen bounds
            if ((dynamicGameObjectController.getGameObjectModel().getX() > this.levelGeneratorPatternView.getLuciferCamera().getTranslateX() + this.levelGeneratorPatternView.getGameLevelScene().getWidth()) && dynamicGameObjectController.getDynamicGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                dynamicGameObjectController.getGameObjectView().removeFromLayer();
                iterOnDynamicObjects.remove();
                break;
            }

            //All the attacks that doesn't moving should be temporary and removed after a little delay, this is a magic by Ciccalicca.
            if (dynamicGameObjectController.getDynamicGameObjectModel().getDx() == 0 && dynamicGameObjectController.getDynamicGameObjectModel().getDy() == 0 && this.luciferBasicController.getLuciferBasicModel().getMeleeAttackChargeCounter() == LuciferBasicModel.getMeleeAttackChargeTime() && dynamicGameObjectController.getDynamicGameObjectModel().getType() != OBJECTSTYPE.BOSS) {
                dynamicGameObjectController.getGameObjectView().removeFromLayer();
                iterOnDynamicObjects.remove();
            }
        }
    }

    //Collisions between Lucifer and Other enemies , staticObjects or Dynamic objects:
    /**
     * Check collisions between Lucifer and Other enemies, staticObjects or Dynamic objects.
     * 
     */
    public void checkLuciferCollisions() {
                   //check the collisions between all the GameObjects in the list objectInscene and them whit Lucifer, these collisions will be always in all levels.
                   Iterator<StandardEnemyController> iterOnAliveHittedObjects = enemyCharactersInScene.iterator();
                   while (iterOnAliveHittedObjects.hasNext()) {
                       //Search for collisions with static objects in scene.
                       StandardEnemyController enemyCharacterHitted = iterOnAliveHittedObjects.next();
                       if (((LuciferController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(enemyCharacterHitted.getGameObjectModel().getHitBox()) && enemyCharacterHitted.getGameObjectAliveModel().isAlive()) { 
                           ((LuciferController) getLuciferBasicController()).gettingDamaged(enemyCharacterHitted.getGameObjectModel().getDamageOnContact());
                       }
                   }

                   //Search for collisions with dynamic objects in scene.
                   Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
                   while (iterOnDynamicObjects.hasNext()) {
                        DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
                        if (((LuciferController) getLuciferBasicController()).getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && ((LuciferController) getLuciferBasicController()).getGameObjectView().getLayer() != dynamicObjectHitted.getGameObjectView().getLayer()) {
                            //The alive object get damage
                            ((LuciferController) getLuciferBasicController()).gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                            //The dynamic object is deleted
                            iterOnDynamicObjects.remove();
                            dynamicObjectHitted.getGameObjectView().removeFromLayer();
                        }
                    }

                   ((LuciferController) getLuciferBasicController()).getLuciferModel().setLuciferOnPlatform(false);

                    //Search for collisions with static objects in scene.
                    Iterator<GameObjectView> iterOnStaticObjects = this.levelGeneratorPatternView.getStaticObjectsInSceneViews().iterator();
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
    }

    //Collisions between Enemies and Other enemies , staticObjects or Dynamic objects:
    /**
     * Collisions between Enemies and Other enemies , staticObjects or Dynamic objects.
     * 
     */
    public void checkAliveObjectInSceneCollisions() {
        Iterator<StandardEnemyController> iterOnEnemyObjects = enemyCharactersInScene.iterator();
        while (iterOnEnemyObjects.hasNext()) {
            StandardEnemyController enemyHitted = iterOnEnemyObjects.next();
            Iterator<DynamicGameObjectController> iterOnDynamicObjects = getDynamicObjectsInScene().iterator();
               while (iterOnDynamicObjects.hasNext()) {
                    DynamicGameObjectController dynamicObjectHitted = iterOnDynamicObjects.next();
                    if (enemyHitted.getGameObjectModel().getHitBox().checkCollision(dynamicObjectHitted.getGameObjectModel().getHitBox()) && enemyHitted.getGameObjectAliveModel().isAlive() && enemyHitted.getGameObjectAliveView().getLayer() != dynamicObjectHitted.getGameObjectView().getLayer()) {
                             //the alive object get damage
                             enemyHitted.gettingDamaged(dynamicObjectHitted.getGameObjectModel().getDamageOnContact());
                             //The dynamic object is deleted
                             iterOnDynamicObjects.remove();
                             dynamicObjectHitted.getGameObjectView().removeFromLayer();
                     }
                }
         }
    }
}
