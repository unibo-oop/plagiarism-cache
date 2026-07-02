package ala.views;

import java.util.ArrayList;
import java.util.List;
import ala.models.DebugInformations;
import ala.models.HellShootingEnemyModel;
import ala.models.HellShootingMiniBossModel;
import ala.models.HellWalkingEnemyModel;
import ala.models.HellWalkingMiniBossModel;
import ala.models.LevelGeneratorPatternModel;
import ala.models.LuciferBasicModel;
import ala.models.LuciferFlyModel;
import ala.models.LuciferModel;
import ala.models.ParadiseShootingEnemyModel;
import ala.models.ParadiseShootingMiniBossModel;
import ala.models.ParadiseWalkingEnemyModel;
import ala.models.ParadiseWalkingMiniBossModel;
import ala.models.PlatformModel;
import ala.models.PurgatoryShootingEnemyModel;
import ala.models.PurgatoryShootingMiniBossModel;
import ala.models.PurgatoryWalkingEnemyModel;
import ala.models.PurgatoryWalkingMiniBossModel;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * LevelGeneratorPatternView class.
 * 
 */
public class LevelGeneratorPatternView implements LevelGeneratorView {

    //Attributes:
    //Game scene assigned to this level.
    private Pane sceneRoot;
    private Scene gameLevelScene;

    private Camera luciferCamera = new PerspectiveCamera();
    private Stage stage;
    private Scene menuScene;
    /**
     * cos'è.
     */
    public static final int CAMERA_FOLLOW_ON_DISTANCE = 720;

    //Input variable:
    private InputManagerView inputManager;

    //Lucifer variables:
    private Pane luciferLayer = new Pane(); //Layer that contains every Lucifer related entities: attacks, himself, health and stamina bars.
    private LuciferBasicView luciferBasicView;

    //Scenario variables:
    private Pane scenarioLayer = new Pane(); //Layer that contains every static object on the screen: platforms, bonus objects.
    private List<GameObjectView> staticObjectsInSceneViews;

    //Enemies Variables:
    private Pane enemiesLayer = new Pane(); //Layer that contains all the things related to common enemies: themself and attacks
    private List<StandardEnemyView> standardEnemyViews;

    //Background variables:
    private Pane backgroundLayer = new Pane(); //Layer that contains all the backgrounds backgrounds.

    //Debug variables:
    private Pane debugLayer = new Pane(); //Layer that contains some debug informations: current fps ratio
    private Label debugLabel;
    private DebugInformations debugInformations;

    private LevelGeneratorPatternModel levelGeneratorPatternModel; //It isn't accessible from out of the class because if the controller wants to access it schould go to dirce access the model without pass from the view.

    /**
     * Constructor.
     * 
     * @param sceneRoot
     * @param gameLevelScene
     * @param stage
     * @param menuScene
     * @param levelGeneratorPatternModel
     * 
     */
    public LevelGeneratorPatternView(final Pane sceneRoot, final Scene gameLevelScene, final Stage stage, final Scene menuScene, final LevelGeneratorPatternModel levelGeneratorPatternModel) {

        this.standardEnemyViews = new ArrayList<StandardEnemyView>();
        this.staticObjectsInSceneViews = new ArrayList<GameObjectView>();

        this.levelGeneratorPatternModel = levelGeneratorPatternModel;

        this.sceneRoot = sceneRoot;
        this.gameLevelScene = gameLevelScene;
        this.stage = stage;
        this.menuScene = menuScene;

        this.inputManager = new InputManagerView(gameLevelScene);
        this.inputManager.addListeners();

        //Set up debug components:
        this.debugInformations = new DebugInformations();
        debugLabel = new Label();
        debugLabel.setTextFill(Color.RED);
        debugLayer.getChildren().add(debugLabel);

        this.initContentView();

        //Add all layers to the Scene
        addToScene();

        gameLevelScene.setCamera(luciferCamera);
    }

    //Methods:
    /**
     * initialize game objects depending on level type.
     * 
     */
     private void initContentView() {
        //Enemies
         for (int i = 0; i < this.levelGeneratorPatternModel.getStandardEnemyModels().size(); i++) {
            switch (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i).getType()) {
                case WALKING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn walking enemy chunk
                               case 1: standardEnemyViews.add(new HellWalkingEnemyView(this.enemiesLayer, (HellWalkingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 2: standardEnemyViews.add(new PurgatoryWalkingEnemyView(this.enemiesLayer, (PurgatoryWalkingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 3: standardEnemyViews.add(new ParadiseWalkingEnemyView(this.enemiesLayer, (ParadiseWalkingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               default:
                               break;
                          }
                         break;
                 case SHOOTING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn shooting enemy chunk
                               case 1: standardEnemyViews.add(new HellShootingEnemyView(this.enemiesLayer, (HellShootingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 2: standardEnemyViews.add(new PurgatoryShootingEnemyView(this.enemiesLayer, (PurgatoryShootingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 3: standardEnemyViews.add(new ParadiseShootingEnemyView(this.enemiesLayer, (ParadiseShootingEnemyModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               default:
                               break;
                         }
                         break;
                 case BOSS_WALKING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn shooting enemy chunk
                               case 1: standardEnemyViews.add(new HellWalkingMiniBossView(this.enemiesLayer, (HellWalkingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 2: standardEnemyViews.add(new PurgatoryWalkingMiniBossView(this.enemiesLayer, (PurgatoryWalkingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 3: standardEnemyViews.add(new ParadiseWalkingMiniBossView(this.enemiesLayer, (ParadiseWalkingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               default:
                               break;
                         }
                         break;
                 case BOSS_SHOOTING_ENEMY: switch (this.levelGeneratorPatternModel.getLvlType()) { //spawn shooting enemy chunk
                               case 1: standardEnemyViews.add(new HellShootingMiniBossView(this.enemiesLayer, (HellShootingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 2: standardEnemyViews.add(new PurgatoryShootingMiniBossView(this.enemiesLayer, (PurgatoryShootingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               case 3: standardEnemyViews.add(new ParadiseShootingMiniBossView(this.enemiesLayer, (ParadiseShootingMiniBossModel) (this.levelGeneratorPatternModel.getStandardEnemyModels().get(i))));
                               break;
                               default:
                               break;
                         }
                         break;
                         default: 
                         break;
            }
         }

        //Platforms
         for (int i = 0; i < this.levelGeneratorPatternModel.getStaticObjectsInSceneModels().size(); i++) {
            switch (this.levelGeneratorPatternModel.getStaticObjectsInSceneModels().get(i).getType()) {
                case PLATFORM: switch (this.levelGeneratorPatternModel.getLvlType()) {
                            case 1: this.staticObjectsInSceneViews.add(new HellPlatformView(this.scenarioLayer, (PlatformModel) (this.levelGeneratorPatternModel.getStaticObjectsInSceneModels().get(i))));
                                    break;
                            case 2: this.staticObjectsInSceneViews.add(new PurgatoryPlatformView(this.scenarioLayer, (PlatformModel) (this.levelGeneratorPatternModel.getStaticObjectsInSceneModels().get(i))));
                                    break;
                            default:
                            break;
                        }
                case CONSUMABLE_ITEM: //Bonus objectNot implemented yet.
                        break;
                default:
                break;
            }
        }

         //Lucifer:
         switch (this.levelGeneratorPatternModel.getLvlType()) {
             case 1: this.luciferBasicView = new LuciferView(this.luciferLayer, (LuciferModel) this.levelGeneratorPatternModel.getLuciferBasicModel());
             break;
             case 2: this.luciferBasicView = new LuciferView(this.luciferLayer, (LuciferModel) this.levelGeneratorPatternModel.getLuciferBasicModel());
             break;
             case 3: this.luciferBasicView = new LuciferFlyView(this.luciferLayer, (LuciferFlyModel) this.levelGeneratorPatternModel.getLuciferBasicModel());
             break;
             default:
             break;
         }
     }

    //Getters&Setters:
    public final InputManagerView getInputManager() {
        return inputManager;
    }

    public final Scene getGameLevelScene() {
        return gameLevelScene;
    }

    public final Stage getStage() {
        return stage;
    }

    public final Scene getMenuScene() {
        return menuScene;
    }

    public final Label getDebugLabel() {
        return debugLabel;
    }

    public final DebugInformations getDebugInformations() {
        return debugInformations;
    }

    public final Pane getluciferLayer() {
        return luciferLayer;
    }

    public final Pane getscenarioLayer() {
        return scenarioLayer;
    }

    public final List<GameObjectView> getStaticObjectsInSceneViews() {
        return staticObjectsInSceneViews;
    }

    public final Pane getenemiesLayer() {
        return enemiesLayer;
    }

    public final List<StandardEnemyView> getStandardEnemyViews() {
        return standardEnemyViews;
    }

    public final Pane getbackgroundLayer() {
        return backgroundLayer;
    }

    public final Pane getdebugLayer() {
        return debugLayer;
    }

    public final LevelGeneratorPatternModel getLevelGeneratorPatternModel() {
        return levelGeneratorPatternModel;
    }

    public final LuciferBasicView getLuciferBasicView() {
        return luciferBasicView;
    }

    public final void setLevelGeneratorPatternModel(final LevelGeneratorPatternModel levelGeneratorPatternModel) {
        this.levelGeneratorPatternModel = levelGeneratorPatternModel;
    }

    public final Camera getLuciferCamera() {
        return luciferCamera;
    }

    //Methods:
    /**
     * Add layers to scene.
     * 
     */
    @Override
    public final void addToScene() {
        //Set up background View components:
        this.sceneRoot.getChildren().add(backgroundLayer);

        //Set up the view scenario elements:
        this.sceneRoot.getChildren().add(scenarioLayer);

        //Set up Lucifer View components:
        this.sceneRoot.getChildren().add(luciferLayer);

        //Set up Debug View components:
        this.sceneRoot.getChildren().add(debugLayer);

        //Set up enemies components:
        this.sceneRoot.getChildren().add(enemiesLayer);
    }
    /**
     * Come back to menu scene.
     * 
     */
    @Override
    public final void backToMenuScene() {
        this.stage.setScene(DisplayManagerView.getEndLevelScene());
    }
    /**
     * Move Camera fallowing Lucifer realtime position.
     * 
     * @param luciferBasicModel
     * 
     */
    @Override
    public final void moveCamera(final LuciferBasicModel luciferBasicModel) {
         this.luciferCamera.setTranslateY(-LevelGeneratorPatternModel.END_OF_LEVEL + 100 + luciferBasicModel.getY());
    }
}
