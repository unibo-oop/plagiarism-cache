package view.level;

import com.almasb.fxgl.entity.Level;
import com.almasb.fxgl.parser.text.TextLevelParser;
import view.level.factory.LevelDepth;
import view.level.factory.LevelFactory;

/**
 * This class implements methods to manage levels.
 */
public class GameLevel {

    private final String levelName;
    private final Level level;

    /**
     * The constructor generates the levels and sets the proper factory.
     * @param levelName
     *                  the path of the .txt corresponding to the level.
     * @param depth
     *                  level depth.
     */
    public GameLevel(final String levelName, final LevelDepth depth) {
        this.levelName = levelName;
        final TextLevelParser parser = new TextLevelParser(new LevelFactory(depth));
        level = parser.parse(levelName);
        }

    /**
     * This method return the level generated into the constructor.
     * 
     *  @return level
     *              a fxgl level
     */
    public Level get() {
        return level;
    }

    /**
     * This method is a getter for the level name.
     * 
     * @return levelName
     *                 a string representing the path of the .txt file related to the level
     */
    public String getLevelName() {
        return levelName;
        }
}
