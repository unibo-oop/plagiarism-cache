package ala.models;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
/**
 * LevelGeneratorPatternModel class.
 * 
 */
public abstract class LevelGeneratorPatternModel {

    //Attributes:
    private static final int CHUNK_WIDTH = 60;
    private static final int CHUNCK_HEIGHT = 40;

    /**
     * max level height.
     */
    public static final int END_OF_LEVEL = 600;
    /**
     * setting variable.
     */
    public static final int DEATH_ANIMATION_TIME = 120; 
    /**
     * setting variable.
     */
    public enum OBJECTSTYPE { EMPTY_SPACE, PLATFORM, CONSUMABLE_ITEM, WALKING_ENEMY, SHOOTING_ENEMY, BOSS_WALKING_ENEMY, BOSS_SHOOTING_ENEMY, LUCIFER, BOSS }
    /**
     * setting variable.
     */
    public static final int START_DEATH_ANIMATION_COUNTER_VALUE = 0;
    /**
     * setting variable.
     */
    public static final int END_DEATH_ANIMATION_COUNTER_VALUE = 120;
    /**
     * setting variable.
     */
    public static final boolean START_ANIMATION_TIMER_STOPPED_VALUE = false;

    /**
     * setting variable.
     */
    public enum LVLNUMBER { UNO, DUE, TRE, QUATTRO, CINQUE, SEI };

    private LVLNUMBER lvlNumber;
    private int lvlType;

    private int lvlHeight;

    private AnimationTimer gameLoop;

    private List<StandardEnemyModel> standardEnemyModels;

    private List<GameObjectModel> staticObjectsInSceneModels;

    private BackgroundPatternModel backgroundPatternModel;

    private LuciferBasicModel luciferBasicModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param lvlNumber
     * @param lvlType
     * 
     */
    public LevelGeneratorPatternModel(final LVLNUMBER lvlNumber, final int lvlType) {

        this.standardEnemyModels = new ArrayList<StandardEnemyModel>();
        this.staticObjectsInSceneModels = new ArrayList<GameObjectModel>();

        this.lvlType = lvlType;
        this.lvlNumber = lvlNumber;

        this.backgroundPatternModel = new BackgroundPatternModel();

        this.initContentModel();
    }

    //Getters&Setters:
    public final AnimationTimer getGameLoop() {
        return gameLoop;
    }

    public final List<StandardEnemyModel> getStandardEnemyModels() {
        return standardEnemyModels;
    }

    public final List<GameObjectModel> getStaticObjectsInSceneModels() {
        return staticObjectsInSceneModels;
    }

    public final void setGameLoop(final AnimationTimer gameLoop) {
        this.gameLoop = gameLoop;
    }

    public final BackgroundPatternModel getBackgroundPatternModel() {
        return backgroundPatternModel;
    }

    public final LuciferBasicModel getLuciferBasicModel() {
        return luciferBasicModel;
    }

    public final LVLNUMBER getLvlNumber() {
        return lvlNumber;
    }

    public final int  getLvlType() {
        return lvlType;
    }

    public final int getLvlHeight() {
        return lvlHeight;
    }

    //Methods:
    /**
     * initialize map basing on level number.
     * 
     */
    private void initContentModel() {
        switch (this.lvlNumber) {
            case UNO: readMapAndGenerateLevel(LevelDataModel.getLevel1());
            break;
            case DUE: readMapAndGenerateLevel(LevelDataModel.getLevel2());
            break;
            case TRE: readMapAndGenerateLevel(LevelDataModel.getLevel3());
            break;
            case QUATTRO: readMapAndGenerateLevel(LevelDataModel.getLevel4());
            break;
            case CINQUE: readMapAndGenerateLevel(LevelDataModel.getLevel5());
            break;
            case SEI: readMapAndGenerateLevel(LevelDataModel.getLevel6());
            break;
            default:break;
        }
    }

    //this method generate the level from top to bottom
    /**
     * this method generate the level from top to bottom.
     * 
     * @param levelMap
     * 
     */
    private void readMapAndGenerateLevel(final String[] levelMap) {
        this.lvlHeight = levelMap.length;

        for (int i = 1; i < levelMap.length; i++) { //Start from the second line because the first is always to let empty or for setting the boss spawn position.
            String line = levelMap[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':   //let empty chunk
                        break;
                    case '1': //spawn platform chunk
                        PlatformModel platform = new PlatformModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT);
                        platform.getHitBox().manualHitBoxMovement(j * CHUNK_WIDTH, j * CHUNK_WIDTH + PlatformModel.PLATFORM_HITBOX_X_OFFSET, i * CHUNCK_HEIGHT + PlatformModel.PLATFORM_Y_OFFSET, i * CHUNCK_HEIGHT + PlatformModel.PLATFORM_HITBOX_Y_OFFSET);
                        staticObjectsInSceneModels.add(platform);
                        break;
                    case '2': //spawn bonus objects, not implemented yet
                         break;
                    case '3': switch (this.lvlType) { //spawn walking enemy chunk
                                  case 1: standardEnemyModels.add(new HellWalkingEnemyModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 2: standardEnemyModels.add(new PurgatoryWalkingEnemyModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 3: standardEnemyModels.add(new ParadiseWalkingEnemyModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  default: break;
                              }
                              break;
                    case '4': switch (this.lvlType) { //spawn shooting enemy chunk
                                  case 1: standardEnemyModels.add(new HellShootingEnemyModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 2: standardEnemyModels.add(new PurgatoryShootingEnemyModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 3: standardEnemyModels.add(new ParadiseShootingEnemyModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  default: break;
                              }
                              break;
                    case '5': switch (this.lvlType) { //spawn shooting enemy chunk
                                  case 1: standardEnemyModels.add(new HellWalkingMiniBossModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 2: standardEnemyModels.add(new PurgatoryWalkingMiniBossModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 3: standardEnemyModels.add(new ParadiseWalkingMiniBossModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  default: break;
                              }
                              break;
                    case '6': switch (this.lvlType) { //spawn shooting enemy chunk
                                  case 1: standardEnemyModels.add(new HellShootingMiniBossModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 2: standardEnemyModels.add(new PurgatoryShootingMiniBossModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  case 3: standardEnemyModels.add(new ParadiseShootingMiniBossModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT));
                                  break;
                                  default: break;
                              }
                              break;
                    case '7': switch (this.lvlType) {
                                  case 1: this.luciferBasicModel = new LuciferModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT);
                                  break;
                                  case 2: this.luciferBasicModel = new LuciferModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT);
                                  break;
                                  case 3: this.luciferBasicModel = new LuciferFlyModel(j * CHUNK_WIDTH, i * CHUNCK_HEIGHT);
                                  break;
                                  default: break;
                              }
                              break;
                    default: break;
                }
            }
        }
    }
}
