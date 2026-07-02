package it.unibo.ninjafrog.screens.levels;

/**
 * Level interface definition. Every implementation of this interface has to
 * define a {@link it.unibo.ninjafrog.screens.levels.Level#getMap() getMap()}
 * method.
 */
public interface Level {
    /**
     * Getter of the map name.
     * 
     * @return a String representation of the .tmx file name of the level.
     */
    String getMap();
}
