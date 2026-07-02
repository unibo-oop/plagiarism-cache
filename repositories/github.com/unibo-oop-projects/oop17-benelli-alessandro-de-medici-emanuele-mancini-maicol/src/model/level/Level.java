package model.level;

import java.awt.Point;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * LevelImpl interface.
 *
 */
public interface Level {

    /**
     * Getter of level's root.
     * 
     * @return root
     */
    AnchorPane getRoot();

    /**
     * Getter of level's scene.
     * 
     * @return gameScene
     */
    Scene getGameScene();

    /**
     * Getter of level's grid.
     * 
     * @return grid
     */
    GridPane getGrid();

    /**
     * Clears level's grid, removes it and the score from the root.
     */
    void resetGrid();

    /**
     * Getter of level's touched tiles' path.
     * 
     * @return tilePath
     */
    String getTilePath();

    /**
     * Getter of level's background.
     * 
     * @return background
     */
    ImageView getBackground();

    /**
     * Getter of level's background's path.
     * 
     * @return backgroundPath
     */
    String getBackgroundPath();

    /**
     * Getter of level's playable map.
     * 
     * @return playableMap
     */
    List<Point> getPlayableMap();

    /**
     * Prints playableMap List.
     */
    void printPlayableMap();

    /**
     * Getter of level's ending position.
     * 
     * @return endPosition.
     */
    Point getEndPosition();

    /**
     * Getter of level's maximum value of the x coordinate.
     * 
     * @return mapBoundMaxX
     */
    int getMapBoundMaxX();

    /**
     * Getter of level's minimum value of the x coordinate.
     * 
     * @return mapBoundMinX
     */
    int getMapBoundMinX();

    /**
     * Getter of level's maximum value of the y coordinate.
     * 
     * @return mapBoundMaxY
     */
    int getMapBoundMaxY();

    /**
     * Getter of level's minimum value of the y coordinate.
     * 
     * @return mapBoundMinY
     */
    int getMapBoundMinY();

    /**
     * Getter of level's starting column.
     * 
     * @return charStartCol
     */
    int getCharStartCol();

    /**
     * Getter of level's starting column.
     * 
     * @return charStartRow
     */
    int getCharStartRow();

    /**
     * Getter of level's ending column.
     * 
     * @return charFinishCol
     */
    int getCharFinishCol();

    /**
     * Getter of level's ending row.
     * 
     * @return charFinishRow
     */
    int getCharFinishRow();

    /**
     * Getter of level's step sound path.
     * 
     * @return stepSoundPath
     */
    String getStepSoundPath();

    /**
     * Getter of level's music path.
     * 
     * @return musicPath
     */
    String getMusicPath();

    /**
     * Setter of stepSoundPath.
     * 
     * @param stepSoundPath
     *            level's step sound path
     */
    void setStepSoundPath(String stepSoundPath);

    /**
     * Setter of musicPath.
     * 
     * @param musicPath
     *            level's music path
     */
    void setMusicPath(String musicPath);

    /**
     * Starts the music.
     */
    void playMusic();

    /**
     * Resets current level adding its elements to the cleared grid and root.
     */
    void resetCurrentLevel();

}
